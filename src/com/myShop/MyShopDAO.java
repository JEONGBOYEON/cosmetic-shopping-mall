package com.myShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.ShopDTO;

public class MyShopDAO {
	
	private Connection conn;
	
	public MyShopDAO(Connection conn){
		this.conn = conn;
	}

	
	public void insertData(String userId, String shopName){
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into myShop (userId,shopName) ";
			sql+= "values (?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, shopName);

			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	//전체 데이터의 갯수
	public int getDataCount(String userId){
		
		int totalDataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from myShop where userid=?";
			
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
	
	
	//전체 데이터
	public List<ShopDTO> getLists(String userId) {
		
		List<ShopDTO> lists = new ArrayList<ShopDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {	
			
			sql = "select shop.shopName shopName,shopTel,shopAddr1,shopAddr2 ";
			sql+= "from shop, myShop ";
			sql+= "where myShop.shopName = shop.shopName and userId=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ShopDTO dto = new ShopDTO();
				
				dto.setShopName(rs.getString("shopName"));
				dto.setShopTel(rs.getString("shopTel"));
				dto.setShopAddr1(rs.getString("shopAddr1"));
				dto.setShopAddr2(rs.getString("shopAddr2"));			
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}
	
	//단골매장 삭제
	public void deleteData(String userId, String shopName){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "delete myShop where userId=? and shopName=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, shopName);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	
}
