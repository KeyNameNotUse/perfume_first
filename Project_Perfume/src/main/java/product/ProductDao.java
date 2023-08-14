package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	public Connection getConnection() {
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

	public int insertProduct(Product product) { // 게시글 입력
		Connection con = getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("insert into product values (productseq.nextval,?,?,?,?,?,?,?,?,sysdate,?)");

			pstmt.setString(1, product.getName()); // 상품 이름
			pstmt.setInt(2, product.getPrice()); // 상품 가격
			pstmt.setInt(3, product.getStock()); // 재고
			pstmt.setString(4, product.getInfo()); // 상품 상세정보
			pstmt.setString(5, product.getImage()); // 상품사진
			pstmt.setInt(6, product.getProdgender()); // 성별정보(외래키Member)
			pstmt.setString(7, product.getProdans1()); // 설문정보1(외래키Answer)
			pstmt.setString(8, product.getProdans2()); // 설문정보2(외래키Answer)
			pstmt.setString(9, product.getDetail()); // 상세페이지 이미지

			return pstmt.executeUpdate(); // dml 시 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public int productCount() {
		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		String sql = "select nvl(count(*),0) from product";
		// null 일 때는 0 리턴
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Product> productList(int pageInt, int limit) {
		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		List<Product> li = new ArrayList<>();
		try {
			String sql = "select * from (" + " select rownum rnum, a.* from"
					+ "(select * from product   order by prodnum desc) a) " + "  where rnum BETWEEN ? and ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (pageInt - 1) * limit + 1);
			pstmt.setInt(2, pageInt * limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setProdnum(rs.getInt("prodnum"));
				p.setName(rs.getString("name"));

				p.setPrice(rs.getInt("price"));

				p.setImage(rs.getString("image"));
				p.setProdans1(rs.getString("prodans1"));
				p.setProdans2(rs.getString("prodans2"));
				li.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}

	public Product productOne(int prodnum) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			String sql = "select * from  product where prodnum = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prodnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Product p = new Product();
				p.setProdnum(rs.getInt("prodnum"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setStock(rs.getInt("stock"));
				p.setInfo(rs.getString("Info"));
				p.setImage(rs.getString("image"));
				p.setProdgender(rs.getInt("prodgender"));
				p.setProdans1(rs.getString("prodans1"));
				p.setProdans2(rs.getString("prodans2"));

				p.setRegdate(rs.getDate("regdate"));
				p.setDetail(rs.getString("detail"));

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> productManagement() {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		ResultSet rs = null;
		List<Product> ma = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from product");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setProdnum(rs.getInt("prodnum"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setStock(rs.getInt("stock"));
				p.setInfo(rs.getString("Info"));
				p.setImage(rs.getString("image"));
				p.setProdgender(rs.getInt("prodgender"));
				p.setProdans1(rs.getString("prodans1"));
				p.setProdans2(rs.getString("prodans2"));

				p.setRegdate(rs.getDate("regdate"));
				ma.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ma;
	}

	public int productUpdate(Product product) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("update product set name = ?, price = ?, stock = ?, "
					+ "info = ?, image = ?, prodgender = ?, prodans1 = ?,"
					+ "prodans2 = ?, detail = ?  where prodnum = ?");

			pstmt.setString(1, product.getName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setInt(3, product.getStock());
			pstmt.setString(4, product.getInfo());
			pstmt.setString(5, product.getImage());
			pstmt.setInt(6, product.getProdgender());
			pstmt.setString(7, product.getProdans1());
			pstmt.setString(8, product.getProdans2());
			pstmt.setString(9, product.getDetail());
			pstmt.setInt(10, product.getProdnum());

			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int productDelete(int prodnum) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		String sql = "delete from product where prodnum = ?";

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prodnum);
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;
	}

	public List<Product> orderProductList(int prodnum) { // 주문서에서 찍어낼 product list
	      Connection con = getConnection(); //1
	      PreparedStatement pstmt;
	      ResultSet rs = null;
	      List<Product> li = new ArrayList<>();
	      
	      try {
	    	 String sql = "select * from product where prodnum = ?";
	    	 pstmt = con.prepareStatement(sql);
	    	 pstmt.setInt(1, prodnum);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Product p = new Product();
				p.setProdnum(rs.getInt("prodnum"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setStock(rs.getInt("stock"));
				p.setInfo(rs.getString("Info"));
				p.setImage(rs.getString("image"));
				p.setProdgender(rs.getInt("prodgender"));
				p.setProdans1(rs.getString("prodans1"));
				p.setProdans2(rs.getString("prodans2"));

				p.setRegdate(rs.getDate("regdate"));
	            li.add(p);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return li;
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
			System.out.println("중복상품 추가");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
		
		
		
	}

//	public Product productDetail(int num) {
//		Connection con = getConnection(); // 1
//		PreparedStatement pstmt;		
//		ResultSet rs=null;
//		String sql = "select * from  product where prodnum = ?";
//		try {
//		
//			
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			rs=pstmt.executeQuery() ;
//			if (rs.next()) {
//				Product p = new Product();
//				p.setProdnum(rs.getInt("prodnum"));
//				p.setName(rs.getString("name"));
//				p.setPrice(rs.getInt("price"));
//				p.setStock(rs.getInt("stock"));
//				p.setInfo(rs.getString("Info"));
//				p.setImage(rs.getString("image"));
//				p.setProdgender(rs.getInt("prodgender"));
//				p.setProdans1(rs.getString("prodans1"));
//				p.setProdans2(rs.getString("prodans2"));
//				p.setRegdate(rs.getDate("regdate"));
//				p.setDetail(rs.getString("detail"));
//				
//				return p;		}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		return null;		
//	}