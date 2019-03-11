package com.productDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class productDetailDAO {

	private Connection conn = null;

	public productDetailDAO(Connection conn) {
		this.conn = conn;
	}
	
	//상품옵션의 개수 카운팅
	public int getMaxNum(String productName){
		
		int maxNum = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select count(*) from product where productName = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
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
	
	//전체데이터의 갯수 카운팅
	public int getDataCount(String searchKey, String searchValue){
	
		int totalDataCount = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			//검색어 포함하는 내용을 다출력하게 하기 위해 %붙임
			searchValue = "%" + searchValue + "%";
			sql = "select nvl(count(*),0) from product ";
			sql += "where " + searchKey + " like ? ";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			
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

	//전체상품의 개수 카운팅
	public int getMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select count(*) from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
	

	// 상품데이터등록
	public void insertData(productDetailDTO dto) {

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into product(productId,productCategory,productName,productOption,state,price,";
			sql += "color,texture,season,originalName,saveFileName,fileCategory) ";
			sql += "values (?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getProductId());
			pstmt.setString(2, dto.getProductCategory());
			pstmt.setString(3, dto.getProductName());
			pstmt.setString(4, dto.getProductOption());
			pstmt.setString(5, dto.getState());
			pstmt.setInt(6, dto.getPrice());

			pstmt.setString(7, dto.getColor());
			pstmt.setString(8, dto.getTexture());
			pstmt.setString(9, dto.getSeason());
			pstmt.setString(10, dto.getOriginalName());
			pstmt.setString(11, dto.getSaveFileName());
			pstmt.setString(12, dto.getFileCategory());

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//상품 상세이미지 등록
	public void insertDetailImage(productDetailImageDTO dto) {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into productDetail (detailNum,productId,productName,originalName,saveFileName) ";
	        sql += "values (?,?,?,?,?)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, dto.getDetailNum());
	        pstmt.setString(2, dto.getProductId());
	        pstmt.setString(3, dto.getProductName());
	        pstmt.setString(4, dto.getOriginalName());
	        pstmt.setString(5, dto.getSaveFileName());
	        pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	//상세페이지 제품 조회_리스트용 사진
	public productDetailDTO getReadData(String productId) {

		productDetailDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select productId,productCategory,productName,productOption,state,price,to_char(productDate,'yyyy-mm-dd') productDate,amount,color,texture,season,originalName,saveFileName,filecategory ";
			sql += "from product where productId=? and filecategory='list'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new productDetailDTO();
				dto.setProductId(rs.getString("productId"));
				dto.setProductCategory(rs.getString("productCategory"));
				dto.setProductName(rs.getString("productName"));
				dto.setProductOption(rs.getString("productOption"));
				dto.setState(rs.getString("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductDate(rs.getString("productDate"));
				dto.setAmount(rs.getInt("amount"));
				dto.setColor(rs.getString("color"));
				dto.setTexture(rs.getString("texture"));
				dto.setSeason(rs.getString("season"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("fileCategory"));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	
	//상세페이지 제품 조회_상세 이미지조회
	public List<productDetailImageDTO> getDetailImageList(String searchKey,String searchValue) {
		List<productDetailImageDTO> lists = new ArrayList<productDetailImageDTO>();
		productDetailImageDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select detailNum,productId,productName,originalName,saveFileName ";
			sql += "from productDetail where " +searchKey+ " = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new productDetailImageDTO();
				dto.setDetailNum(rs.getString("detailNum"));
				dto.setProductId(rs.getString("productId"));
				dto.setProductName(rs.getString("productName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalName(rs.getString("originalName"));
				lists.add(dto);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	//동일상품 옵션 리스트 
	public List<String> getOptionList(String productName) {

		List<String> optionlist = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select productOption from product where productName = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				optionlist.add(rs.getString("productOption"));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return optionlist;
		
	}
	
	//admin페이지 상품 리스트 조회
	public List<productDetailDTO> getReadData(int start, int end, String searchKey, String searchValue) {

		List<productDetailDTO> lists = new LinkedList<productDetailDTO>();
		productDetailDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			searchValue = "%" + searchValue + "%";
			sql = "select * from (";
			sql += "select rownum rnum, data.* from ( ";
			sql += "select productId,productCategory,productName,productOption,state,price,to_char(productDate,'yyyy-mm-dd') productDate,amount,color,texture,season,originalName,saveFileName,filecategory ";
			sql += "from product where " +searchKey+ " like ? order by productId desc) data) ";
			sql += "where rnum >= ? and rnum <=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new productDetailDTO();
				dto.setProductId(rs.getString("productId"));
				dto.setProductCategory(rs.getString("productCategory"));
				dto.setProductName(rs.getString("productName"));
				dto.setProductOption(rs.getString("productOption"));
				dto.setState(rs.getString("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductDate(rs.getString("productDate"));
				dto.setAmount(rs.getInt("amount"));
				dto.setColor(rs.getString("color"));
				dto.setTexture(rs.getString("texture"));
				dto.setSeason(rs.getString("season"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("fileCategory"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	//admin페이지 상품 삭제
	public int deleteData(String productId){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql= "delete product where productId=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//admin페이지 제품 조회_수정삭제
	public productDetailDTO getUpdateData(String productId) {

		productDetailDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select productId,productCategory,productName,productOption,state,price,to_char(productDate,'yyyy-mm-dd') productDate,amount,color,texture,season,originalName,saveFileName,filecategory ";
			sql += "from product where productId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new productDetailDTO();
				dto.setProductId(rs.getString("productId"));
				dto.setProductCategory(rs.getString("productCategory"));
				dto.setProductName(rs.getString("productName"));
				dto.setProductOption(rs.getString("productOption"));
				dto.setState(rs.getString("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductDate(rs.getString("productDate"));
				dto.setAmount(rs.getInt("amount"));
				dto.setColor(rs.getString("color"));
				dto.setTexture(rs.getString("texture"));
				dto.setSeason(rs.getString("season"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("fileCategory"));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}
	
	//상품정보 수정
	public int updateData(productDetailDTO dto){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update product set productCategory=?,productName=?,productOption=?,";
			sql += "state=?,price=?,color=?,texture=?,season=? " ;
			sql += "where productId=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProductCategory());
			pstmt.setString(2, dto.getProductName());
			pstmt.setString(3, dto.getProductOption());
			pstmt.setString(4, dto.getState());
			pstmt.setInt(5, dto.getPrice());
			pstmt.setString(6, dto.getColor());
			pstmt.setString(7, dto.getTexture());
			pstmt.setString(8, dto.getSeason());
			pstmt.setString(9, dto.getProductId());
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//상품이미지 수정
	public int updateFileData(productDetailDTO dto){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update productDetail set originalName=?,saveFileName=?,fileCategory=?";
			sql += " where productId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getOriginalName());
			pstmt.setString(2, dto.getSaveFileName());
			pstmt.setString(3, dto.getFileCategory());
			pstmt.setString(4, dto.getProductId());
			result = pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
}