package login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kic.mskim.MsInterceptor;

public class BoardLoginAdmin implements MsInterceptor{

//	게시글 수정, 삭제 시 admin 계정이 아니면 해당 게시글로 return
	@Override
	public String loginCheck(HttpServletRequest request, HttpServletResponse arg1) {
			System.out.println("admin");
			int num = 1;
			num = Integer.parseInt(request.getParameter("num"));
			
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String login = (String)request.getSession().getAttribute("id");
			request.setAttribute("msg", "접근이 불가능 합니다.");
			request.setAttribute("url", "board/boardComment?num="+num);
			if (login == null || !login.equals("admin")) {	
			
			return "alert";
			} else {
			return null;
			}
	}
}