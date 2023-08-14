package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class BoardAdmin extends HandlerInterceptorAdapter{

	
//	게시글 쓰기를 누를 때 admin 계정이 아니면 boardList로 return
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String login = (String)request.getSession().getAttribute("id");	
		System.out.println(login);
		if(login==null || !login.equals("admin")) {
			response.sendRedirect(request.getContextPath()+"/board/boardList");
			return false;
		} else {
			return true;			
		}
	}
	
}