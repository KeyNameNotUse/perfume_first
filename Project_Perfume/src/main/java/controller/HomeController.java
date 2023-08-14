package controller;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.Board;
import board.BoardDao;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;

@WebServlet(urlPatterns= {"/home/*"},  initParams = { @WebInitParam(name = "view", value = "/view/home/")})

public class HomeController extends  MskimRequestMapping {
	
	@RequestMapping("index") // /test/index
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		return "index";
	}
	
	@RequestMapping("about") // /test/index
	public String about(HttpServletRequest request, HttpServletResponse response) {
		return "about";
	}
}
