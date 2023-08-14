<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물: ${board.num}</title>
</head>
<body>
	<div class="container">
		<table class="table">
			<tr>
				<td>글 번호</td>
				<td>${board.num}</td>
				<td>조회수</td>
				<td>${board.readcnt}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.name}</td>
				<td>작성일</td>
				<td><fmt:formatDate value="${board.regdate}" var="dateValue" pattern="yyyy-MM-dd"/>${dateValue}</td>
			</tr>
			<tr>
				<td>글제목</td>
				<td colspan="3">${board.subject}</td>
			</tr>
			<tr>
				<td>첨부사진</td>
				<td colspan="3">	
				<!-- 사진업로드 안할시 이미지깨짐?안나오게 -->
				<c:choose><c:when test="${board.image eq null}"><c:out value="없음" /></c:when>
				<c:otherwise>
				<img class="mb-3 text-center" alt="첨부사진"
					src="${pageContext.request.contextPath}/view/board/images/${board.image}" width="100%"/></c:otherwise></c:choose>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">
				${board.content}
				</td>
			</tr>
		
		
		<tr class="text-end" style="border-bottom: double 3px grey;">
			<td colspan="12">
			<button type="button" class="btn btn-outline-dark"
			onclick="location.href='${pageContext.request.contextPath}/board/boardUpdateForm?num=${board.num}'">
				<i class="fa-solid fa-pencil"></i> 수정
			</button>
			<button type="button" class="btn btn-outline-danger"
			onclick="location.href='${pageContext.request.contextPath}/board/boardDeleteForm?num=${board.num}'">
				삭제
			</button>
			<button type="button" class="btn btn-outline-primary"
			onclick="location.href='${pageContext.request.contextPath}/board/boardList?boardid=${boardid}'">
				돌아가기
			</button>
			</td>
		</tr>
		</table>
		
		<table class="table table-borderless">
		<tr>
			<!-- 세션에 로그인이 되어 있지 않으면 댓글을 남길 수 없음 -->
			<% if (session.getAttribute("id")==null) { %>
			<td colspan="3">로그인한 사용자만 댓글을 남길 수 있습니다.</td>
			<% } else { %>
			<td colspan="3" class="center">
			<textarea rows="1" class="form-control"
			cols="50" name="content" id="comment" placeholder="댓글을 입력하세요"></textarea></td>
			<td><input type="button" class="btn btn-outline-secondary"
					onclick="commentPro('${board.num}')" value="입력"></td>
			<% } %>
			
			<tr>
				<td colspan="2">내용</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
			<c:forEach var="c" items="${commentLi}">
			<tr>
				<td colspan="2">${c.content}</td>
				<td>${c.name}</td>
				<td><fmt:formatDate value="${board.regdate}" var="dateValue" pattern="yyyy-MM-dd"/>${dateValue}</td>
				<td>
					<form action="${pageContext.request.contextPath}/board/boardCommentDeleteForm?ser=${c.ser}"
					name="del" method="post" >
						<input type="hidden" name="ser" value="${ser}">
						<button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
					</form>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<script>
	function commentPro(num) {
		let comment = document.querySelector("#comment").value
		console.log(comment)
		// $.get(url, function() {} )
		if (comment.trim() != "") {
			$.get(
					"${pageContext.request.contextPath}/board/boardCommentPro?comment="
							+ comment + "&num=" + num, function(data, state) {
						console.log("Data: " + data + "\nStatus: " + state);
						let commentList = document.querySelector("#commentList")
						commentList.innerHTML = data + commentList.innerHTML
						console.log(commentList.innerHTML)
					})
			document.querySelector("#comment").value = ''
		} else {
			alert ("댓글 내용을 입력해주세요.")
		}
		
	}
	
	
	</script>         
</body>
</html>