﻿<%@page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,com.bit.model.AdminDao,com.bit.model.LectureDto,com.bit.model.RegisterDto"%>
<%
	ArrayList<Integer> numStd = new ArrayList<Integer>();
	ArrayList<Integer> maxStd = new ArrayList<Integer>();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/frame.css" />
<style type="text/css">
	#menu>ul {
	width: 610px;
	list-style-type: none;
	margin: 0px auto;
	}
	#content{
	height:700px;
	margin: 0 auto;
	}
	#content #sidebar{
	position:absolute;
	top:243px;
	height:700px;
	width: 200px;
	text-align:center;
	background-color: gray;
	}
	#content #sidebar ul li{
	list-style: none;
	}
	#content #sidebar ul li a{
	text-decoration: none;
	color: rgb(0,0,0);
	}
	#content #real_content{
	position:relative;
	left:300px;
	width: 600px;
	height:700px;
	}
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #people_check{
	float:right;
	width: 130px;
	}
	#content #lecture_list{
	clear:both;
	float:right;
	width: 55px;
	}
	#content #app_list{
	clear:both;
	width: 600px;
	height:500px;
	margin: 0 auto;
	text-align:center;
	}
	#content #app_list table{
	width: 600px;
	margin: 0 auto;
	}
	#content #app_list table,th,td{
	border: 1px solid gray;
	}
	#content #under_list{
	width: 600px;
	height:95px;
	margin: 0 auto;
	}
	#content #under_list #close_button{
	float:right;
	width: 45px;
	}
	#content #under_list #search_box{
	clear:both;
	width: 230px;
	margin: 0 auto;
	}
	#content #under_list #page_button{
	width: 150px;
	margin: 0 auto;
	}
</style>
<script type="text/javascript" src="./js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var numStd = $('#people_check>span')
		numStd.hide();
		$("#numStd").show();
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#header>img').click(function() {
			location.href = 'main.adm'
		}).mouseenter(function(){
			$('#header>img').css('cursor', 'pointer')
		});

		$("select[name=lecture_name]").change(
               function() {
                  var k = $(this).children("option:selected").text();
                  console.log(k);
                  if(k=="전체"){
                	  $("#register_change>tr").show();
                	  numStd.hide();
                	  $("#numStd").show();
                  }
                  else{
	                  $("#register_change>tr").hide();
	                  var temp = $("#register_change>tr>td:contains('"
	                        + k + "')");
	                  $(temp).parent().show();
	                  
	                  $('#people_check').find('span').each(function(idx, ele){
	                	 var id = ($(this).attr('id'));
	                	 console.log(id);
	                	    if(id=="JAVA"){
	                	    	numStd.hide();
	                	    	$(this).show();
	                	    	return false;
	                	    }
	                  });
                  }
         });
	});
	//var param = "test"
	var param = $("#lecture_name option:selected").index($("#lecture_name option:selected"));
	

    //테스트 주석
</script>
</head>
<body>

	<div>
		<div id="header">
			<a href="logout.bit">logout</a> <img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
			<ul>
				<li class="topmenu"><a href="#">학생관리</a>
					<ul class="submenu">
						<li><a href="register.adm">학생등록</a></li>
						<li><a href="manage_stu.adm">수강생관리</a></li>
					</ul></li>
				<li><a href="manage_lec.adm">강좌관리</a></li>
				<li><a href="manage_tea.adm">강사관리</a></li>
				<li><a href="qna.adm">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<div id="sidebar">
			<br/><br/><br/><br/>
			<h3>학생관리</h3>
			<br/><br/>
			<ul>
				<li><a href="register.adm">학생등록</a></li>
				<br/>
				<li><a href="manage_stu.adm">수강생관리</a></li>
			</ul>
		</div>
		<div id="real_content">
			<br/>
			<div id="page_name">
				<h2>학생등록</h2>
			</div>
			<div id="people_check">
			<%
				ArrayList<LectureDto> lectureList = (ArrayList<LectureDto>)request.getAttribute("arrangeLecture");
				int num = 0,max=0;
				if(lectureList !=null){
					for(LectureDto bean : lectureList){
						if(bean.getLectureID()>0){
							num += bean.getNumStd();
							max += bean.getMaxStd();
						
			%>
			<span id="<%=bean.getName()%>"><%=bean.getNumStd()+"/"+bean.getMaxStd() %></span>
			<!-- 아래 select에서 어떤 값을 선택했는지에 따라 바뀌는 값 -->
			<!-- option 넘버에 따라 제이쿼리에서 이미 저장되어있는 numStd, maxStd를 해당 넘버의 값으로 변환 -->
			
			<%
						}
					}
			%>
			<span id="numStd"><%=num+"/"+max%></span>
			<!--  <span>num_stu/max_stu</span>-->
			</div>
			<div id="lecture_list">
				<select name="lecture_name" id="lecture_name">

				<option value="" selected="selected">전체</option>
				<%
				for(LectureDto bean : lectureList){
						if(bean.getLectureID()>0){
				%>
					<option value="" ><%=bean.getName()%></option>
				<%
						}
					}
				}
				%>
				    <!-- <option value="" >JAVA</option>
				    <option value="학생" >WEB</option>
				    <option value="회사원" >DB</option> -->
				</select>
			</div>
			<div id="app_list">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>ID</th>
						<th>이름</th>
						<th>강좌</th>
						<th>신청일</th>
					</tr>
				</thead>
				<tbody id="register_change">
					
					<%
								ArrayList<RegisterDto> registerList = (ArrayList<RegisterDto>)request.getAttribute("RegisterList");
								if(registerList !=null){
									for(RegisterDto bean : registerList){
					%>
					<tr>

							<td><%=bean.getNum()%></td>
							<td><a href="register_detail.adm?idx=<%=bean.getNum()%>"><%=bean.getName()%>님의 수강신청</a></td>
							<td><%=bean.getId()%></td>
							<td><%=bean.getName()%></td>
							<td><%=bean.getLecName()%></td>
							<td><%=bean.getApplyDate()%></td>

					</tr>

							<%
								                        }
								}
							%>

				</tbody>
			</table>
		</div>
		<div id="under_list">
			<div id="close_button">
				<button type="button">마감</button>
			</div>
			<div id="search_box">
				<form action="register_list.adm">
					<input type="text" id="search_sub" name="search_sub">
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="page_button">
				<button><</button>
				<button>1</button>
				<button>2</button>
				<button>3</button>
				<button>></button>
			</div>
		</div>
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