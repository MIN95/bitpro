﻿package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao extends Dao{

	public ArrayList<CalendarDto> getCalendarMonthList(int lectureId, String yearMonth){
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
	
	public ArrayList<CalendarDto> getCalendarDayList(int lectureId, String yearMonthDay){
		openConnection();
		
		closeConnection();
		return null;
	}

	public AttendanceDto getAttendance(String userId) {
		openConnection();
		
		closeConnection();
		
		return null;
	}

	public int getTotalDays(int lectureId) {
		// 총 수업 일수를 받아오는 메서드
		String sql = "SELECT total_days FROM user01 u "
				+ "JOIN lectureUser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON lu.lecture_id  = l.lecture_id "
				+ "WHERE l.lecture_id =?";
		int totalDays = -1;
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalDays = rs.getInt("total_days");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return totalDays;
	}
	public int getProgressDays(int lectureId) {
		// 진행중인 수업 일 수를 반환하는 메서드
		openConnection();
		
		closeConnection();
		return -1;
	}

	public int getAttendanceDays(String userId) {
		// 학생의 출석일수를 반환하는 메서드(지각, 조퇴 아님)
		String sql  = "SELECT count(*) as\"attendanceDays\" FROM attendance WHERE std_id = ?";
		int attendanceDays = -1;
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attendanceDays = rs.getInt("attendanceDays");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return attendanceDays;
	}

	public int getNewQnaLAnswerNum(String userId) {
		// 답변이 달렸으나 학생이 확인하지 않은 QnaL의 개수를 반환 하는 메서드
		openConnection();
		
		closeConnection();
		return -1;
	}
	public int getTotalQnaLNum(String userId){
		//학생이 QnaL에 올린 문의의 개수를 반환하는 메서드
		openConnection();
		
		closeConnection();
		return -1;
	}

	public int[] getAttendanceStatusList(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public ArrayList<AttendanceDto> getAttendanceMonthList(String userId, String yearMonth) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public ScoreDto getScoreBean(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public ArrayList<AssignmentDto> getAssignmentList(int lecture_id) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public AssignmentDto getAssignmentDetail(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public SubmsissionDto getSubmissionBean(String assignmentId, String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int insertSubmission(int assignmentId, String userId) {
		openConnection();
		
		closeConnection();
		return -1;
	}

	public int deleteSubmission(int assignmentId, String userId) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public int updateSubmission(String assignmentId, String userId,
			String fileName) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public ArrayList<QnaLDto> getQnaList(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int insertQnaL(String userId, String title, String type,
			String questionContent) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public QnaLDto getQnaBean(String qnaId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int updateQnaL(int qnaId, String title, String type,
			String questionContent) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public int deleteQnaL(String[] qnaId) {
		//String으로 받아서 여기서 int로 변환?
		// qna삭제 배열에 하나만 들어있으면 하나만삭제, 여러개면 여러개 삭제
		openConnection();
		
		closeConnection();
		return 0;
	}

	public AssignmentDto getAssignmentBean(String assignmentId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int updateAttendance(String stuId) {
		//일괄적으로 insert는 AM 6시, 출석마감은 PM 11시에 되는걸로
		//학생이 출석버튼 클릭시 시간에 맞춰 출석 처리
		
		openConnection();
		
		closeConnection();
		return 0;
	}


}
