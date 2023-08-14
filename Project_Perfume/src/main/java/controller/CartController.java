package controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.Cart;
import cart.CartDao;
import cart.CartMybatis;
import kic.mskim.Login;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import member.Member;
import member.MemberMybatis;
import product.Product;
import product.ProductDao;

@WebServlet(urlPatterns= {"/cart/*"},  
initParams= {@WebInitParam(name="view",value="/view/cart/"),//jsp 위치
		@WebInitParam(name="login",value="login")} )  //package

public class CartController extends MskimRequestMapping {
	//CartController 클래스에서는 CartDao를 이용하여 데이터를 가져온 후, 해당 데이터를 View에 전달한다
	
	@RequestMapping("cartPro") // 상세 페이지에서 장바구니 추가
	@Login("COLoginUser")
	public String cartPro2(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
    	String id = (String)session.getAttribute("id");
    	
    	String msg = "";
    	String url = "";
    	
    	try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Cart c = new Cart();
    	c.setId(id);
    	int prodnum = Integer.parseInt(request.getParameter("prodnum"));
    	int quantity = Integer.parseInt(request.getParameter("quantity"));
    	c.setProdnum(prodnum);
    	c.setProdname(request.getParameter("prodname"));
    	c.setQuantity(quantity);
    	c.setPrice(Integer.parseInt(request.getParameter("price")));
    	c.setResult(1); // 주문유무. 기본 1 = 주문 X 상태
    	
    	ProductDao pd = new ProductDao();
    	Product product = pd.productOne(prodnum);
    	
    	CartMybatis cd = new CartMybatis();
    	int result = cd.checkCart(c);
    	
    	if (result != 1) {
    		if (product.getStock() < quantity) { // 주문 수량이 재고보다 많은 경우
    			msg = "장바구니에 추가하실 수량이 재고보다 많습니다.";
    			url = "product/productDetail?prodnum="+prodnum;
    		} else { // 주문 수량이 재고보다 적거나 같은 경우
    			cd.insertCart(c);
            	msg = "장바구니에 추가했습니다.";
            	url = "cart/cartList";
    		}
        } else {
        	cd.checkUpdateCart(quantity, id, prodnum);
        	msg = "이미 장바구니에 존재하는 상품입니다.";
            url = "cart/cartList";
        }
    	
    	request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
		return "alert";
	}
	
	@RequestMapping("cartList")
	@Login("COLoginUser")
	public String cartList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
    	String id = (String)session.getAttribute("id");
    	
    	CartMybatis cd = new CartMybatis();
    	List<Cart> li = cd.cartList(id); // 세션에 로그인 된 ID의 모든 장바구니 데이터 불러오기
    	Cart cart = new Cart();
    	MemberMybatis md = new MemberMybatis(); // ???님의 장바구니에서 name을 띄우기 위함
    	Member member = md.oneMember(id);
    	
    	request.setAttribute("member", member);
    	request.setAttribute("li", li);
		request.setAttribute("id", id);
		return "cartList";
	}
	
	@RequestMapping("cartUpdate")
	@Login("COLoginUser")
	public String cartUpdate(HttpServletRequest request, HttpServletResponse response) {
		String msg = "오류가 발생했습니다.";
		String url = "cart/cartList";
		
		try {
			request.setCharacterEncoding("utf-8");
			
			CartMybatis cd = new CartMybatis();
			Cart c = new Cart();
			int cartid = Integer.parseInt(request.getParameter("cartid"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			c.setCartid(cartid);
			c.setQuantity(quantity);
			Cart cart = cd.cartOne(cartid);
			int prodnum = cart.getProdnum();
			ProductDao pd = new ProductDao();
			Product product = pd.productOne(prodnum);
			int stock = product.getStock();
			
			if (stock < quantity) {
				msg = "해당 상품의 재고보다 수량이 많습니다.";
				url = "cart/cartList";
			} else {
				cd.cartUpdate(c);
				msg = "수정되었습니다.";
				url = "cart/cartList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
		return "alert";
	}
	
	@RequestMapping("cartDelete")
	@Login("COLoginUser")
	public String cartDelete(HttpServletRequest request, HttpServletResponse response) {
		String msg = "오류가 발생했습니다.";
		String url = "cart/cartList";
		
		CartMybatis cd = new CartMybatis();
		Cart c = new Cart();
		int cartid = Integer.parseInt(request.getParameter("cartid"));
		c.setCartid(cartid);
		if (cd.CartDelete(cartid) > 0) {
			msg = "해당 상품이 장바구니에서 삭제되었습니다.";
			url = "cart/cartList";
		} else {
			msg = "해당 상품을 장바구니에서 삭제하는데 실패했습니다.";
			url = "cart/cartList";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

}
