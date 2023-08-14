package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import board.Board;
import board.BoardComment;
import board.BoardMybatis;
import kic.mskim.Login;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import member.Member;
import member.MemberMybatis;

@WebServlet(urlPatterns = { "/board/*" }, initParams = { @WebInitParam(name = "view", value = "/view/board/"), // jsp 위치
		@WebInitParam(name = "login", value = "login") }) // package

public class BoardController extends MskimRequestMapping {
	
	@RequestMapping("boardForm") // 게시글 작성 페이지
	@Login("BoardAdmin")
	public String boardForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberMybatis md = new MemberMybatis();
		Member member = md.oneMember(id);

		request.setAttribute("member", member);
		return "boardForm";
	} // boardForm end
	
	
	@RequestMapping("boardPro") // 게시글 업로드
	public String boardPro(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getServletContext().getRealPath("/")+"view/board/images"; // 사진 파일 경로
		String msg = "게시물 등록에 실패하였습니다.";
		String url = "/board/boardForm";
		HttpSession session = request.getSession();
		
		String filename="";
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "utf-8");
			filename = multi.getFilesystemName("image");
			Board board = new Board();
			board.setBoardid(multi.getParameter("boardid"));
			board.setName((String)session.getAttribute("id"));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			board.setImage(filename);
			BoardMybatis bd = new BoardMybatis();
			int num = bd.insertBoard(board);
					
			if (num > 0) {
				msg = "게시물이 등록되었습니다.";
				url = "/board/boardList";
			}
			
			
		} catch (IOException e) {
			e.printStackTrace(); // 오류 메시지 콘솔에 출력
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	} // boardList end
	
	
	@RequestMapping("boardList") // 게시글 목록
	public String boardList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// boardid가 파라미터로 넘어 왔을 때만 session에 저장
		if (request.getParameter("boardid") != null) /* */ {
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", "1");
		}
		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null)
			boardid = "1";

		if (request.getParameter("pageNum") != null) /* pageNum을 넘겨 받음 */ {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null)
			pageNum = "1"; // 넘겨받은 pageNum이 없으면 1페이지로

		int limit = 5; // 한 page 당 게시물 갯수
		int pageInt = Integer.parseInt(pageNum); // page 번호

		BoardMybatis bd = new BoardMybatis();
		int boardCount = bd.boardCount(boardid); // 전체 게시물 갯수
		int boardNum = boardCount - ((pageInt - 1) * limit);

		List<Board> list = bd.boardList(pageInt, limit, boardid);

		String boardName = "";
		switch (boardid) {
		case "1":
			boardName = "공지사항";
			break;
		case "2":
			boardName = "이벤트";
			break;
		}

		int bottomLine = 5;
		int start = (pageInt - 1) / bottomLine * bottomLine + 1;
		// (pageInt-1) / bottomLine -> 1, 2, 3일 때는 0이므로 (pageInt-1) / bottomLine *
		// bottomLine + 1 -> 1
		// (pageInt-1) / bottomLine -> 1, 2, 3일 때는 1이므로 (pageInt-1) / bottomLine *
		// bottomLine + 1 -> 4
		int end = start + bottomLine - 1;
		// start가 1이면 end가 3, start가 4면 end가 6 ...
		int maxPage = (boardCount / limit) + (boardCount % limit == 0 ? 0 : 1);
		if (end > maxPage)
			end = maxPage;

		request.setAttribute("list", list);
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("boardName", boardName);
		request.setAttribute("pageInt", pageInt);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("maxPage", maxPage);

		return "boardList"; // view/board/boardList.jsp
	} // boardList end
	
	
	@RequestMapping("boardUpdateForm")
	@Login("BoardLoginAdmin")
	public String boardUpdateForm(HttpServletRequest request, HttpServletResponse response) {
	
		int num = 1;
		try {
			num = Integer.parseInt(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid == null)
			boardid = "1";

		String boardName = "";
		switch (boardid) {
		case "1":
			boardName = "공지사항";
			break;
		case "2":
			boardName = "이벤트";
			break;
		}

		BoardMybatis bd = new BoardMybatis();
		Board board = bd.boardOne(num);

		request.setAttribute("boardName", boardName);
		request.setAttribute("board", board);
		return "boardUpdateForm";
	} // boardUpdateForm end

	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(HttpServletRequest request, HttpServletResponse response) {
		int num = 1;
		String path = request.getServletContext().getRealPath("/") + "view/board/images/";
		String msg = "";
		String url = "";

		String filename = "";
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");

			Board board = new Board();
			
			board.setNum(Integer.parseInt(multi.getParameter("num")));
			board.setBoardid(multi.getParameter("boardid"));
			board.setName(multi.getParameter("name"));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			
			String image2 = multi.getFilesystemName("image2");
			if (image2 != null) {
				board.setImage(image2);
			} else {
				board.setImage(multi.getParameter("image"));
			}
			
			BoardMybatis bd = new BoardMybatis();
			if (bd.boardUpdate(board) > 0) /* Update OK */ {
					msg = "수정을 완료했습니다.";
					url = "/board/boardComment?num=" + board.getNum(); // 해당 게시물로 이동
				} else { // update fail
					msg = "수정을 실패했습니다.";
					url = "board/boardUpdateForm?num=" + board.getNum(); // 해당 게시물의 UpdateForm으로 이동
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert"; // view/board/alert.jsp 이동
	} // boardUpdatePro end
	
	
	@RequestMapping("boardDeleteForm")
	@Login("BoardLoginAdmin")
	public String boardDeleteForm(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("num", num);
		return "boardDeleteForm";
	} // boardDeleteForm end
	
	
	@RequestMapping("boardDeletePro")
	public String boardDeletePro(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardMybatis  bd = new BoardMybatis();
		String msg = "";
		String url = "";
		if (bd.boardDelete(num) > 0) {
			msg = "게시글이 삭제 되었습니다.";
			url = "/board/boardList";
		} else {
			msg = "삭제가 되지 않았습니다.";
			url = "/board/boardComment?num="+num;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	} // boardDeletePro end

	
	@RequestMapping("boardComment") // 게시글 페이지 (댓글)
	public String boardComment(HttpServletRequest request, HttpServletResponse response) {
		int num = 1;
		try {
			num = Integer.parseInt(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BoardMybatis bd = new BoardMybatis();
		Board board = bd.boardOne(num);
		List<BoardComment> commentLi = bd.commentList(num);
		
		request.setAttribute("commentLi", commentLi);
		request.setAttribute("board", board);
		return "boardComment";
	} // boardComment end
	
	
	@RequestMapping("boardCommentPro") // 댓글 업로드
	public String boardCommentPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int boardnum = 1;
		try {
			boardnum = Integer.parseInt(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = (String)session.getAttribute("id");
		String comment = request.getParameter("comment");
		BoardMybatis bd = new BoardMybatis();
		int num = bd.insertComment(comment, name, boardnum);

		if (num == 0)
			comment = "저장되지 않았습니다.";

		BoardComment c = new BoardComment();
		c.setContent(comment);
		c.setRegdate(new Date());

		request.setAttribute("c", c);

		return "boardCommentPro";
	} // boardCommentPro end
	
	@RequestMapping("boardCommentDeleteForm")
	public String boardCommentDeleteForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		int ser = Integer.parseInt(request.getParameter("ser"));
		
		BoardMybatis  bd = new BoardMybatis();
		BoardComment bc = bd.commentOne(ser);
		int num = bc.getNum();
		
		request.setAttribute("num", num);
		request.setAttribute("ser", ser);
		return "boardCommentDeleteForm";
	}
	
	@RequestMapping("boardCommentDeletePro")
	public String boardCommentDeletePro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("id");
		
		int ser = Integer.parseInt(request.getParameter("ser"));
		
		BoardMybatis  bd = new BoardMybatis();
		BoardComment bc = bd.commentOne(ser);
		String msg = "";
		String url = "";
		
		int num = bc.getNum();
		Board b = bd.boardOne(num);
		int num2 = b.getNum(); // 댓글을 삭제하면 num만으로 해당 게시글로 돌아갈 수 없음.
		
		// 세션의 ID와 다르거나 admin 계정이 아니면 댓글 삭제 불가
		if (name.equals(bc.getName()) || name.equals("admin")) {
			if (bd.commentDelete(ser) > 0) {
				msg = "댓글이 삭제 되었습니다.";
				url = "/board/boardComment?num="+num2;
			} else {
				msg = "오류 발생";
				url =  "/board/boardComment?num="+num2;
			}
		} else {
			msg = "해당 댓글을 작성한 회원만 삭제할 수 있습니다.";
			url =  "/board/boardComment?num="+num2;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	} // boardDeletePro End
	
} // BoardController End
