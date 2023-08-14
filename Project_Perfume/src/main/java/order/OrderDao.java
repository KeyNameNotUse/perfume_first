package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.Board;
import cart.Cart;
import product.Product;
import product.ProductDao;

public class OrderDao {
	
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
	
	public int insertOrder(Order order) {
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		String sql = "insert into order2 values (?,?,?,?,?,?,?,?,sysdate)";
		try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, order.getOrdernum());
				pstmt.setString(2, order.getId()); // 주문 회원 ID
				pstmt.setString(3, order.getName()); // 받으시는 분
				pstmt.setString(4, order.getTel()); // 전화번호
				pstmt.setString(5, order.getZipcode()); // 우편번호
				pstmt.setString(6, order.getAddress()); // 주소
				pstmt.setInt(7, order.getResult()); // 주문 상태
				pstmt.setInt(8, order.getDelivercost()); // 배송비
				return pstmt.executeUpdate(); 
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		String sql = "insert into orderitem values (orderitemseq.nextval,?,?,?,?)";
		try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderItem.getOrdernum()); // 주문 번호 (ordernum)
				pstmt.setInt(2, orderItem.getProdnum()); // 상품 번호
				pstmt.setInt(3, orderItem.getQuantity()); // 수량
				pstmt.setInt(4, orderItem.getPrice()); // 가격
				return pstmt.executeUpdate(); 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int stockUpdate(Product product) { // 상품 주문 시 해당 상품의 재고를 update
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		String sql = "update product set stock = ? where prodnum = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product.getStock()); // 재고
			pstmt.setInt(2, product.getProdnum()); // 상품 번호
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int orderedCartDelete (Cart cart) { // 상품 주문 시 해당 상품 장바구니에서 삭제
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		
		String sql = "delete from cart where id = ? and prodnum = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getId());
			pstmt.setInt(2, cart.getProdnum());
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;	
	}
	
	
	
	 public List<Order> orderList(String id) { // 특정 회원의 주문 리스트
	      Connection con = getConnection(); //1
	      PreparedStatement pstmt;
	      ResultSet rs = null;
	      List<Order> li = new ArrayList<>();
	      
	      try {
	    	 String sql = "select * from order2 where id = ? order by regdate desc";
	    	 pstmt = con.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Order o = new Order();
	            o.setOrdernum(rs.getString("ordernum"));
	            o.setId(rs.getString("id"));
	            o.setName(rs.getString("name"));
	            o.setTel(rs.getString("tel"));
	            o.setZipcode(rs.getString("zipcode"));
	            o.setAddress(rs.getString("address"));
	            o.setResult(rs.getInt("result"));
	            o.setDelivercost(rs.getInt("delivercost"));
	            o.setRegdate(rs.getDate("regdate"));
	            li.add(o);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return li;
	   }

	 
	 public Order orderOne(String ordernum) { // 주문 정보
			Connection con = getConnection(); // 1
			PreparedStatement pstmt;		
			ResultSet rs=null;
			try {
			String sql = "select * from order2 where ordernum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ordernum);
				rs=pstmt.executeQuery() ;
				if (rs.next()) {
					Order o = new Order();
		            o.setOrdernum(rs.getString("ordernum"));
		            o.setId(rs.getString("id"));
		            o.setName(rs.getString("name"));
		            o.setTel(rs.getString("tel"));
		            o.setZipcode(rs.getString("zipcode"));
		            o.setAddress(rs.getString("address"));
		            o.setResult(rs.getInt("result"));
		            o.setDelivercost(rs.getInt("delivercost"));
		            o.setRegdate(rs.getDate("regdate"));
					return o;		}
			} catch (SQLException e) {
				e.printStackTrace();
			}		return null;		
		}
	 
	 
	 
	 
	 public OrderItem orderItemOne(String ordernum) { // 주문 상세 정보
		 Connection con = getConnection(); // 1
		 PreparedStatement pstmt;		
		 ResultSet rs = null;
		 
		 try {
			String sql = "select * from orderitem where ordernum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordernum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				OrderItem oi = new OrderItem();
				oi.setOditemnum(rs.getInt("oditemnum"));
	            oi.setOrdernum(rs.getString("ordernum"));
	            oi.setProdnum(rs.getInt("prodnum"));
	            oi.setQuantity(rs.getInt("quantity"));
	            oi.setPrice(rs.getInt("price"));
	            return oi;
			}
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
	 } 
	 
	 
	 public List<OrderItem> orderItemList(String ordernum) { // 주문 상세 리스트
	      Connection con = getConnection(); //1
	      PreparedStatement pstmt;
	      ResultSet rs = null;
	      List<OrderItem> li = new ArrayList<>();
	      
	      try {
	    	 String sql = "select * from orderitem where ordernum = ? order by prodnum desc";
	    	 pstmt = con.prepareStatement(sql);
	    	 pstmt.setString(1, ordernum);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 OrderItem oi = new OrderItem();
		         oi.setOditemnum(rs.getInt("oditemnum"));
		         oi.setOrdernum(rs.getString("ordernum"));
		         oi.setProdnum(rs.getInt("prodnum"));
		         oi.setQuantity(rs.getInt("quantity"));
		         oi.setPrice(rs.getInt("price"));
		         li.add(oi);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return li;
	   }
	
	 public int orderUpdate(Order order) { // 주문 취소
			Connection con = getConnection(); 
			PreparedStatement pstmt;
			String sql = "update order2 set result = ? where ordernum = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order.getResult()); // 주문 상태
				pstmt.setString(2, order.getOrdernum()); // 상품 번호
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
	}
	 
	 
	 public List<Order> orderList() { // 모든 회원의 주문 리스트 (관리자용)
	      Connection con = getConnection(); //1
	      PreparedStatement pstmt;
	      ResultSet rs = null;
	      List<Order> li = new ArrayList<>();
	      
	      try {
	    	 String sql = "select * from order2 order by id, regdate desc";
	    	 pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Order o = new Order();
	            o.setOrdernum(rs.getString("ordernum"));
	            o.setId(rs.getString("id"));
	            o.setName(rs.getString("name"));
	            o.setTel(rs.getString("tel"));
	            o.setZipcode(rs.getString("zipcode"));
	            o.setAddress(rs.getString("address"));
	            o.setResult(rs.getInt("result"));
	            o.setDelivercost(rs.getInt("delivercost"));
	            o.setRegdate(rs.getDate("regdate"));
	            li.add(o);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return li;
	   }
	
}
