<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean"
	rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/frame.css" />
<style type="text/css">
#menu>ul {
	width: 610px;
	list-style-type: none;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content>button').hide().eq(0).show().click(function() {
			$('#content>button').show().eq(0).hide();
		});
		$('#sb').change(function(){
			var state=$('#sb option:selected').val();
			if(state=='day'){
				alert('day');
				$(location).attr('href', 'attendance.tea')
			}else if(state=='month'){
				alert('month');
				$(location).attr('href', 'attendance_month_T.tea')
			}
		});
	});
	
</script>
</head>
<body>
	<div>
		<div id="header">
			<a href="#">logout</a> <img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
			<ul>
				<li class="topmenu"><a href="#">학생관리</a>
					<ul class="submenu">
						<li><a href="attendance.tea">출결관리</a></li>
						<li><a href="score.tea">성적관리</a></li>
						<li><a href="assignment.tea">과제관리</a></li>
					</ul></li>
				<li><a href="qna.tea">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<h2>월별 출석 현황</h2>
			<select name="sb" id="sb">
				<option value="" selected="selected">전체</option>
				<option value="day" id="day">day</option>
				<option value="month" id="month">month</option>
			</select>
			<table border="1">
				<thead>
					<tr>
						<th>이름</th>
						<%
							int i = 1;
							for (i = 1; i <= 31; i++) {
						%>
						<td><%=i%>
						<td>
							<%
								}
							%>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>홍길동</td>
						<td>출석</td>
						<td>지각</td>
						<td>출석</td>
						<td>출석</td>
						<td>출석</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="footer">
			<div>
				<img alt="logo" src="img/logo.jpg" />
				<p>
					비트캠프 서울시 서초구 강남대로 459 (서초동, 백암빌딩)｜ 사업자등록번호 : 214-85-24928<br>
					(주)비트컴퓨터 서초본원 대표이사 : 조현정 / 문의 : 02-3486-9600 / 팩스 : 02-6007-1245<br>
					통신판매업 신고번호 : 제 서초-00098호 / 개인정보보호관리책임자 : 최종진<br> Copyright
					&copy; 비트캠프 All rights reserved.
				</p>
			</div>
		</div>

	</div>
</body>
</html>