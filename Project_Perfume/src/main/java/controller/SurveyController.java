package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kic.mskim.Login;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import product.Product;
import survey.Survey;
import survey.SurveyMybatis;

@WebServlet(urlPatterns={"/survey/*"},
initParams = {@WebInitParam(name="view", value="/view/survey/"),
			  @WebInitParam(name="login", value="login")} )


public class SurveyController extends MskimRequestMapping {
	
	@Login("SurveyLoginUser")
	@RequestMapping("survey01")
	public String Survey01(HttpServletRequest request, HttpServletResponse response) {
		return "survey01";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("survey02")
	public String Survey02(HttpServletRequest request, HttpServletResponse response) {
		return "survey02";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("survey03")
	public String Survey03(HttpServletRequest request, HttpServletResponse response) {
		return "survey02";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("survey03flower")
	public String survey03flower(HttpServletRequest request, HttpServletResponse response) {
		return "survey03flower";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("survey03fluit")
	public String survey03fluit(HttpServletRequest request, HttpServletResponse response) {
		return "survey03fluit";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("survey03wood")
	public String survey03wood(HttpServletRequest request, HttpServletResponse response) {
		return "survey03wood";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("surveyResult")
	public String surveyResult(HttpServletRequest request, HttpServletResponse response) {
		return "surveyResult";
	}
	
	@Login("SurveyLoginUser")
	@RequestMapping("surveyStart")
	public String surveyStart(HttpServletRequest request, HttpServletResponse response) {
		// 설문 시작화면에 통계표 구현 
		// 각 성별 선호 향 도넛그래프로 표시
		Survey s = new Survey();
	      SurveyMybatis surbd = new SurveyMybatis();
	      
	      List<Survey> MaleD = surbd.AnswerDoughnut(1);
	      List<Survey> FemaleD = surbd.AnswerDoughnut(2);
	      
	      while(MaleD.size()!=3) {
	         MaleD.add(s);
	         if(MaleD.size()==3)
	         break;
	      }
	      while(FemaleD.size()!=3) {
	         FemaleD.add(s);
	         if(FemaleD.size()==3)
	         break;
	      }
		
	    /*	System.out.println("MaleD: "+MaleD.get(0).getDoughnut()+","
	    								+MaleD.get(1).getDoughnut()+","
	    								+MaleD.get(2).getDoughnut()+"\n"+
	    					 "FemaleD: "+FemaleD.get(0).getDoughnut()+","
	    								+FemaleD.get(1).getDoughnut()+","
	    								+FemaleD.get(2).getDoughnut()); */
	   
	    	request.setAttribute("MaleD", MaleD);
			request.setAttribute("FemaleD", FemaleD);
		
		return "surveyStart";
	}
	
	@RequestMapping("RadioCheckedPro")
	public String RadioCheckedPro(HttpServletRequest request, HttpServletResponse response) {
		String msg ="";	
		Survey survey = new Survey();
		SurveyMybatis surbd = new SurveyMybatis();
		String page = request.getParameter("page");
		//value 확인
		System.out.println("page:"+page);
		System.out.println("성별: "+request.getParameter("ck1gender"));
		System.out.println("선호향: "+request.getParameter("ck2favorite"));
		System.out.println("과일?: "+request.getParameter("ck3fruit"));
		System.out.println("꽃?: "+request.getParameter("ck3flower"));
		System.out.println("나무?: "+request.getParameter("ck3wood"));
		
		
		switch(page){ 
		case "survey02": 
		    request.setAttribute("ck1gender", request.getParameter("ck1gender"));
		    request.setAttribute("ck2favorite", request.getParameter("ck2favorite"));
		    break;
		
		case "survey03fruit": 
		    request.setAttribute("ck1gender", request.getParameter("ck1gender"));
		    request.setAttribute("ck2favorite", request.getParameter("ck2favorite"));
		    break;
		    
		case "survey03flower": 
		    request.setAttribute("ck1gender", request.getParameter("ck1gender"));
		    request.setAttribute("ck2favorite", request.getParameter("ck2favorite"));
		    break;
		    
		case "survey03wood": 
		    request.setAttribute("ck1gender", request.getParameter("ck1gender"));
		    request.setAttribute("ck2favorite", request.getParameter("ck2favorite"));
		    break;
		
		case "surveyResult":  
			request.setAttribute("ck1gender", request.getParameter("ck1gender"));
	        request.setAttribute("ck2favorite", request.getParameter("ck2favorite"));
	        request.setAttribute("ck3fruit", request.getParameter("ck3fruit"));
	        request.setAttribute("ck3flower", request.getParameter("ck3flower"));
	        request.setAttribute("ck3wood", request.getParameter("ck3wood"));
	        
	        
	        int ansGender = Integer.parseInt(request.getParameter("ck1gender"));
			String ans1 = request.getParameter("ck2favorite");
			String ans2 = "";
				if(request.getParameter("ck3fruit")!= null 
						&& !request.getParameter("ck3fruit").equals("")) {
							ans2 = request.getParameter("ck3fruit");			
				}	
				if(request.getParameter("ck3flower")!= null 
						&& !request.getParameter("ck3flower").equals("")) {
							ans2 = request.getParameter("ck3flower");
				}
				if(request.getParameter("ck3wood")!= null
						&& !request.getParameter("ck3wood").equals("")) {
							ans2 = request.getParameter("ck3wood");
				}
			
			// 설문 문항 따라서 보이는 제품사진//제품이름
			// product db에서 image와 name가져오기
			Product p = surbd.ProductImage(ansGender, ans1, ans2);
			System.out.println("resultpage출력"+ansGender+","+ans1+","+ans2);
			
			String image = p.getImage();
			String pname = p.getName();
			
			System.out.println(pname);
			System.out.println(image);
			
			request.setAttribute("pname", pname);
			request.setAttribute("image", image);
			
			
			// 이전 설문 결과 표시 창
			// Answer db에서 id가 가지고잇는 리스트 출력후 배열로 정렬
			String id = (String) request.getSession().getAttribute("id");
			//String id = "1048";
		    survey.setId(id);
		    List<Survey> anslist = surbd.surveyList(id);
		    List<String> anslistImage= new ArrayList<>();
		    List<String> anslistImageName= new ArrayList<>();
		    List<Integer> anslistProdnum = new ArrayList<>();
		    for(int i=0; i<anslist.size(); i++) {
		    	ansGender = anslist.get(i).getAnsGender();
		    	ans1 = anslist.get(i).getAns1();
		    	ans2 = anslist.get(i).getAns2();
		    	p = surbd.ProductImage(ansGender, ans1, ans2);
		    	anslistImage.add(p.getImage());
		    	anslistImageName.add(p.getName());
		    	anslistProdnum.add(p.getProdnum());
		    }
		    System.out.println("anslistImage: "+anslistImage);
		    System.out.println("prodname: "+anslistImageName);
		    System.out.println("prodnum: "+anslistProdnum);
		    
			if(anslistImage!=null && anslistImage.size()!=0) {
				msg = "이전 결과";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("anslistImage", anslistImage);
			request.setAttribute("anslistImageName", anslistImageName);
			request.setAttribute("anslistProdnum", anslistProdnum);
	        return "surveyResult";
		}	
	        
	    
		return page;
	}

// 이미 설문 조사한 사람도 다시 참여가능하며 같은아이디 다른 시퀀스 번호로 설문한 자료 저장됨
	@RequestMapping("surveyInsertPro")
	public String surveyInsertPro(HttpServletRequest request, HttpServletResponse response) {
		String url = "";
		Survey survey = new Survey();
		SurveyMybatis surbd = new SurveyMybatis();
		String page = request.getParameter("page");
		System.out.println(page);
		
		request.setAttribute("ck1gender", request.getParameter("ck1gender"));
        request.setAttribute("ck2favorite", request.getParameter("ck2favorite"));
        request.setAttribute("ck3fruit", request.getParameter("ck3fruit"));
        request.setAttribute("ck3flower", request.getParameter("ck3flower"));
        request.setAttribute("ck3wood", request.getParameter("ck3wood"));
        
        int ansGender = Integer.parseInt(request.getParameter("ck1gender"));		
        String ans1 = request.getParameter("ck2favorite");
        String id = (String) request.getSession().getAttribute("id");
        
        String ans2 = "";
		if(request.getParameter("ck3fruit")!= null 
				&& !request.getParameter("ck3fruit").equals("")) {
					ans2 = request.getParameter("ck3fruit");			
		}	
		if(request.getParameter("ck3flower")!= null 
				&& !request.getParameter("ck3flower").equals("")) {
					ans2 = request.getParameter("ck3flower");
		}
		if(request.getParameter("ck3wood")!= null
				&& !request.getParameter("ck3wood").equals("")) {
					ans2 = request.getParameter("ck3wood");
		}
        
        survey.setId(id); 
		survey.setAnsGender(Integer.parseInt(request.getParameter("ck1gender")));
		survey.setAns1(request.getParameter("ck2favorite"));
		survey.setAns2(ans2);
		// 설문 결과에 나온 상품 이름 불러오기
		Product p = surbd.ProductImage(ansGender,ans1,ans2);
		String prodname = p.getName();
		int prodnum = p.getProdnum();
		survey.setProdname(prodname);
		System.out.println(prodname+":"+prodnum);

		switch(page) {
	      //홈페이지로~
	      case "surveyStart" :
	      
	      int num = surbd.insertSurvey(survey);
	           if(num>0) {
	              url = "/home/index";
	            System.out.println("저장성공");
	         }else {
	            System.out.println("저장실패");
	         }
	           request.setAttribute("url", url);
	           return "alert";
	        
	      //설문결과 사진 누르면~
	      case "product" :

	         num = surbd.insertSurvey(survey);
	           if(num>0) {
	            System.out.println("저장성공");
	            url = "product/productDetail?prodnum=";
	         }else {
	            System.out.println("저장실패");
	         }
	           request.setAttribute("url", url);
	           request.setAttribute("prodnum", prodnum);
	           return "alert2"; 
	         }
	      return null;
	      }
	
	
	

	
		
}//end