package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.Board;
import cart.Cart;
import product.Product;
import product.ProductDao;

public class OrderMybatis {
	
	private SqlSession sqlSession = mybatis.MybatisConnection.getConnection();
	private static final String NS = "mybatis.Order.";	
	
	
	public int insertOrder(Order order) {
		try {
				return sqlSession.insert(NS+"insertOrder", order);
		} catch(Exception e) {
			e.printStackTrace();
		} finally { sqlSession.commit(); }
		return 0;
	}
	
	/* orderResult
	 * 1 = 주문 완료
	 * 2 = 배송 중
	 * 3 = 주문 취소
	 * 4 = 배송 완료
	 * 5 = 주문 확정
	 */ 
	
	public int insertOrderItem(OrderItem orderItem) {
		try {
				return sqlSession.insert(NS+"insertOrderItem", orderItem);
		} catch(Exception e) {
			e.printStackTrace();
		} finally { sqlSession.commit(); }
		return 0;
	}
	

	 public List<Order> orderList(String id) { // 특정 회원의 주문 리스트
		 return sqlSession.selectList(NS+"orderList",id);
	 }

	 
	 
	 public Order orderOne(String ordernum) { // 주문 정보
		 return sqlSession.selectOne(NS+"orderOne", ordernum);
	 }
	 

	 public List<OrderItem> orderItemList(String ordernum) { // 주문 상세 리스트
		 return sqlSession.selectList(NS+"orderItemList", ordernum);
	 }
	
	 public int orderUpdate(Order order) { // 주문 상태 변경용
		 try {
				return sqlSession.insert(NS+"orderUpdate",order);
			} catch (Exception e) {
				e.printStackTrace();
			} finally { sqlSession.commit(); }
			return 0;
	}
	 
	 
	 public List<Order> orderListAdmin() { // 모든 회원의 주문 리스트 (관리자용)
		 return sqlSession.selectList(NS+"orderListAdmin");
	 }
}
