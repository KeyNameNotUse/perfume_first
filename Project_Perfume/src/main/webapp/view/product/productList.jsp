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
<title>Document</title>
<style>
li {
	list-style-type: none;
}

.subdivision a {
	color: black;
}

.subdivision a:hover {
	text-decoration: underline;
}

</style>
</head>

<body>
	<div class="text-center mb-2">
		<h2>전체 제품</h2>
	</div>

	<!-- 상품 정렬 -->
	<div class="array mb-3 text-center subdivision">
		<p>
			<a href="#none" style="font-weight: bold;">최신순</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
			<a href="#none">인기순</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp <a
				href="#none">후기순</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp <a href="#none">낮은가격</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp

			<a href="">높은가격</a>
		</p>
	</div>

	<!-- 제품 이미지 -->
	<div class="product text-center  flex-wrap:wrap"
		style="width: 81%; margin: auto;">

		<c:forEach var="p" items="${li}">
			<ul class="d-inline-flex flex-sm-column m-auto p-5">
				<li class="mt-3">
					<div class="card" style="width: 18rem;">
						<a
							href="${pageContext.request.contextPath}/product/productDetail?prodnum=${p.prodnum}">
							<img
							src="${pageContext.request.contextPath}/view/product/images/${p.image}"
							class="card-img-top" alt="...">
						</a>
						<div class="card-body container text-center">
							<h5 class="card-title">${p.name}</h5>
							<p class="card-text">
								<fmt:formatNumber value='${p.price}' pattern="#,### 원" />
							</p>
						</div>
						<ul class="list-group list-group-flush">
						</ul>
						<div class="card-body container text-center">
							<a href="#" class="card-link"> 
							<img src="${pageContext.request.contextPath}/images/shop/하트.png"
								alt="" width="30px"></a> 
							<a href="#" class="card-link cart-btn" data-prodnum="${p.prodnum}" 
							data-prodname="${p.name}" data-price="${p.price}"> 
							<img src="${pageContext.request.contextPath}/images/shop/장바구니.png"
								alt="" width="30px"></a>
						</div>
					</div>
				</li>

			</ul>
		</c:forEach>
	</div>
	<!-- 상품 페이지 표시 -->
	<br>
	<div class="text-center">
		<!-- 이전 버튼 -->
		<c:if test="${start < bottomLine}">
			<a href="#" class="btn btn-sm btn-outline-danger disabled">이전</a>
		</c:if>
		<c:if test="${start > bottomLine}">
			<a
				href="${pageContext.request.contextPath}/product/productList?pageNum=${start-bottomLine}"
				class="btn btn-sm btn-outline-primary">이전</a>
		</c:if>

		<!-- 현재 페이지 표시 -->
		<c:forEach var="p" begin="${start}" end="${end}">
			<a
				href="${pageContext.request.contextPath}/product/productList?pageNum=${p}"
				class="btn <c:if test="${pageInt==p}"> btn-sm btn-outline-secondary </c:if> ml-3 mr-3">${p}</a>
		</c:forEach>

		<!-- 다음 버튼 -->
		<c:if test="${end >= maxPage}">
			<a href="#" class="btn btn-sm btn-outline-danger disabled">다음</a>
		</c:if>
		<c:if test="${end <  maxPage}">
			<a
				href="${pageContext.request.contextPath}/product/productList?pageNum=${start+bottomLine}"
				class="btn btn-sm btn-outline-primary">다음</a>
		</c:if>
	</div>

	<!-- 장바구니 form -->
	<form action="${pageContext.request.contextPath}/cart/cartPro"
		method="post" class="cart-form">
		<input type="hidden" name="prodnum" class="current-prodnum"> <input
			type="hidden" name="price" class="current-price"> <input
			type="hidden" name="prodname" class="current-prodname"> <input
			type="hidden" name="quantity" value="1">
	</form>


	<script>
		/* 장바구니 버튼 */
		$(".cart-btn").on("click", function() {
			let prodnum = $(this).data("prodnum");
			let prodname = $(this).data("prodname");
			let price = $(this).data("price");
			$(".current-prodnum").val(prodnum);
			$(".current-prodname").val(prodname);
			$(".current-price").val(price);
			$(".cart-form").submit();
		});
	</script>
</body>
</html>