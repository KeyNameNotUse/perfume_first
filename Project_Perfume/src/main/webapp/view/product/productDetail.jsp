<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="product.Product"%>
<%@page import="product.ProductDao"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.name}</title>
</head>

<body>

	<div class="container">
		<div class="card mb-3" style="max-width: 1500px;">
			<div class="row g-0">
				<div class="col-md-4">
					<img
						src="${pageContext.request.contextPath}/view/product/images/${product.image}"
						class="img-fluid rounded-start" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">${product.name}</h5>
						<p>
							<b>상품번호</b> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							${product.prodnum}
						</p>
						<p>
							<b>재고 수 </b> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<fmt:formatNumber value='${product.stock}' pattern="#,### 개" />
						</p>
						<h4>
							<fmt:formatNumber value='${product.price}' pattern="#,### 원" />
						</h4>
						<hr>
						<p>
							<b>배송비 </b>
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 2,500원
							(50,000원 이상 구매 시 무료)
						<p>
							<b>출고예정일</b> &nbsp&nbsp&nbsp&nbsp&nbsp 2시 이전 당일출고(주말,공휴일 제외)
						<p>
							<b>배송방법 </b> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 퍼퓸컴퍼니 / 본사
							검수 및 직배송 / CJ대한통운
						<div class="quantity-div-parent">
							<div class="quantity-div" style="display: inline-block;">
								<input type="text" value="1"
									style="border: radius; outline: none; width: 50px;"
									class="text-center">
								<button class="plus-btn btn btn-sm btn-outline-primary">+</button>
								<button class="minus-btn btn btn-sm btn-outline-success">-</button>
							</div>
							<a class="order-btn btn btn-info">상품주문</a> 
							<a class="cart-btn btn btn-secondary">장바구니</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="detail">
		<div class="text-center">
			<img
				src="${pageContext.request.contextPath}/view/product/images/${product.detail}"
				class="rounded" alt="...">
		</div>
	</div>
	<div class="deliver">
		<div class="text-center">
			<img
				src="${pageContext.request.contextPath}/view/product/images/상품구매안내.png"
				class="rounded" alt="...">
		</div>
	</div>

	<!-- 장바구니 form -->
	<form action="${pageContext.request.contextPath}/cart/cartPro"
		method="post" class="cart-form">
		<input type="hidden" name="prodnum" value="${product.prodnum}"}> 
		<input type="hidden" name="quantity" class="input-quantity"> 
		<input type="hidden" name="prodname" value="${product.name}"> 
		<input type="hidden" name="price" value="${product.price}">
	</form>
	
	<!-- 주문하기 form -->
	<form action="${pageContext.request.contextPath}/order/order"
		method="post" class="order-form">
		<input type="hidden" name="prodnum" value="${product.prodnum}">
		<input type="hidden" name="quantity" class="order-quantity">
	</form>
	
	
	<script>
		/* 수량버튼 */
		$(".plus-btn").on("click", function() {
			let quantity = $(this).parent("div").find("input").val();
			$(this).parent("div").find("input").val(++quantity);
		});
		$(".minus-btn").on("click", function() {
			let quantity = $(this).parent("div").find("input").val();
			if (quantity > 1) {
				$(this).parent("div").find("input").val(--quantity);
			}
		});

		/* 장바구니 버튼 */
		$(".cart-btn").on("click", function() {
			let quantity = $(this).parent("div").find("input").val();
			$(".input-quantity").val(quantity);
			$(".cart-form").submit();
		});
		
		/* 주문하기 버튼 */
		$(".order-btn").on("click", function() {
			let quantity = $(this).parent("div").find("input").val();
			$(".order-quantity").val(quantity);
			$(".order-form").submit();
		});
	</script>
</body>
</html>