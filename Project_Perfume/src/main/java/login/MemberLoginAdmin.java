package login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kic.mskim.MsInterceptor;

public class MemberLoginAdmin implements MsInterceptor{

	@Override
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("admin");
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String login = (String)request.getSession().getAttribute("id");
			request.setAttribute("msg", "접근이 불가능 합니다.");
			request.setAttribute("url", "/home/index");
			if (login == null || !login.equals("admin")) {	
			
			return "alert";
			}	else {
			return null;
	}
	}
}