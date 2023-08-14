package login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class BoardLoginAdmin extends HandlerInterceptorAdapter{

	
//	게시글 수정, 삭제 시 admin 계정이 아니면 해당 게시글로 return	
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		int num = 1;
		num = Integer.parseInt(request.getParameter("num"));
		String login = (String)request.getSession().getAttribute("id");
		System.out.println(login);
		if(login==null || !login.equals("admin")) {
			response.sendRedirect(request.getContextPath()+"boardComment?num="+num);
			return false;
		} else {
			return true;			
		}
	}
}