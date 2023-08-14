package member;

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

import mybatis.MemberAnno;
import mybatis.MybatisConnection;



public class MemberMybatis {
	SqlSession sqlSession = MybatisConnection.getConnection();
   
   
   public int insertMember(Member m) {
       int num = sqlSession.getMapper(MemberAnno.class).insertMember(m);
       sqlSession.commit();
	   return num;
	  
	 
   }
   
   public Member oneMember(String id) {
	   
	   Member m = sqlSession.getMapper(MemberAnno.class).oneMember(id);
	   
	   return m;
   }
  
   public int updateMember(Member m) {
	  
	   	
	   	
	   	int num = sqlSession.getMapper(MemberAnno.class).updateMember(m);
	       sqlSession.commit();
		   return num;

	   }

   public int deleteMember(String id) {
	   
	   int num = sqlSession.getMapper(MemberAnno.class).deleteMember(id);
       sqlSession.commit();
	   return num;
	   
		
	   }
   
   public int changePass(String id, String newPass) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("pass",newPass);
		   int num = sqlSession.getMapper(MemberAnno.class).changePass(map);
	       sqlSession.commit();
		   return num;
		   }
   
   public List<Member> memberList() {
	     
			return sqlSession.getMapper(MemberAnno.class).memberList();
	   }
}