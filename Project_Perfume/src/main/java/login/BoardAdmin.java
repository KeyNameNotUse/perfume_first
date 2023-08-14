package login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kic.mskim.MsInterceptor;

public class BoardAdmin implements MsInterceptor {

//	게시글 쓰기를 누를 때 admin 계정이 아니면 boardList로 return
	@Override
	public String loginCheck(HttpServletRequest request, HttpServletResponse arg1) {
			System.out.println("admin");
			
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String login = (String)request.getSession().getAttribute("id");
			request.setAttribute("msg", "접근이 불가능 합니다.");
			request.setAttribute("url", "board/boardList");
			if (login == null || !login.equals("admin")) {	
			
			return "alert";
			} else {
			return null;
			}
	}
}
