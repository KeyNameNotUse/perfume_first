package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.MybatisConnection;

public class BoardMybatis {
	
	private SqlSession sqlsession = MybatisConnection.getConnection();
	private static final String NS = "mybatis.Board.";
	
	public int insertBoard(Board board) {
		try {
			return sqlsession.insert(NS+"insertBoard",board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.commit();
		} return 0;	}
	
	
	public int boardCount (String boardid) {
		return sqlsession.selectOne(NS+"boardCount", boardid);
	}
	
	public List<Board> boardList (int pageInt, int limit, String boardid){
		Map map = new HashMap();
		map.put("boardid", boardid);
		map.put("start", (pageInt-1)*limit+1);
		map.put("end", pageInt*limit);
		return sqlsession.selectList(NS+"boardList", map);
	}

	public Board boardOne(int num){
		return sqlsession.selectOne(NS+"boardOne",num);
	}   
	
	public int boardUpdate(Board board) {
		try {
			return sqlsession.insert(NS+"boardUpdate", board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			sqlsession.commit(); 
		} return 0;	}
		   
		   
	public int boardDelete(int num) {
		try {
			sqlsession.delete(NS+"boardDelete2", num);
			return sqlsession.insert(NS+"boardDelete", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			sqlsession.commit(); 
		} return 0; }   	

	
	public int insertComment(String comment, String name, int num) {
		Map map = new HashMap();	
		map.put("comment", comment);
		map.put("name", name);
		map.put("num", num);
		try {
			return sqlsession.insert(NS+"insertComment", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			sqlsession.commit(); 
		} return 0; }
	
	
	public List<BoardComment> commentList(int num){
		Map map = new HashMap();
		map.put("num", num);
		return sqlsession.selectList(NS+"commentList", num);
	}
	
	
	public BoardComment commentOne(int ser) {
		return sqlsession.selectOne(NS+"commentOne", ser);
	}   
	
	
	public int commentDelete(int ser) {
		try {
			return sqlsession.delete(NS+"commentDelete", ser);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			sqlsession.commit(); 
		} return 0; } 
	
	
	
}