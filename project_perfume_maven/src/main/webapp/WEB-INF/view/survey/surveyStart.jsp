<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 페이지</title>
<style>
.recommend img {
	width: 300px;
	height: 300px;
	margin-bottom: 10px;
}

.recommend {
	text-align: center;
}

.recommend a {
	display: block;
	text-decoration: none;
	color: black;
}

.recommend p {
	margin-top: 10px;
	font-size: 20px;
}

.title {
	background-color: rgba(230, 230, 230, 0.4);
	margin: auto;
	border-radius: 20px;
	padding: 25px;
	width: 900px;
}

</style>
</head>
<body
	style="background-image: url('${pageContext.request.contextPath}/images/surveyback/svback02.jpg'); 
	background-size: cover;">

	<div class="recommend h1 fw-bold" style="margin-top: 5%; text-align: center;">
		<p class="title fs-2">당신에게 맞는 최고의 향수를 찾아드립니다.</p>
	</div>
	<div class="d-flex justify-content-center " style="text-align: center;">
		<div class="" style="width: 250px;  background-color: rgb(230,230,230,0.5)" >
			<canvas id="maleChart" width="" height=""></canvas>
			<p class="fs-4">남성 선호</p>
		</div>
		<div class="" style="width: 250px; background-color: rgb(230,230,230,0.5)">
		<canvas id="femaleChart" width="" height=""></canvas>
			<p class="fs-4">여성 선호</p>
		</div>
	</div>
	<div class="btnbox" style="text-align: center;display: block; margin-top: 100px;">
		<div class="btn-group-vertical"
			style="margin-top: 0px; width: 400px;">
			<a href="${pageContext.request.contextPath}/survey/survey01" type="button"
				class="btn btn-dark p-4 fs-3 fw-bold"><p>설문 시작하기</p>
			</a> 
			
			<a	href="${pageContext.request.contextPath}/home/index" type="button" 
				class="btn btn-outline-secondary p-3 fs-5 fw-bold link-dark ">
				<p>홈페이지로 돌아가기</p>
			</a> 

	</div>
   </div>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
		crossorigin="anonymous">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<!-- 통계차트 chart.js cdn --> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<script>
	const ctx1 = document.getElementById('maleChart').getContext('2d');
	const ctx2 = document.getElementById('femaleChart').getContext('2d');
	
	const maleChart = new Chart(ctx1, {
    type: 'doughnut',
    data: {
        labels: ['꽃', '과일', '나무'],
        datasets: [{
            label: '# of Votes',
            data:  [ '${MaleD.get(0).doughnut}','${MaleD.get(1).doughnut}','${MaleD.get(2).doughnut}'
            	
            ],
            backgroundColor: [
                'rgba(75, 192, 192, 0.4)',
                'rgba(255, 99, 132, 0.4)',
                'rgba(88, 88, 88, 0.4)',
            ],
            borderColor: [
                'rgba(75, 192, 192, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(88, 88, 88, 0.5)',
            ],
        	    borderWidth: 1
     	   }]
 	   },
    options: {
        scales: {
            y: {
                beginAtZero: true
    	        }
   	     }
  	  }
	});
	const femaleChart = new Chart(ctx2, {
	    type: 'doughnut',
	    data: {
	        labels: ['꽃', '과일', '나무'],
	        datasets: [{
	            label: '# of Votes',
	            data: ['${FemaleD.get(0).doughnut}', '${FemaleD.get(1).doughnut}', '${FemaleD.get(2).doughnut}'],
	            backgroundColor: [
	                'rgba(75, 192, 192, 0.4)',
	                'rgba(255, 99, 132, 0.4)',
	                'rgba(88, 88, 88, 0.4)',
	            ],
	            borderColor: [
	                'rgba(75, 192, 192, 1)',
	                'rgba(255, 99, 132, 1)',
	                'rgba(88, 88, 88, 0.5)',
	            ],
	        	    borderWidth: 1
	     	   }]
	 	   },
	    options: {
	        scales: {
	            y: {
	                beginAtZero: true
	    	        }
	   	     }
	  	  }
		});
</script>
</body>
</html>