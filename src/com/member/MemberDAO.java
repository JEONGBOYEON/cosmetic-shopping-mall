package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MemberDAO {

	private Connection conn;

	public MemberDAO(Connection conn){
		this.conn=conn;
	}

	//회원가입
	public int insertData(MemberDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		String sql;

		try {
			sql="insert into member (userId,userPwd,userName,birth,gender,phone) ";
			sql+="values (?,?,?,?,?,?)";

			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPwd());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getGender());
			pstmt.setString(6, dto.getPhone());

			result=pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	//회원 가져오기(한명의 회원찾기)
	public MemberDTO getReadData(String userId){
		MemberDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;

		try {
			sql="select userId,userPwd,userName,to_char(birth,'YYYYMMDD') birth, phone,point,userGrade,gender ";
			sql+="from member where userId=?";

			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs=pstmt.executeQuery();

			if(rs.next()){
				dto= new MemberDTO();

				dto.setUserId(rs.getString("userId"));
				dto.setUserPwd(rs.getString("userPwd"));
				dto.setUserName(rs.getString("userName"));
				dto.setBirth(rs.getString("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
				dto.setUserGrade(rs.getString("userGrade"));
				dto.setGender(rs.getString("gender"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}
	
	//아이디 찾기(이름으로 한명의 회원찾기) 
	public MemberDTO getReadName(String userName){
		MemberDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;

		try {
			sql="select userId,userPwd,userName,to_char(birth,'YYYYMMDD') birth, phone,point,userGrade,gender ";
			sql+="from member where userName=?";

			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, userName);

			rs=pstmt.executeQuery();

			if(rs.next()){
				dto= new MemberDTO();

				dto.setUserId(rs.getString("userId"));
				dto.setUserPwd(rs.getString("userPwd"));
				dto.setUserName(rs.getString("userName"));
				dto.setBirth(rs.getString("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
				dto.setUserGrade(rs.getString("userGrade"));
				dto.setGender(rs.getString("gender"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}
		

	//수정
	public int updateData(MemberDTO dto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update member set userPwd=?,birth=?,phone=? ";
			sql+= "where userId=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUserPwd());
			pstmt.setString(2, dto.getBirth());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getUserId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;

	}
	
	//삭제
	public int deleteData(String userId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "delete member where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}

}
