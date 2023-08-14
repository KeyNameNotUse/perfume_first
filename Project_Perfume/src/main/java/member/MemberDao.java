package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO
 * Data Access Object
 * 데이터베이스의 data에 접근하기 위한 객체.
 * DataBase에 접근하기 위한 로직&비즈니스 로직을 분리하기 위해 사용
 * 
 * 
 * DTO
 * Data Transfer Object
 * 계층간 데이터 교환을 하기 위해 사용하는 객체
 * 로직을 가지지 않는 setter getter
 * 
 */
public class MemberDao {
   
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
   
   public int insertMember(Member m) {
      Connection con = getConnection(); //1
      
      PreparedStatement pstmt;
      try {
         pstmt = con.prepareStatement(
            "insert into member2 values (?,?,?,?,?,?,?,?, sysdate)");
         pstmt.setString(1, m.getId());
         pstmt.setString(2, m.getPass());
         pstmt.setString(3, m.getName());
         pstmt.setInt(4, m.getGender());
         pstmt.setString(5, m.getTel());
         pstmt.setString(6, m.getEmail());
         pstmt.setString(7, m.getZipcode());
         pstmt.setString(8, m.getAddress());

         return pstmt.executeUpdate(); //3 dml시 실행
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return 0;
   }
   
   public Member oneMember(String id) {
      Connection con = getConnection(); //1
      
      PreparedStatement pstmt;
      ResultSet rs = null;
      try {
         pstmt = con.prepareStatement(
            "select * from member2 where id = ?");
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            Member m = new Member();
            m.setId(rs.getString("id"));
            m.setPass(rs.getString("pass"));
            m.setName(rs.getString("name"));
            m.setGender(rs.getInt("gender"));
            m.setTel(rs.getString("tel"));
            m.setEmail(rs.getString("email"));
            m.setZipcode(rs.getString("zipcode"));
            m.setAddress(rs.getString("address"));
            
            return m;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }
  
   //memberUpdatePro에 updateMember를 위에 insertMember에서 긁어옴.
   public int updateMember(Member m) {
	      Connection con = getConnection(); //1
	      
	      PreparedStatement pstmt;
	      String sql = "update member2 set name=?, gender=?, tel=?, "
	    		 +	"email=?, zipcode=?, address=? where id = ?";
	      
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, m.getName());
	         pstmt.setInt(2, m.getGender());
	         pstmt.setString(3, m.getTel());
	         pstmt.setString(4, m.getEmail());
	         pstmt.setString(5, m.getZipcode());
	         pstmt.setString(6, m.getAddress());
	         pstmt.setString(7, m.getId());

	         return pstmt.executeUpdate(); //3 dml시 실행
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return 0;
	   }

   public int deleteMember(String id) {
	      Connection con = getConnection(); //1
	      
	      PreparedStatement pstmt;
	      String sql = "delete from member2 where  id = ?";
	    		
	      
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, id);
	         return pstmt.executeUpdate(); //3 dml시 실행
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return 0;
	   }
   
   public int changePass(String id, String newPass) {
	      Connection con = getConnection(); //1
	      
	      PreparedStatement pstmt;
	      String sql = "update member2 set pass=? where id = ?";
	    		
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, newPass);
	         pstmt.setString(2, id);
	         
	         return pstmt.executeUpdate(); //3 dml시 실행
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return 0;
	   }
   
   public List<Member> memberList() {
	      Connection con = getConnection(); //1
	      
	      PreparedStatement pstmt;
	      ResultSet rs = null;
	      List<Member> li = new ArrayList<>();
	      
	      try {
	         pstmt = con.prepareStatement(
	            "select * from member2");
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Member m = new Member();
	            m.setId(rs.getString("id"));
	            m.setPass(rs.getString("pass"));
	            m.setName(rs.getString("name"));
	            m.setGender(rs.getInt("gender"));
	            m.setTel(rs.getString("tel"));
	            m.setEmail(rs.getString("email"));
	            m.setZipcode(rs.getString("zipcode"));
	            m.setAddress(rs.getString("address"));
	            li.add(m);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return li;
	   }
}