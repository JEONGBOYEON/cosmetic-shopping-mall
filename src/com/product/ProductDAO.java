package com.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ProductDAO {

	private Connection conn = null;
	
	public ProductDAO(Connection conn){
		this.conn = conn;
	}
	

	//����¡ ��ü ������ ��� - �ֽż�
	public List<ProductDTO> getListsNew(int start, int end) {

		List<ProductDTO> lists = new ArrayList<ProductDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select productid,productname,price,savefilename,productoption ";
			sql+= "from product where fileCategory='list' order by productdate )data) ";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				ProductDTO dto = new ProductDTO();

				dto.setProductId(rs.getString("productid"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setProductName(rs.getString("productname"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductOption(rs.getString("productoption"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}
	
	

	//����¡ ��ü ������ ��� - �Ǹŷ���
	public List<ProductDTO> getListsBest(int start, int end) {

		List<ProductDTO> lists = new ArrayList<ProductDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select productid,productname,price,savefilename,productoption ";
			sql+= "from product where fileCategory='list' order by amount )data) ";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				ProductDTO dto = new ProductDTO();

				dto.setProductId(rs.getString("productid"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setProductName(rs.getString("productname"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductOption(rs.getString("productoption"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}
	
	
	//�ɼ� ���
	public List<String> getOption(String productName){
		
		List<String> options = new ArrayList<String>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select productoption from product where productname = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();

			while(rs.next()){

				options.add(rs.getString("productoption"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return options;
	}


	//��ü �������� ����
	public int getDataCount() {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql = "select nvl(count(*),0) from product where fileCategory='list'";
			//searchKey�� ������ ���� �������, like ���� ?���� ���� �� �� ���� �ִ�.

			pstmt = conn.prepareStatement(sql);

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

	//��ü �������� ����
	public int getDataCountCategory(String productCategory) {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql = "select nvl(count(*),0) from product where fileCategory='list' and productCategory=?";
			//searchKey�� ������ ���� �������, like ���� ?���� ���� �� �� ���� �ִ�.

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productCategory);
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
	
	//����¡ ����Ʈ��ǰ ������ ��� - ī�װ���
	public List<ProductDTO> getListsCategory(int start, int end, String productCategory) {

		List<ProductDTO> lists = new ArrayList<ProductDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select productid,productname,price,savefilename,productoption ";
			sql+= "from product where fileCategory='list' and productCategory=? )data) ";
			sql+= "where rnum>=? and rnum<=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				ProductDTO dto = new ProductDTO();

				dto.setProductId(rs.getString("productid"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setProductName(rs.getString("productname"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductOption(rs.getString("productoption"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}
	
	
	
	
	
/*

	//��ü ���
	public List<ProductDTO> getList(){

		List<ProductDTO> lists = new ArrayList<ProductDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			//product���̺��� ���� ��������
			sql = "select productid,productname,price,savefilename,productoption from product where fileCategory='list'";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){

				ProductDTO dto = new ProductDTO();

				dto.setProductId(rs.getString("productid"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setProductName(rs.getString("productname"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductOption(rs.getString("productoption"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}

*/
	
}
