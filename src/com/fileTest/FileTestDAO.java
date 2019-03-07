package com.fileTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FileTestDAO {
	
	private Connection conn = null;
	
	public FileTestDAO(Connection conn){
		this.conn = conn;
	}
	
	//레코드번호
	public int getMaxNum(){
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(num),0) from fileTest";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
	
	//파일데이터 입력
	public void insertData(FileTestDTO dto){
		
		PreparedStatement pstmt = null;
		String sql; 
		
		try {
			sql = "insert into fileTest (num,subject,saveFileName, ";
			sql += "originalFileName) values (?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getSaveFileName());
			pstmt.setString(4, dto.getOriginalFileName());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//파일데이터 전체조회
	public List<FileTestDTO> getList(int start, int end){
		
		List<FileTestDTO> lists = new ArrayList<FileTestDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select * from (select rownum rnum,num,subject,saveFileName,originalFileName ";
			sql += "from fileTest order by num desc) ";
			sql += "where rnum >= ? and rnum <=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				FileTestDTO dto = new FileTestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
				lists.add(dto);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	//특정 데이터 조회
	public FileTestDTO getReadData(int num){
		
		FileTestDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select num,subject,saveFileName,originalFileName ";
			sql += "from fileTest where num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num); //매개변수로 받은 num. where조건에 넣어줌
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new FileTestDTO();
	
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;

	}
	
	//파일데이터 삭제
	public void deleteData(int num){
		
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "delete fileTest where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//데이터 세기
	public int getDataCount(){
		
		int totalDataCount = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql;	
		try {
			sql = "select nvl(count(*),0) from fileTest ";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			if(rs.next())
				totalDataCount = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return totalDataCount;
	}
}
