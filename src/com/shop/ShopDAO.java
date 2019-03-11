package com.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

	private Connection conn = null;

	public ShopDAO(Connection conn){
		this.conn = conn;
	}

	//��ü ������
	public List<ShopDTO> getLists(String searchValue) {

		List<ShopDTO> lists = new ArrayList<ShopDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			searchValue = "%" + searchValue + "%";

			sql = "select shopName,shopTel,shopAddr1,shopAddr2 ";
			sql+= "from shop where shopName like ? or shopAddr1 like ?";
			sql+= "order by shopName desc ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setString(2, searchValue);

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

	//��ü �������� ����
	public int getDataCount(String searchValue) {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			//like ���
			searchValue = "%" + searchValue + "%";

			sql = "select nvl(count(*),0) from shop ";
			sql+= "where shopName like ? or shopAddr1 like ?";
			//searchKey�� ������ ���� �������, like ���� ?���� ���� �� �� ���� �ִ�.

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setString(2, searchValue);
			
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
