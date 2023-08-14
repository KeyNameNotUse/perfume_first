package survey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import product.Product;

public class SurveyDao {

	public Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
				Connection scon =DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe","kic","1111");
				System.out.println("db ok");
				return scon;
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		System.out.println("db ok");
		return null;
	
	
}
	//설문저장
	public int insertSurvey(Survey survey) {
		Connection con = getConnection();
		PreparedStatement pstmt;
		String sql = "insert into answer values(surveyseq.nextval,?,?,?,?,?, sysdate)";
		
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, survey.getId());
			pstmt.setInt(2, survey.getAnsGender());
			pstmt.setString(3, survey.getAns1());
			pstmt.setString(4, survey.getAns2());
			pstmt.setString(5, survey.getProdname());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	// 설문 결과내역에 따른 상품불러오기
	public Product ProductImage(int ansGender, String ans1, String ans2){

		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		
			
			String sql = " select * from product where prodgender = ? "
						+" and prodans1 = ? and prodans2 = ? ";
			
			/*
			  String sql ="select * from ( "
			 
					   +"select 1, p.* from product p where prodgender = ? and prodans1 like '%"+ans1+"%' and PRODANS2 like '%"+ans2+"'"
					   +" union "
					   +"select 2, p.* from product p where prodgender = ? and prodans1 like '%"+ans1+"%'"
					   +" union "
					   +"select 3, p.* from product p where  prodgender = ? and PRODANS2 like '%"+ans2+"%') " 
					   +" where rownum = 1";
			*/
		
		/*	System.out.println("=======Dao=======");
			System.out.println(ansGender);
			System.out.println(ans1);
			System.out.println(ans2); 
		*/	
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ansGender);
			pstmt.setString(2, ans1);
			pstmt.setString(3, ans2);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Product p = new Product();
				p.setProdgender(rs.getInt("prodgender"));
				p.setProdans1(rs.getString("prodans1"));
				p.setProdans2(rs.getString("prodans2"));
				p.setName(rs.getString("name"));
				p.setImage(rs.getString("image"));
				p.setProdnum(rs.getInt("prodnum")); //prodnum추가하고 확인아직
			//  System.out.println(p);
				return p;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
			}
	//이전 결과
	public List<Survey> surveyList(String id){
		
		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		List<Survey> li = new ArrayList<>();
		System.out.println(id);
		try {
			String sql=   "  select * from ( "
						+ "  select rownum rnum, a.* from (  "
						+ "  select * from answer where id = ?  "
						+ "  order by ser desc) a) " ;
			System.out.println(id);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Survey s = new Survey();
				s.setId(rs.getString("id"));
				s.setAnsGender(rs.getInt("ansgender"));
				s.setAns1(rs.getString("ans1"));
				s.setAns2(rs.getString("ans2"));
				s.setProdname(rs.getString("prodname"));
				li.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return li;
	}
	//통계내역
	// mybatis 바꾼뒤 확인x
	public List<Survey> AnswerDoughnut(int ansgender){
		
		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		List<Survey> li = new ArrayList<>();
		try {
			String sql = "  select  ans1, count(*) doughnut from answer  " 
						+"  where ansGender = ?  "
						+"  group by  ans1  "
						+"  order  by  1  ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ansgender);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Survey s = new Survey();
				s.setDoughnut(rs.getInt("count"));
				li.add(s);
			}
			System.out.println(li);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return li;
	}
	
	
	
	
	
	}//end