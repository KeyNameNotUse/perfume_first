package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Board;
import model.BoardComment;



@Repository
public class BoardMybatis {
	
	
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	private static final String NS = "mybatis.Board.";
	
	
	
	public int insertBoard(Board board) {
		return sqlsession.insert(NS+"insertBoard",board);
	}
	
	
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
		return sqlsession.insert(NS+"boardUpdate", board);
	}
		   
		   
	public int boardDelete(int num) {
		sqlsession.delete(NS+"boardDelete2", num);
		return sqlsession.insert(NS+"boardDelete", num);
	}

	
	public int insertComment(String comment, String name, int num) {
		Map map = new HashMap();	
		map.put("comment", comment);
		map.put("name", name);
		map.put("num", num);
		return sqlsession.insert(NS+"insertComment", map);
	}
	
	
	public List<BoardComment> commentList(int num){
		Map map = new HashMap();
		map.put("num", num);
		return sqlsession.selectList(NS+"commentList", num);
	}
	
	
	public BoardComment commentOne(int ser) {
		return sqlsession.selectOne(NS+"commentOne", ser);
	}   
	
	
	public int commentDelete(int ser) {
		return sqlsession.delete(NS+"commentDelete", ser);
	} 
	
	
	
}