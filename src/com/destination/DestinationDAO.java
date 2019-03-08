package com.destination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO {
	
	private Connection conn = null;
	
	public DestinationDAO(Connection conn){
		this.conn = conn;
	}
	
	//배송지 정보 입력
	public void insertData(DestinationDTO dto){
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into destination (userId,destNickname,destName,destPhone,destTel,zip,addr1,addr2,addrKey) ";
			sql+= "values (?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,dto.getUserId());
			pstmt.setString(2,dto.getDestNickname());
			pstmt.setString(3,dto.getDestName());
			pstmt.setString(4,dto.getDestPhone());
			pstmt.setString(5,dto.getDestTel());
			pstmt.setInt(6,dto.getZip());
			pstmt.setString(7,dto.getAddr1());
			pstmt.setString(8,dto.getAddr2());
			pstmt.setString(9,dto.getAddrKey());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//배송지 정보 리스트 출력
	public List<DestinationDTO> getList(String userId){
		
		List<DestinationDTO> lists = new ArrayList<DestinationDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select addrKey,destNickname,destName,zip,addr1,addr2,destPhone ";
			sql+= "from destination where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				DestinationDTO dto = new DestinationDTO();
				
				dto.setAddrKey(rs.getString("addrKey"));
				dto.setDestNickname(rs.getString("destNickname"));
				dto.setDestName(rs.getString("destName"));
				dto.setZip(rs.getInt("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setDestPhone(rs.getString("destphone"));
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}
	
	//전체 데이터의 갯수
	public int getDataCount(String userId){
		
		int totalDataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from destination where userid=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
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
	
	//배송지 하나 정보
	public DestinationDTO getReadData(String userId, String destNickname){
		
		DestinationDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select addrKey,destNickname,destName,zip,addr1,addr2,destPhone,destTel ";
			sql+= "from destination where userId=? and destNickname=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, destNickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new DestinationDTO();
				
				dto.setAddrKey(rs.getString("addrKey"));
				dto.setDestNickname(rs.getString("destNickname"));
				dto.setDestName(rs.getString("destName"));
				dto.setZip(rs.getInt("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setDestPhone(rs.getString("destPhone"));
				dto.setDestTel(rs.getString("destTel"));
						
			}
		
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
		
	}
	
	//addrKey yes → no
	public void changeAddrkeyNo(){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update destination set addrKey='no' where addrKey='yes'"; 
			
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	//addrKey no → yes
	public void changeAddrkeyYes(String userId, String destNickname){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update destination set addrKey='yes' where userId=? and destNickname=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, destNickname);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//배송지명 가져오기
	public String[] selectDestNickname(String userId, int totalDataCount, String destNickname){
		
		String destNicknameList[] = new String[totalDataCount];
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs = null;
		
		try {
			
		
			sql = "select destNickname from destination where userId=? and destNickname not in(?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, destNickname);
			

			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()){
				destNicknameList[i] = rs.getString("destNickname");
				i++;
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return destNicknameList;
		
	}
	
	//배송지 수정
	public void updateData(DestinationDTO dto, String destNickname){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update destination set destNickname=?, destName=?, destPhone=?, destTel=?, zip=?,addr1=?,addr2=? ";
			sql+= "where userId=? and destNickname=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,dto.getDestNickname());
			pstmt.setString(2,dto.getDestName());
			pstmt.setString(3,dto.getDestPhone());
			pstmt.setString(4,dto.getDestTel());
			pstmt.setInt(5,dto.getZip());
			pstmt.setString(6, dto.getAddr1());
			pstmt.setString(7,dto.getAddr2());
			pstmt.setString(8, dto.getUserId());
			pstmt.setString(9, destNickname);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//배송지 삭제
	public void deleteData(String userId, String destNickname){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "delete destination where userId=? and destNickname=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, destNickname);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}
