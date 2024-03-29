package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao extends Dao{

	//메인페이지 달력 가져오기
	//다 가져와서 목록까지 저장하고 있다가 뿌려주기
	//제이쿼리로 숨겼다가 달력에 일 클릭하면 나타나는걸루
	//월별 수강생관리 페이지 월은 ?idx=""로 받아오기
	//제일 처음 접근일 때는 sysdate로 가져오기
	//날짜이동버튼을 누르면 제이쿼리에서 2019-07에서 -1을 하든 +1을 하든 해서 idx값으로 넘겨주기
	public ArrayList<CalendarDto> getCalendarMonthList(String yearMonth){
		ArrayList<CalendarDto> list = new ArrayList<CalendarDto>();
		
		String sql = "";
		if(yearMonth==null){
			//int calendarId, lectureId;
			//String title, content, startDate, endDate;
			sql = "select calendar_id,lecture_id,title,start_date,end_date from calendar where calendar_id=to_number(to_char(sysdate,'mm')";
		}else{
			sql = "select calendar_id,lecture_id,title,start_date,end_date from calendar where calendar_id=?";
		}
			
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			while(rs.next()){
				CalendarDto bean = new CalendarDto();		
				list.add(bean);
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	public ArrayList<CalendarDto> getCalendarDayList(String yearMonthDay){
		openConnection();
		
		closeConnection();
		
		return null;
	}
	
	
	//메인페이지 달력 상세 가져오기
	//상세를 누르면 모달창이 생성되게
	public CalendarDto getCalendar(String calendarId){
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//메인페이지 달력 일정 추가하기
	//추가를 누르면 추가모달창이 생성되게
	public int insertCalendar(CalendarDto calendarBean){
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	//메인페이지 달력 일정 수정하기
	//수정을 누르면 상세 모달창은 숨겨지고 수정모달창이 생성되게
	public int updateCalendar(CalendarDto calendarBean){
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	//메인페이지 달력 일정 삭제하기
	public int deleteCalendar(int idx){
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	//메인페이지 유저정보랑 신청현황 문의현황 가져오기
	//신청현황은 그냥 신청온거(현재 apply테이블에 있는 로우 카운트수/apply_id의 max값)
	//문의현황은 총문의수(count)-답변달린거/총문의수(count)
	public UserDto getUser(String userId){
		
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//콤보박스 정렬 및 수강인원/최대인원 가져오기
	//목록이나 상세에서는 조인이나 조건문을 통해 필요한 것들만 가져오게 되므로 따로 빼준다
	public ArrayList<LectureDto> getArrangeLectureList() {
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		String sql = "select lecture_id,name,num_std,max_std from lecture";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setLectureID(rs.getInt("lecture_id"));
				bean.setName(rs.getString("name"));
				bean.setNumStd(rs.getInt("num_std"));
				bean.setMaxStd(rs.getInt("max_std"));			
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//행정팀 강좌목록 불러오기
	//싸그리 다 불러와서 필요한것만 뽑아서 쓰기
	public ArrayList<LectureDto> getLectureList() {
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		//번호/강좌명/강사명/개강일
		String sql = "select l.lecture_id,l.name,u.name as \"teaName\",TO_CHAR(start_date,'yyyymmdd') as \"startDate\" from lecture l JOIN lectureuser lu on lu.lecture_id=l.lecture_id JOIN user01 u on u.user_id=lu.user_id where u.belong='teacher'";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setLectureID(rs.getInt("lecture_id"));
				bean.setName(rs.getString("name"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setTeaName(rs.getString("teaName"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//강좌 상세정보페이지
	public LectureDto getLecture(int lectureId) {
		LectureDto bean = new LectureDto();
		
		//sql문 수정해야할 수도
		//select l.lecture_id,num_std,total_days,max_std,lv,l.name,u.name as "username",start_date,end_date,content,file_name,is_close from lecture l JOIN user01 u on u.belong='teacher' where l.lecture_id=1;
		String sql = "select l.lecture_id as \"lecNum\",num_std,total_days,max_std,lv,l.name,u.name as \"username\",start_date,end_date,content,file_name,is_close from lecture l JOINlectureuser lu on lu.lecture_id=l.lecture_id JOIN user01 u on u.user_id=lu.user_id and u. where l.lecture_id=? and u.belong='teacher'";

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean.setLectureID(rs.getInt("lecNum"));
				bean.setNumStd(rs.getInt("num_std"));
				bean.setTotalDays(rs.getInt("total_days"));
				bean.setMaxStd(rs.getInt("max_std"));
				bean.setName(rs.getString("name"));
				bean.setTeaName(rs.getString("username"));
				//start_date,end_date,content,file_name,is_close
				bean.setStartDate(rs.getString("startDate"));
				bean.setEndDate(rs.getString("end_date"));
				bean.setContent(rs.getString("content"));
				bean.setFileName(rs.getString("file_name"));
				bean.setIsClose(rs.getString("is_close"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return bean;
	}
	
	//행정팀 강좌관리 강좌 추가 페이지(POST방식)
	//넘어올 값 강좌명,강사명,교육기간(시작일,종료일),교육수준,최대인원,강좌내용,파일이름
	//결과값 int로 전송되어 제대로 입력되었는지 확인 가능
	public int insertLecture(LectureDto lectureBean) {
		String sql = "";
		openConnection();
		
		
		closeConnection();
		return 0;
	}
	
	//행정팀 강좌관리 강좌 수정 페이지(POST방식)
	//넘어올 값 커리큘럼이미지,강좌명,강사명,교육기간,교육수준,최대인원,강좌내용,첨부파일을 수정가능
	//결과값 int로 전송되어 제대로 입력되었는지 확인 가능
	public int updateLecture(LectureDto lectureBean) {
		String sql = "";
		openConnection();
		
		
		
		closeConnection();
		
		return 0;
	}
	
	//행정팀 강좌관리 삭제 기능
	public int deleteLecture(int lectureId) {
		//해당 idx값을 삭제
		String sql = "";
		openConnection();
		
		
		
		closeConnection();
		
		return 0;
	}
	
	//행정팀 학생관리 학생등록 목록 페이지
	public ArrayList<RegisterDto> getRegisterList() {
		
		ArrayList<RegisterDto> list = new ArrayList<RegisterDto>();
		
		//번호 (제목제외) ID 이름 강좌 날짜 소속을 불러와야함
		//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
		//조건은 콤보박스로 강좌명에 따라 화면표시
		//그리고 어플라이한 사람만 보여야함 belong=before

		//SELECT apply_id as "num", u.name AS "name" ,u.user_id AS "id", l.name AS "lecName", TO_CHAR(a.apply_date,'yyyymmdd') AS "applyDate", u.belong AS "belong" FROM apply a INNER JOIN user01 u on a.user_id=u.user_id INNER JOIN lecture l on l.lecture_id = a.lecture_id
		//WHERE a.lecture_id = 1 ORDER BY a.apply_date;
		String sql = "SELECT apply_id as \"num\", u.name as \"name\", u.user_id AS \"id\", l.name AS \"lecName\", "
		+"TO_CHAR(a.apply_date,'yyyymmdd') AS \"applyDate\", u.belong AS \"belong\" "
		+"FROM apply a INNER JOIN user01 u on a.user_id=u.user_id "
		+"INNER JOIN lecture l on l.lecture_id = a.lecture_id "
		+"ORDER BY apply_id desc";
		//WHERE a.lecture_id = ? 
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				RegisterDto bean = new RegisterDto();
				bean.setApplyDate(rs.getString("applyDate"));
				bean.setUserId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setUserName(rs.getString("name"));
				bean.setRowNum(rs.getInt("num"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//행정팀 학생관리 학생등록 상세 페이지
	public RegisterDto getRegister(int registerId) {
		//학생목록에서 num를 idx로 받아 해당 num의 수강신청한 내용을 볼 수 있게
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//행정팀 학생관리 학생등록 상세페이지 삭제
	public int deleteRegister(int registerId) {
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	//행정팀 학생관리 수강생으로 등록(user테이블 update), 해당 user_id로 된 apply테이블의 정보를 삭제
	public int updateRegister(String userId) {
		//해당 값들 인자로 받아와서 belong을 update
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	
	//행정팀 수강생관리 목록형
	public ArrayList<UserDto> getManageStu() {
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//행정팀 수강생관리 월별
	public ArrayList<AttendanceDto> getManageStuMonth(String yyyymm) {
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//행정팀 수강생 삭제, 강사 삭제
	public int deleteUser(String[] userId) {
		openConnection();
		
		closeConnection();
		return -1;
	}
	
	//행정팀 강사관리 목록
	public ArrayList<TeacherDto> getTeacherList() {
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//행정팀 강사관리 상세
	//강사 명으로 접근해야 하기 때문에 user_id의 자료형인 String형으로 수정
	public TeacherDto getTeacher(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//행정팀 강사관리 추가
	public int insertTeacher(TeacherDto teacherBean) {
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	//행정팀 강사관리 수정
	public int updateTeacher(TeacherDto teacherBean) {
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	//행정팀 큐엔에이 목록
	//번호, 제목, 작성자, 작성일, 답변여부, 분류
	public ArrayList<QnaLDto> getQnaLList() {
		ArrayList<QnaLDto> list = new ArrayList<QnaLDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num, title, name as \"std_name\","
				+ "TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,is_respon, type "
				+ "FROM "
				+ "WHERE type='행정'";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				QnaLDto bean = new QnaLDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setTitle(rs.getString("title"));
				bean.setStdName(rs.getString("std_name"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setIsRespon(rs.getString("is_respone"));
				bean.setType(rs.getString("type"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return list;
	}
	
	//행정팀 큐엔에이 상세
	public QnaLDto getQnaL(int qnaLId) {
		openConnection();
		
		closeConnection();
		return null;
	}
	
	//행정팀 큐엔에이 삭제
	//여러개삭제할때는 배열로 보내주면 됨
	//그냥 하나일 수도 있고 하나면 배열에 하나만 들어있겠지
	public int deleteQnaL(int[] qnaLIdList) {
		openConnection();
		
		closeConnection();
		return 0;	
	}
	
	//행정팀 큐엔에이 답변등록
	public int updateQnaL(QnaLDto qnaLBean) {
		openConnection();
		
		closeConnection();
		return 0;
	}
	
	

	
	//매일(수업이 있는) 오전 6시 모든 학생의 출석 row를 생성
	//checkin, checkout 모두 null
	public int insertAttendanceAll(ArrayList<UserDto> stuList) {
		openConnection();
		
		closeConnection();
		return -1;
	}
	
	//매일(수업이 있는) 오후 11시 모든 학생의 출석 상태를 수정
	//checkinTime과 checkoutTime을 비교해서 출석,지각, 결석, 조퇴 중 하나로 변경
	public int updateAttendanceAll(ArrayList<UserDto> stuList) {
		openConnection();
		
		closeConnection();
		return -1;
	}

	//행정팀 출석 생성
	public ArrayList<UserDto> getStudentList() {
		openConnection();
		
		closeConnection();
		return null;
	}

	//출석업데이트
	public int updateManageStuMonth(String yyyymm, String[] userId,
			String[] status) {
		openConnection();
		
		closeConnection();
		return 0;
	}
	

}