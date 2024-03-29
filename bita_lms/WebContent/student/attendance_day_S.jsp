<%@page import="java.sql.Date"%>
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
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content button').hide().eq(0).show().click(function() {
			$('#content button').show().eq(0).hide();
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
				<li class="topmenu"><a href="attendance.stu">출결관리</a>
				<li><a href="score.stu">성적관리</a></li>
				<li><a href="assignment.stu">과제관리</a></li>
				<li><a href="qna.stu">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<h2>출석상황</h2>
			<div id="content">
				<form>
					<table border="1">
						<tr>
							<td rowspan="3">사진</td>
							<td><%=request.getAttribute("name")%></td>
							<td rowspan="3">입실횟수</td>
							<td rowspan="3">지각</td>
							<td rowspan="3">조퇴</td>
							<td rowspan="3">외출</td>
							<td rowspan="3">결성</td>
						</tr>
						<tr>
							<td>시간</td>
						</tr>
						<tr>
							<td>
							<button id="checkin">입실</button>
							<button id="checkout">퇴실</button>
							</td>
						</tr>
						<tr>
							<td colspan="2"><label for="branch">강좌명 :</label> <span>JAVA 과정</span></td>
							<td rowspan="2" colspan="5"><label for="today_q">문의현황 </label> <span> ?? / 30</span></td>
						</tr>
						<tr>
							<td colspan="2"><label for="branch">강좌기간 :</label> <span>0000-00-00~0000-00-00</span></td>
						</tr>

					</table>
				</form>
				
			</div>

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