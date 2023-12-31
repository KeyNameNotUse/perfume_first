<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>회원가입</title>

<!-- 회원가입 시 우편번호 주소-->

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}
	
	<!-- 회원가입 시 아이디 중복확인 -->
	
    function checkDuplicateId() {
    	//alert("ok")
        var id = document.getElementById("id").value;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var response = xhr.responseText;
                    
                    alert(response)
                } else {
                    console.error(xhr.status);
                }
            }
        };
        var url = "${pageContext.request.contextPath}/member/checkDuplicateId";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("id=" + id);
    }
	
    <!-- 회원가입 시 아이디 4글자 이상 경고문구 -->
    
	function Validation() {
    	var id = document.getElementById("id")

		if(id.value.length <4){
    	alert("아이디를 4글자 이상 입력하세요.")
    	id.focus();
    	return false;
		}
}
</script>

</head>
<body>
	<br>
	<br>
	<br>
	<div class="container mt-3">
		<h2 align="center">회원가입</h2>
		</div><br>
		<div class="container mt-3">
		    <form class="container" name="f" action="${pageContext.request.contextPath}/member/joinPro" method="post" onsubmit="return blankchk();">
						<div class="mb-3">
						<label>아이디  (4자 이상)</label>
						<input class="form-control" type="text" name="id" id="id" placeholder="Id" min="4" onblur="checkDuplicateId()"> </div>
						
						<div class="mb-3">
						<label>비밀번호</label>
						<input class="form-control" type="password" name="pass" placeholder="Password" id="pass"> </div>
						
						<div class="mb-3">
						<label>이름</label> 
						<input class="form-control" type="text" name="name" placeholder="Username" id="name"> </div>
						
						<div class="mb-3">
						<p class="mb-0"><label>성별</label></p>
						<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="1" checked> 남 </div> 
						<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="2">여 </div> </div>
						
						<div class="mb-3">
						<label>전화번호</label>
						<input class="form-control" type="text" name="tel" placeholder="Phone Number" id="tel"></div>
						
						<div class="mb-3">
						<label>이메일</label> 
						<input class="form-control" type="text" name="email" placeholder="example@hotmail.com" id="email">  </div>
						
						<div class="mb-3">
						<p class="mb-0"><label>주소</label></p>
						<div class="row">
    					<div class="col">
						<input class="form-control" type="text" id="sample4_postcode" name="zipcode" placeholder="우편번호" readonly> </div>
						<div class="col">
						<input type="button" class="btn btn-outline-secondary" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"> </div> </div> </div>
						
						<div class="mb-3">
						<div class="row">
    					<div class="col">
						<input class="form-control" type="text" id="sample4_roadAddress" name="address" placeholder="도로명주소" readonly> </div>
						<div class="col">
						<input class="form-control" type="text" id="sample4_jibunAddress" placeholder="지번주소"> </div> </div> </div>
						<span id="guide" style="color: #999; display: none"></span> 
						<div class="mb-3">
						<div class="row">
    					<div class="col">
						<input class="form-control" type="text" id="sample4_detailAddress" placeholder="상세주소"> </div>
						<div class="col">
						<input class="form-control" type="text" id="sample4_extraAddress" placeholder="참고항목" readonly> </div> </div> </div>
						<div class="mt-5">
						<input class="form-control" type="submit" value="회원가입"> </div>
</form>
</div>
<script>

	<!-- 빈칸 체크 -->

	function blankchk() {
	if(document.getElementById( 'id' ).value.trim() == "") {
    	alert("아이디는 필수 입력 값입니다.")
      	return false;
	}
    if(document.getElementById( 'pass' ).value.trim() == "") {
        alert("비밀번호는 필수 입력 값입니다.")
        return false;
    }
    if(document.getElementById( 'name' ).value.trim() == "") {
        alert("이름은 필수 입력 값입니다.")
        return false;
    }
	if(document.getElementById( 'tel' ).value.trim() == "") {
        alert("전화번호는 필수 입력 값입니다.")
        return false;
	}
    if(document.getElementById( 'email' ).value.trim() == "") {
        alert("이메일은 필수 입력 값입니다.")
        return false;
    }
    if(document.getElementById( 'sample4_postcode' ).value.trim() == "") {
        alert("우편번호는 필수 입력 값입니다.")
        return false;
    }
    if(document.getElementById( 'sample4_roadAddress' ).value.trim() == "") {
        alert("주소는 필수 입력 값입니다.")
        return false;
    }
    	else return true;
    };
</script>
</body>
</html>