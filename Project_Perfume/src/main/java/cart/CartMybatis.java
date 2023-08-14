package cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class CartMybatis {
	
	private SqlSession sqlSession = mybatis.MybatisConnection.getConnection();
	private static final String NS = "mybatis.Cart.";	
	
	
	public int insertCart(Cart cart) { // 장바구니 추가
		try {
				return sqlSession.insert(NS+"insertCart", cart);
		} catch(Exception e) {
			e.printStackTrace();
		} finally { sqlSession.commit(); }
		return 0;
	}
	
	public int checkCart(Cart cart) { // 중복되는 상품 체크
		int result = 0;
		try {
			Map map = new HashMap();
			map.put("id", cart.getId());
			map.put("prodnum", cart.getProdnum());
			cart = sqlSession.selectOne(NS+"cartSelect",map);
			if (cart != null) { // 중복된 상품이 있는 경우
				result = 1;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { sqlSession.commit(); }
		
		return result;
	}
	
	public int checkUpdateCart(int quantity, String id, int prodnum) { // 중복되는 상품 존재할 시에 새로 입력한 수량으로 update
		Map map = new HashMap();
		map.put("quantity", quantity);
		map.put("id", id);
		map.put("prodnum", prodnum);
		
		try {
			return sqlSession.insert(NS+"quantityUpdate",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { sqlSession.commit(); }
		return 0;
	}
	
	 public List<Cart> cartList(String id) { // 장바구니 리스트
	      return sqlSession.selectList(NS+"cartList",id);
	}
	 
	 public Cart cartOne(int cartid) { // 장바구니 정보
		 return sqlSession.selectOne(NS+"cartOne", cartid);
	 }
	 
	 
	 public int cartUpdate(Cart cart) { // 장바구니 페이지에서 상품 수량 수정
			try {
				return sqlSession.insert(NS+"cartUpdate",cart);
			} catch (Exception e) {
				e.printStackTrace();
			} finally { sqlSession.commit(); }
			return 0;
	}
	 
	 public int CartDelete(int cartid) { // 장바구니 삭제
			try {
				return sqlSession.delete(NS+"cartDelete", cartid);
			} catch (Exception e) {
					e.printStackTrace();
			} finally { sqlSession.commit(); }
			return 0;		
	}
	 
	 public int orderedCartDelete (Cart cart) { // 상품 주문 시 해당 상품 장바구니에서 삭제
			try {
				return sqlSession.delete(NS+"orderedCartDelete", cart);

			} catch (Exception e) {
					e.printStackTrace();
			} finally { sqlSession.commit(); }
			return 0;	
	}
	 
	 
	
}
