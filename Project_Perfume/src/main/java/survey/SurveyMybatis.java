package survey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import product.Product;
import mybatis.MybatisConnection;

public class SurveyMybatis {
		
	private SqlSession sqlsession = MybatisConnection.getConnection();
	private static final String NS = "mybatis.Survey.";
	
	public int insertSurvey(Survey survey) {
		
		try {
			return sqlsession.insert(NS+"insertSurvey",survey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.commit();
		}
		return 0;
	}
	
	
	public Product ProductImage(int ansGender, String ans1, String ans2){
			Map map = new HashMap();
			map.put("ansGender", ansGender);
			map.put("ans1", ans1);
			map.put("ans2", ans2);
			System.out.println(map);
			return sqlsession.selectOne(NS+"ProductImage",map);
			}
	
	public List<Survey> surveyList(String id){
		
		Map map = new HashMap();
		map.put("id", id);
		
		return sqlsession.selectList(NS+"surveyList",map);
	}
	
	public List<Survey> AnswerDoughnut(int ansGender){
		
		return sqlsession.selectList(NS+"AnswerDoughnut", ansGender);
	}
	
}//end