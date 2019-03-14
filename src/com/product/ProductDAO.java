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
	

	//페이징 전체 데이터 출력 - 최신순
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
	
	

	//페이징 전체 데이터 출력 - 판매량순
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
	
	
	//옵션 출력
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


	//전체 데이터의 갯수
	public int getDataCount() {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql = "select nvl(count(*),0) from product where fileCategory='list'";
			//searchKey는 무조건 값이 들어지만, like 뒤의 ?에는 값이 안 들어갈 수도 있다.

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

	//전체 데이터의 갯수
	public int getDataCountCategory(String productCategory) {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql = "select nvl(count(*),0) from product where fileCategory='list' and productCategory=?";
			//searchKey는 무조건 값이 들어지만, like 뒤의 ?에는 값이 안 들어갈 수도 있다.

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
	
	//페이징 베스트상품 데이터 출력 - 카테고리별
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

	//전체 출력
	public List<ProductDTO> getList(){

		List<ProductDTO> lists = new ArrayList<ProductDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			//product테이블에서 정보 가져오기
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
