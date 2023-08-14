package cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
	
	public Connection getConnection() { // db 연동 확인
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic", "1111");
			System.out.println("db ok");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("db error");
		return null;
	}
	
	public int insertCart(Cart cart) {
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		String sql1 = "insert into cart values (cartseq.nextval,?,?,?,?,?,?,sysdate)";
		try {
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, cart.getId()); // 회원 아이디
				pstmt.setInt(2, cart.getProdnum()); // 상품 num
				pstmt.setString(3, cart.getProdname()); // 상품 이름
				pstmt.setInt(4, cart.getQuantity()); // 수량
				pstmt.setInt(5, cart.getPrice()); // 가격
				pstmt.setInt(6, cart.getResult()); // 주문 유무
				return pstmt.executeUpdate(); 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int checkCart(Cart cart) { // 중복되는 상품 체크
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		ResultSet rs=null;
		int result = 0;

		try {
			String sql = "select * from cart where id = ? and prodnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getId());
			pstmt.setInt(2, cart.getProdnum());
			rs = pstmt.executeQuery();
			if (rs.next()) { // 중복된 상품이 있는 경우
				result = 1;
				sql = "update cart set quantity = ? where id = ? and prodnum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart.getQuantity());
				pstmt.setString(2, cart.getId());
				pstmt.setInt(3, cart.getProdnum());
				pstmt.executeUpdate();
				System.out.println("중복상품 추가");
			}					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	 public List<Cart> cartList(String id) { // 장바구니 리스트
	      Connection con = getConnection(); //1
	      PreparedStatement pstmt;
	      ResultSet rs = null;
	      List<Cart> li = new ArrayList<>();
	      
	      try {
	    	 String sql = "select * from cart where id = ?";
	    	 pstmt = con.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Cart c = new Cart();
	            c.setCartid(rs.getInt("cartid"));
	            c.setProdnum(rs.getInt("prodnum"));
	            c.setProdname(rs.getString("prodname"));
	            c.setQuantity(rs.getInt("quantity"));
	            c.setPrice(rs.getInt("price"));
	            c.setResult(rs.getInt("result"));
	            c.setRegdate(rs.getDate("regdate"));
	            li.add(c);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return li;
	   }
	 
	 public Cart cartOne(int cartid) { // 장바구니 정보
		 Connection con = getConnection(); // 1
		 PreparedStatement pstmt;		
		 ResultSet rs = null;
		 
		 try {
			String sql = "select * from cart where cartid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Cart c = new Cart();
	            c.setCartid(rs.getInt("cartid"));
	            c.setId(rs.getString("id"));
	            c.setProdnum(rs.getInt("prodnum"));
	            c.setProdname(rs.getString("prodname"));
	            c.setQuantity(rs.getInt("quantity"));
	            c.setResult(rs.getInt("result"));
	            c.setRegdate(rs.getDate("regdate"));
	            
	            return c;
			}
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 
	 public int cartUpdate(Cart cart) {
			Connection con = getConnection(); // 1
			PreparedStatement pstmt;
			try {
				pstmt = con.prepareStatement
				("update cart set quantity = ? where cartid = ?");
				
				pstmt.setInt(1, cart.getQuantity());
				pstmt.setInt(2, cart.getCartid());

				return pstmt.executeUpdate(); // 3 dml시 실행

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	 
	 public int CartDelete(int cartid) {
			Connection con = getConnection(); // 1
			PreparedStatement pstmt;
			
			String sql = "delete from cart where cartid = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cartid);
				return pstmt.executeUpdate(); // 3 dml시 실행

			} catch (SQLException e) {
					e.printStackTrace();
				}
			return 0;		
		}
	 
	 
	
}
