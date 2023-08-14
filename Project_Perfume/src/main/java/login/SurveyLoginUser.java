package login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kic.mskim.MsInterceptor;

public class SurveyLoginUser implements MsInterceptor{

	@Override
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		if(id!=null&&!id.equals("")){ // login.ok
		
			return null;
		
		} else {
			String msg = "로그인 하신 유저분들만 설문하실 수 있습니다.";
			String url = "member/loginForm";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			return "alert";
		}
		
	}

}