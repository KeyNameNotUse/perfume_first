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
<title>상품 관리 페이지</title>
<style>
li {
	list-style-type:none;
}
</style>
</head>

<body>


		<!-- 제품 이미지 -->
		
 <div class="product text-center  flex-wrap:wrap" style="width:81%; margin:auto;">
 
     <div class="text-lg-end text-center container d-flex justify-content-center">
		<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/product/productForm" >상품 등록</a>
		</div>		      
            
 	<c:forEach var="p" items="${ma}">
             <!-- C: 반복문 사용 !! -->
 <!--나누기-->
 <ul class="d-inline-flex flex-sm-column m-auto p-5">
 <li class="mt-3">
 	<div class="col-lg-3 com-md-6" style="border-radius: 5px;">
    <div class="card" style="width: 18rem;">
    	<a href="${pageContext.request.contextPath}/product/productDetail?prodnum=${p.prodnum}">
        <img src="${pageContext.request.contextPath}/view/product/images/${p.image}"  class="card-img-top" alt="..." width="200px" height="200px"></a>
        <div class="card-body">
          <h5 class="card-title" style="text-align: center;color: black;">
          <a href="${pageContext.request.contextPath}/product/productDetail?prodnum=${p.prodnum}" style="text-decoration: none; color: black">${p.name}</a>
          </h5>
         
          
            			<!-- 상품 편집 -->
                                  
                                 
                                    
                        <a href="${pageContext.request.contextPath}/product/productUpdateForm?prodnum=${p.prodnum}">          
                        <button class="btn btn-outline-dark" type="button" onclick="" style="float: center;" >
                        수정
                        </button> <!-- 상품 수정탭으로 이동 -->
                        
                        </a>
                        
                        <a href="${pageContext.request.contextPath}/product/productDeleteForm?prodnum=${p.prodnum}">          
                        <button class="btn btn-outline-dark" type="button" onclick="" style="float: center;" >
                        삭제
                        </button><!-- 상품 삭제탭으로 이동 -->
                        
                        </a>
                                                  
                                    
                                   
                                    <!-- 편집기능끝 -->
                                    <hr/>
                                    
                                    <!-- 상품태그 -->
          <p class="card-text" style="text-align: center;">
            <span class="badge bg-dark">
   			
            <c:if test="${p.prodgender eq 1}">  male </c:if>
            <c:if test="${p.prodgender eq 2}">  female </c:if>
            </span>  
            <span class="badge bg-dark">${p.prodans1}</span>
            <span class="badge bg-dark">${p.prodans2}</span>
          </p>
          <div style="text-align: center;">
   
              </div>
            <div class="text-dark" style="text-align: center;">&nbsp;<strong><fmt:formatNumber value='${p.price}' pattern="#,### 원" /></strong></div>
          </div>
          
        </div>
      </div>       
      </li>
      </ul>     
       </c:forEach>     
</div>

</body>
</html>