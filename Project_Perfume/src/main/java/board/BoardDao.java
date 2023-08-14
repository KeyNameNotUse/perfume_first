package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
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
	
	public int insertBoard(Board board) { // 게시글 입력
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement
					("insert into board2 values (boardseq2.nextval,?,?,?,?,?,sysdate,0)");
			
			pstmt.setString(1, board.getBoardid()); // 게시판 종류
			pstmt.setString(2, board.getName()); // 작성자
			pstmt.setString(3, board.getSubject()); // 제목
			pstmt.setString(4, board.getContent()); // 내용
			pstmt.setString(5, board.getImage()); // 첨부된 사진
			
			return pstmt.executeUpdate(); // dml 시 실행
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int boardCount (String boardid) {
		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		String sql = "select nvl(count(*),0) from board2 where boardid = ?";
		// null 일 때는 0 리턴
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Board> boardList (int pageInt, int limit, String boardid) {
		Connection con = getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		List<Board> li = new ArrayList<>();
		try {
			String sql = "select * from (" + "select rownum rnum, a.* from"
		+ "(select * from board2 where boardid = ?"
		+ " order by num desc) a) where rnum BETWEEN ? and ?";
			
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, boardid);
		pstmt.setInt(2, (pageInt - 1) * limit + 1); // 1, 11, 21, ...
		pstmt.setInt(3, pageInt * limit); // 10, 20, 30, ...
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Board b = new Board();
			b.setNum(rs.getInt("num"));
			b.setName(rs.getString("name"));
			b.setSubject(rs.getString("subject"));
			b.setContent(rs.getString("content"));
			b.setImage(rs.getString("image"));
			b.setRegdate(rs.getDate("regdate"));
			b.setReadcnt(rs.getInt("readcnt"));
			li.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;		
	}
	
	public Board boardOne(int num) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;		
		ResultSet rs=null;
		try {
		String sql = "select * from board2 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery() ;
			if (rs.next()) {
				Board b = new Board();
				b.setNum(rs.getInt("num"));
				b.setName(rs.getString("name"));
				b.setSubject(rs.getString("subject"));
				b.setContent(rs.getString("content"));
				b.setImage(rs.getString("image"));
				b.setRegdate(rs.getDate("regdate"));
				b.setReadcnt(rs.getInt("readcnt"));
				return b;		}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return null;		
	}
	
	public int boardUpdate(Board board) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement
			("update board2 set boardid = ?, subject = ?, content = ?, image = ? where num = ?");
			
			pstmt.setString(1, board.getBoardid());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getImage());
			pstmt.setInt(5, board.getNum());
			
			
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int boardDelete(int num) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		String sql1 = "delete from board2 where num = ?";
		String sql2 = "delete from boardcomment2  where num = ?";
		try {
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;		
	}
	
	
	
	
	public int insertComment(String comment, String name, int num) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement
			("insert into boardcomment2 values (boardcomseq2.nextval,?,?,?,sysdate)");
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, comment);
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<BoardComment> commentList(int num) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;		
		ResultSet rs=null;
		List<BoardComment> li = new ArrayList<>();
		try {	
		String sql = "select * from boardcomment2 where num = ?  order by regdate desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				BoardComment c = new BoardComment();
				c.setSer(rs.getInt("ser"));
				c.setName(rs.getString("name"));
				c.setNum(rs.getInt("num"));
				c.setContent(rs.getString("content"));
				c.setRegdate(rs.getDate("regdate"));
				li.add(c);		}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return li;		
	}
	
	public BoardComment commentOne(int ser) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;		
		ResultSet rs=null;
		try {
		String sql = "select * from boardcomment2 where ser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ser);
			rs=pstmt.executeQuery() ;
			if (rs.next()) {
				BoardComment c = new BoardComment();
				c.setSer(rs.getInt("ser"));
				c.setNum(rs.getInt("num"));
				c.setName(rs.getString("name"));
				c.setContent(rs.getString("content"));
				c.setRegdate(rs.getDate("regdate"));
				return c;		}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return null;		
	}
	
	public int commentDelete(int ser) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		String sql = "delete from boardcomment2 where ser = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ser);
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;		
	}	
	
	
} // end class