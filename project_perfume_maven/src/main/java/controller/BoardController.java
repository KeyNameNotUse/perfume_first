package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.Board;
import model.BoardComment;
import service.BoardMybatis;



@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardMybatis bd;
	
	Model m;
	HttpSession session;
	HttpServletRequest request;
	
	//초기화 작업을 한다, 객체 초기화시에 사용한다
	@ModelAttribute
	void init (HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		session = request.getSession();
	}	
	
	
	
	
	
	@RequestMapping("boardForm") // 게시글 작성 페이지
	public String boardForm() {
		
		return "board/boardForm";
	} // boardForm end
	
	
	
	
	@RequestMapping("boardPro") // 게시글 업로드
	public String boardPro(
			@RequestParam("f") MultipartFile multipartFile, Board board) {
		
		String path = request.getServletContext().getRealPath("/")+"WEB-INF/view/board/images"; // 사진 파일 경로
		String msg = "게시물 등록에 실패하였습니다.";
		String url = "/board/boardForm";
		String filename="";

		if(!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			filename = multipartFile.getOriginalFilename();
			
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String boardid = (String)session.getAttribute("boardid");
		if (boardid == null) {
			boardid="1";
			}
		
		board.setBoardid(boardid);
		board.setImage(filename);
		System.out.println(board);
		int num = bd.insertBoard(board);
		if(num>0) {
			msg = "게시물 등록 성공";
			url = "/board/boardList";	
		}

		m.addAttribute("msg", filename+" : "+msg);
		m.addAttribute("url", url);
		return "alert";
	} // boardPro end
	
	
	
	
	@RequestMapping("boardList") // 게시글 목록
	public String boardList() {

		// boardid가 파라미터로 넘어 왔을 때만 session에 저장
		if (request.getParameter("boardid") != null) /* */ {
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", "1");
		}
		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null) {
			boardid = "1";
		}

		if (request.getParameter("pageNum") != null) /* pageNum을 넘겨 받음 */ {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null)
			pageNum = "1"; // 넘겨받은 pageNum이 없으면 1페이지로

		int limit = 5;								// 한 page 당 게시물 갯수
		int pageInt = Integer.parseInt(pageNum);	// page 번호
		int boardCount = bd.boardCount(boardid);	// 전체 게시물 갯수
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
		// (pageInt-1) / bottomLine -> 1, 2, 3일 때는 0이므로 
		// [(pageInt-1) / bottomLine * bottomLine + 1] -> 1
		// (pageInt-1) / bottomLine -> 1, 2, 3일 때는 1이므로 
		// [(pageInt-1) / bottomLine * bottomLine + 1] -> 4
		int end = start + bottomLine - 1;
		// start가 1이면 end가 3, start가 4면 end가 6 ...
		int maxPage = (boardCount / limit) + (boardCount % limit == 0 ? 0 : 1);
		if (end > maxPage)
			end = maxPage;

		m.addAttribute("list", list);
		m.addAttribute("boardNum", boardNum);
		m.addAttribute("boardName", boardName);
		m.addAttribute("pageInt", pageInt);
		m.addAttribute("bottomLine", bottomLine);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("maxPage", maxPage);

		return "board/boardList"; // view/board/boardList.jsp
	} // boardList end
	
	
	
	
	@RequestMapping("boardUpdateForm")
	public String boardUpdateForm(@RequestParam("num") int num) {
	
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

		Board board = bd.boardOne(num);

		request.setAttribute("boardName", boardName);
		request.setAttribute("board", board);
		return "board/boardUpdateForm";
	} // boardUpdateForm end

	
	
	
	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(
			@RequestParam("f") MultipartFile multipartFile, Board board) {
		
		String path = request.getServletContext().getRealPath("/")+"WEB-INF/view/board/images";
		String msg = "";
		String url = "";
		String filename= " ";
		
		if(!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			filename = multipartFile.getOriginalFilename();
			
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		board.setImage(filename);
		
		if (bd.boardUpdate(board) > 0) /* Update OK */ {
			msg = "수정을 완료했습니다.";
			url = "/board/boardComment?num=" + board.getNum(); // 해당 게시물로 이동
			} else { // update fail
				msg = "수정을 실패했습니다.";
				url = "board/boardUpdateForm?num=" + board.getNum(); // 해당 게시물의 UpdateForm으로 이동
			}

		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert"; // view/board/alert.jsp 이동
	} // boardUpdatePro end
	
	
	
	
	
	@RequestMapping("boardDeleteForm")
	public String boardDeleteForm(@RequestParam("num") int num) {

		m.addAttribute("num", num);
		return "board/boardDeleteForm";
	} // boardDeleteForm end
	
	
	
	
	
	@RequestMapping("boardDeletePro")
	public String boardDeletePro(@RequestParam("num") int num) {

		String msg = "";
		String url = "";
		
		if (bd.boardDelete(num) > 0) {
			msg = "게시글이 삭제 되었습니다.";
			url = "/board/boardList";
		} else {
			msg = "삭제가 되지 않았습니다.";
			url = "/board/boardComment?num="+num;
		}
		
		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	} // boardDeletePro end

	
	
	
	@RequestMapping("boardComment") // 게시글 페이지 (댓글)
	public String boardComment(@RequestParam("num") int num) {

		Board board = bd.boardOne(num);
		List<BoardComment> commentLi = bd.commentList(num);
		
		m.addAttribute("commentLi", commentLi);
		m.addAttribute("board", board);
		return "board/boardComment";
	} // boardComment end
	
	
	
	
	
	@RequestMapping("boardCommentPro") // 댓글 업로드
	public String boardCommentPro() {
		
		int boardnum = 1;
		String name = (String)session.getAttribute("id");
		String comment = request.getParameter("comment");

		int num = bd.insertComment(comment, name, boardnum);

		if (num == 0)
			comment = "저장되지 않았습니다.";

		BoardComment c = new BoardComment();
		c.setContent(comment);
		c.setRegdate(new Date());

		m.addAttribute("c", c);

		return "board/boardCommentPro";
	} // boardCommentPro end
	
	
	
	@RequestMapping("boardCommentDeleteForm")
	public String boardCommentDeleteForm() {
		
		int ser = Integer.parseInt(request.getParameter("ser"));

		BoardComment bc = bd.commentOne(ser);
		int num = bc.getNum();
		
		m.addAttribute("num", num);
		m.addAttribute("ser", ser);
		return "board/boardCommentDeleteForm";
	}
	
	
	
	@RequestMapping("boardCommentDeletePro")
	public String boardCommentDeletePro() {
		

		String name = (String)session.getAttribute("id");
		
		int ser = Integer.parseInt(request.getParameter("ser"));
		

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
		
		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	} // boardDeletePro End
	
	
	@RequestMapping("alert")
	public String alert(String id) {	
		
		if(id.equals("admin")) {
			m.addAttribute("msg", "접근 불가 합니다");
			} else {
				m.addAttribute("msg", "로그인 하세요");
			}
		m.addAttribute("url", "board/loginForm");

		return "alert";		}		
	
	
} // BoardController End
