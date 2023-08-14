package controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.Cart;
import cart.CartMybatis;
import kic.mskim.Login;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import member.Member;
import member.MemberMybatis;
import order.Order;
import order.OrderItem;
import order.OrderMybatis;
import product.Product;
import product.ProductDao;
import product.ProductMybatis;

@WebServlet(urlPatterns = { "/order/*" }, initParams = { @WebInitParam(name = "view", value = "/view/order/"), // jsp 위치
		@WebInitParam(name = "login", value = "login") }) // package

public class OrderController extends MskimRequestMapping {

	@RequestMapping("order")
	@Login("COLoginUser")
	public String order(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String[] chk = request.getParameterValues("chk");

		MemberMybatis md = new MemberMybatis(); // ???님의 주문서
		Member member = md.oneMember(id);

		ProductMybatis pd = new ProductMybatis();
		CartMybatis cd = new CartMybatis();
		Cart cart = new Cart();
		List<Product> li = new ArrayList<Product>();
		List<List<Product>> list = new ArrayList();
		List<Integer> quantityList = new ArrayList();

		int quantity = 0;
		int prodnum = 0;

		request.setAttribute("member", member);

		if (chk == null) { // 상품 페이지에서 바로 주문한 경우
			prodnum = Integer.parseInt(request.getParameter("prodnum"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
			li = pd.orderProductList(prodnum);
			Product product = pd.productOne(prodnum);
			request.setAttribute("li", li);
			request.setAttribute("quantity", quantity);
			if (product.getStock() < quantity) { // 주문 수량이 더 많은 경우
				String msg = "주문하시려는 수량이 재고보다 많습니다.";
				String url = "product/productDetail?prodnum=" + prodnum;
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return "alert";
			} else {
				System.out.println(li);
				return "order";
			}
		} else { // 장바구니에서 주문한 경우
			for (String s : chk) {
				int cartid = Integer.parseInt(s);
				cart = cd.cartOne(cartid);
				quantity = cart.getQuantity(); // 체크된 상품의 quantity
				prodnum = cart.getProdnum(); // 체크된 상품의 prodnum
				li = pd.orderProductList(prodnum);
				Product product = pd.productOne(prodnum);
				list.add(li);
				if (product.getStock() < quantity) { // 주문 수량이 해당 상품의 재고보다 많은 경우
					String msg = "주문하시려는 " + product.getName() + "상품의 수량이 재고보다 많습니다.";
					String url = "cart/cartList";
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					return "alert";
				} else {
					quantityList.add(quantity);
				}

			}

			request.setAttribute("list", list);
			request.setAttribute("quantityList", quantityList);
			System.out.println(list);
			System.out.println(quantityList);
			return "order";
		}
	}

	@RequestMapping("orderPro")
	@Login("COLoginUser")
	public String orderPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String msg = "";
		String url = "";

		OrderMybatis od = new OrderMybatis();
		Order order = new Order();
		OrderItem orderItem = new OrderItem();
		// 주문 번호 만들기 용
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddHHmmss");
		String ordernum = id + format.format(date);
		// 주문 완료 시 장바구니 삭제
		CartMybatis cd = new CartMybatis();
		Cart cart = new Cart();
		// 재고 update 용
		ProductMybatis pd = new ProductMybatis();

		order.setId(id);
		order.setName(request.getParameter("name"));
		order.setTel(request.getParameter("tel"));
		order.setZipcode(request.getParameter("zipcode"));
		order.setAddress(request.getParameter("address"));
		order.setResult(1);
		order.setOrdernum(ordernum); // 주문 번호 (id + 년도월일시분초)

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (request.getParameterValues("multi-prodnum") == null) { // 상품 페이지 -> 주문서인 경우
			int prodnum = Integer.parseInt(request.getParameter("single-prodnum"));
			int quantity = Integer.parseInt(request.getParameter("single-quantity"));
			int price = Integer.parseInt(request.getParameter("single-price"));
			int totalPrice = Integer.parseInt(request.getParameter("single-total-price"));

			if (totalPrice > 50000) { // 5만원 이상인 경우 배송비 무료
				order.setDelivercost(0);
			} else {
				order.setDelivercost(2500);
			}

			orderItem.setProdnum(prodnum);
			orderItem.setPrice(price);
			orderItem.setQuantity(quantity);

			if (od.insertOrder(order) > 0) {
				orderItem.setOrdernum(order.getOrdernum());
				od.insertOrderItem(orderItem);
				// 재고 Update
				Product p = pd.productOne(prodnum);
				int stock = p.getStock() - quantity;
				p.setStock(stock);
				pd.stockUpdate(p);
				msg = "주문이 완료되었습니다.";
				url = "order/orderResult?ordernum=" + ordernum;
			} else {
				msg = "오류가 발생했습니다.";
				url = "product/productDetail?prodnum=" + prodnum;
			}
		} else { // 장바구니 -> 주문서인 경우
			String[] multiProdnum = request.getParameterValues("multi-prodnum");
			String[] multiQuantity = request.getParameterValues("multi-quantity");
			String[] multiPrice = request.getParameterValues("multi-price");
			String[] multiTotalPrice = request.getParameterValues("multi-total-price");

			int finalTotalPrice = 0;
			for (int i = 0; i < multiTotalPrice.length; i++) {
				finalTotalPrice += Integer.parseInt(multiTotalPrice[i]);
			}

			if (finalTotalPrice > 50000) { // 5만원 이상인 경우 무료배송
				order.setDelivercost(0);
			} else {
				order.setDelivercost(2500);
			}

			if (od.insertOrder(order) > 0) {
				for (int i = 0; i < multiProdnum.length; i++) {
					orderItem.setProdnum(Integer.parseInt(multiProdnum[i]));
					orderItem.setPrice(Integer.parseInt(multiPrice[i]));
					orderItem.setQuantity(Integer.parseInt(multiQuantity[i]));
					orderItem.setOrdernum(order.getOrdernum());
					od.insertOrderItem(orderItem);
					// 장바구니에서 삭제
					cart.setProdnum(Integer.parseInt(multiProdnum[i]));
					cart.setId(id);
					cd.orderedCartDelete(cart);
					// 재고 update
					Product p = pd.productOne(Integer.parseInt(multiProdnum[i]));
					int stock = p.getStock() - Integer.parseInt(multiQuantity[i]);
					p.setStock(stock);
					pd.stockUpdate(p);
				}
				msg = "주문이 완료되었습니다.";
				url = "order/orderResult?ordernum=" + ordernum;
			} else {
				msg = "오류가 발생했습니다.";
				url = "cart/cartList";
			}
		}

		request.setAttribute("ordernum", ordernum);
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("orderList")
	@Login("COLoginUser")
	public String orderList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		MemberMybatis md = new MemberMybatis(); // ???님의 주문내역
		Member member = md.oneMember(id);

		OrderMybatis od = new OrderMybatis();
		List<Order> li = od.orderList(id); // 세션에 로그인 된 ID의 주문내역

		request.setAttribute("li", li);
		request.setAttribute("member", member);
		return "orderList";
	}

	@RequestMapping("orderDetail")
	@Login("COLoginUser")
	public String orderDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String ordernum = "";

		try {
			ordernum = request.getParameter("ordernum");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(ordernum);

		OrderMybatis od = new OrderMybatis();
		List<OrderItem> li = od.orderItemList(ordernum);
		Order order = od.orderOne(ordernum);

		// 상품 이름 찍기
		int prodnum = 0;
		String prodname = "";
		List<String> prodnameLi = new ArrayList<String>(); // 상품 이름 리스트
		List<Integer> prodnumLi = new ArrayList<Integer>(); // 상품 prodnum 리스트 (해당 상품 페이지로 이동 가능하게)
		ProductMybatis pd = new ProductMybatis();
		for (int i = 0; i < li.size(); i++) {
			prodnum = li.get(i).getProdnum();
			Product product = pd.productOne(prodnum);
			prodnumLi.add(prodnum);
			prodname = product.getName();
			prodnameLi.add(prodname);
		}

		request.setAttribute("id", id);
		request.setAttribute("order", order);
		request.setAttribute("ordernum", ordernum);
		request.setAttribute("prodnumLi", prodnumLi);
		request.setAttribute("prodnameLi", prodnameLi);
		request.setAttribute("li", li);
		return "orderDetail";
	}

	@RequestMapping("orderCancelForm")
	@Login("COLoginUser")
	public String orderCancelForm(HttpServletRequest request, HttpServletResponse response) {
		String ordernum = request.getParameter("ordernum");

		request.setAttribute("ordernum", ordernum);
		return "orderCancelForm";
	}

	@RequestMapping("orderCancelPro")
	@Login("COLoginUser")
	public String orderCancelPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String ordernum = request.getParameter("ordernum");
		String msg = "";
		String url = "";

		OrderMybatis od = new OrderMybatis();
		Order order = new Order();
		List<OrderItem> li = od.orderItemList(ordernum);
		int prodnum = 0;
		int quantity = 0;

		List<Integer> prodnumLi = new ArrayList<Integer>(); // 상품 prodnum 리스트 (해당 상품 페이지로 이동 가능하게)
		List<Integer> quantityLi = new ArrayList<Integer>(); // 수량 리스트
		ProductMybatis pd = new ProductMybatis();
		for (int i = 0; i < li.size(); i++) {
			prodnum = li.get(i).getProdnum();
			quantity = li.get(i).getQuantity();
			prodnumLi.add(prodnum);
			quantityLi.add(quantity);
		}

		// 재고 update 용
		order.setOrdernum(ordernum);
		// 아이디 검사용
		Order orderOne = od.orderOne(ordernum);

		if (id.equals(orderOne.getId())) {
			// result 3: 주문 취소
			order.setResult(3);
			// 7/31까지 작업.. 재고 update되게 수정해야함
			if (od.orderUpdate(order) > 0) {
				for (int i = 0; i < li.size(); i++) {
					Product p = pd.productOne(prodnumLi.get(i));
					int stock = p.getStock() + quantityLi.get(i);
					p.setStock(stock);
					pd.stockUpdate(p);
				}
				msg = "주문이 취소되었습니다.";
				url = "order/orderList";
			} else {
				msg = "오류가 발생했습니다.";
				url = "order/orderDetail?ordernum=" + ordernum;
			}
		} else {
			msg = "해당 주문을 하신 유저만 주문 취소를 하실 수 있습니다.";
			url = "home/index";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("orderResult")
	@Login("COLoginUser")
	public String orderResult(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String ordernum = request.getParameter("ordernum");

		request.setAttribute("ordernum", ordernum);
		return "orderResult";
	}

	@RequestMapping("orderConfirm")
	@Login("COLoginUser")
	public String orderConfirm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String ordernum = request.getParameter("ordernum");
		OrderMybatis od = new OrderMybatis();
		Order order = od.orderOne(ordernum);
		String msg = "";
		String url = "";

		if (id.equals(order.getId())) {
			order.setResult(5); // 5: 주문 확정
			if (od.orderUpdate(order) > 0) {
				msg = "주문이 확정되었습니다.";
				url = "order/orderList";
			}
		} else {
			msg = "해당 주문을 하신 유저만 주문 확정이 가능합니다.";
			url = "home/index";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("orderManagement")
	@Login("OrderLoginAdmin")
	public String orderManagement(HttpServletRequest request, HttpServletResponse response) {
		OrderMybatis od = new OrderMybatis();
		List<Order> li = od.orderListAdmin();
		List<Integer> resultLi = new ArrayList<Integer>();
		int result = 0;

		for (int i = 0; i < li.size(); i++) {
			result = li.get(i).getResult();
			resultLi.add(result);
		}

		request.setAttribute("resultLi", resultLi);
		request.setAttribute("li", li);
		return "orderManagement";
	}

	@RequestMapping("orderStateUpdate")
	@Login("OrderLoginAdmin")
	public String orderStateUpdate(HttpServletRequest request, HttpServletResponse response) {
		OrderMybatis od = new OrderMybatis();
		String msg = "";
		String url = "";

		String ordernum = request.getParameter("ordernum");
		Order order = od.orderOne(ordernum);
		int result = Integer.parseInt(request.getParameter("result"));
		order.setResult(result);

		if (od.orderUpdate(order) > 0) {
			System.out.println(result);
			msg = "주문 상태 수정이 완료되었습니다.";
			url = "order/orderManagement";
		} else {
			msg = "오류가 발생했습니다.";
			url = "order/orderManagement";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

}
