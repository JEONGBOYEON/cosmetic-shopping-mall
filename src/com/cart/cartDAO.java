package com.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class cartDAO {

	private Connection conn = null;

	public cartDAO(Connection conn) {
		this.conn = conn;
	}
	
	//��ٱ��� �ѻ�ǰ����
	public int getTotalItemCount(String userId){
		
		int maxNum = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select sum(amount) from cart where userId = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	
	//��ٱ��� �ѻ�ǰ����
	public int getCartItemCount(String userId, String productId){
		
		int maxNum = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select amount from cart where userId = ? and productId=? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);
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
	
	//��ٱ��� �Ѿ�
	public int getTotalItemPrice(String userId){
		
		int maxNum = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select sum(amount*price) from cart where userId = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	
	//��ٱ��� ��ǰ�߰�
	public void insertCartItem(cartDTO dto) {

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into cart(userId,productId,amount,price) ";
			sql += "values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getProductId());
			pstmt.setInt(3, dto.getAmount());
			pstmt.setInt(4, dto.getPrice());

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//��ٱ��� ��ȸ
	public List<cartDTO> getReadData(String userId) {

		List<cartDTO> lists = new LinkedList<cartDTO>();
		cartDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select userId,a.productId productId,productName,productOption,a.amount amount,a.price price,originalName, saveFileName ";
			sql += "from cart a,product b where a.productId=b.productId ";
			sql += "and userId=? order by productName";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new cartDTO();
				dto.setUserId(rs.getString("userId"));
				dto.setProductId(rs.getString("productId"));
				dto.setProductName(rs.getString("productName"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFIleName"));
				dto.setProductOption(rs.getString("productOption"));
				dto.setAmount(rs.getInt("amount"));
				dto.setPrice(rs.getInt("price"));
				lists.add(dto);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	
	//��ٱ��� ������ ����
	public int deleteCartItem(String productId, String userId) {
		
		//���ϻ�ǰ�ɼǻ����� productId
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql= "delete cart where productId=? and userId = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	//�ֹ��Ϸ� �� ��ٱ��� ������ ����
	public int deleteCartItem(String userId) {
		
		//�ֹ��Ϸ�� userId
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql= "delete cart where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//����� ��ǰ�ɼ��� ���� ��ǰid��ȸ
	public String searchProductId(String productName, String productOption) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String productId="";
		
		try {
			sql = "select productId ";
			sql += "from product where productName=? and productOption=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			pstmt.setString(2, productOption);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				productId = rs.getString("productId");
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return productId;
	}

	//�ߺ������� ��ٱ��� �߰�
	public int updateCartItem(cartDTO dto) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql= "update cart set amount=? where userId = ? and productId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getAmount());//��������
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getProductId());
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//
	public int searchBeforeProductId(String productId, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int result=0;
		
		try {
			sql = "select count(*) ";
			sql += "from cart where productId=? and userId=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
}
