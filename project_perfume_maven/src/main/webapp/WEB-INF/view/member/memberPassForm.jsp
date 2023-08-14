<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호수정</title>
</head>
<script>
	function passChk(form) {
		//alert("ok")
		if (form.chgpass1.value != form.chgpass2.value) {
			alert("변경 비밀번호와 재입력 번호가 다릅니다")
			form.chgpass2.value = ""
			form.chgpass2.focus()
			return false;
		} else {
			return true;
		}
	}
</script>

<script>
			alert("로그인을 하세요")
			location.href = "loginForm.jsp";
</script>

<body>
	<br>
	<br>
	<br>
	<div class="container mt-3">
		<h2 align="center">비밀번호수정</h2>
	</div>
	<br>
	<div class="container mt-3">
		<form class="container" name="f"
			action="${pageContext.request.contextPath}/member/memberPassPro" method="post" onsubmit="return passChk(this)">

			<p>
				<label>현재 비밀번호</label> <input class="form-control" type="password" name="pass" >
			</p>
			<p>
				<label>변경 비밀번호</label> <input class="form-control" type="password" name="chgpass1" >
			</p>
			<p>
				<label>변경 비밀번호 재확인</label> <input class="form-control" type="password" name="chgpass2" >
			</p><br><br>
			<p>
				<input class="form-control" type="submit" value="비밀번호 변경"> </p>
		
		  </form>

	</div>
</body>
</html>