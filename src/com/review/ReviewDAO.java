package com.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

	private Connection conn = null;

	public ReviewDAO(Connection conn){
		this.conn = conn;
	}

	//한 명의 사용자 리뷰 데이터
	public List<ReviewDTO> getLists(int start, int end, String userId, String writed) {

		List<ReviewDTO> lists = new ArrayList<ReviewDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select productdetail.productId productId,productName,rate,subject,content,to_char(reviewDate,'yyyy-mm-dd') reviewDate ";
			sql+= "from review,productdetail where review.PRODUCTID=productdetail.PRODUCTID and userId=? and writed=?";
			sql+= "order by reviewDate) data) ";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, writed);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				ReviewDTO dto = new ReviewDTO();

				dto.setProductId(rs.getString("productId"));
				dto.setProductName(rs.getString("productName"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReviewDate(rs.getString("reviewDate"));
				dto.setRate(rs.getInt("rate"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}

	//한명의 사용자 리뷰 데이터의 갯수
	public int getDataCount(String userId, String writed) {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*),0) from review ";
			sql+= "where userId = ? and writed=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, writed);

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
	
	//리뷰작성시 작성할 상품 정보 가져오기
	public ReviewDTO getProductList(String userId, String productId){
		
		ReviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select productName,productOption ";
			sql+= "from review,product ";
			sql+= "where review.productId=product.productId and userId=? and review.productId=? and writed='no'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new ReviewDTO();
				
				dto.setProductId(productId);
				dto.setProductName(rs.getString("productName"));
				dto.setProductOption(rs.getString("productOption"));
						
			}
		
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	//리뷰등록
	public void insertData(ReviewDTO dto){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update review set rate=?,subject=?,content=?,reviewDate=sysdate,originalName=?,savefileName=?,writed='yes' ";
			sql+= "where userId=? and productId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRate());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getOriginalName());
			pstmt.setString(5, dto.getSavefileName());
			pstmt.setString(6, dto.getUserId());
			pstmt.setString(7, dto.getProductId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//삭제할 리뷰의 데이터 파일 정보
	public String getSaveFile(String userId, String productId){
		
		String saveFileName = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select saveFileName from review ";
			sql+= "where userId=? and productId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);

			rs = pstmt.executeQuery();
					
			if(rs.next())
				saveFileName = rs.getString(1);
			
			rs.close();
			pstmt.close();
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return saveFileName;
	}
	
	//리뷰삭제
	public void deleteData(String userId, String productId){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update review set rate='',subject='',content='',REVIEWDATE='',originalname='',savefilename='',writed='no' where userId=? and productId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//특정 상품 리뷰 데이터의 갯수
	public int getPoductDataCount(String productName) {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*),0)";
			sql+= "from review where writed='yes' "; 
			sql+= "and productId in (";
			sql+= "select product.productId from review,product ";
			sql+= "where review.productId = product.productId ";
			sql+= "and product.productName=?)";


			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, productName);

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
	
	//특정 상품의 리뷰 데이터 가져오기
	public List<ReviewDTO> productGetLists(int start, int end, String productName) {

		List<ReviewDTO> lists = new ArrayList<ReviewDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select userId,review.productId productId,productOption,rate,subject,content,to_char(reviewDate,'yyyy-mm-dd') reviewDate,review.originalName originalName,review.saveFileName saveFileName ";
			sql+= "from (select * from review where writed='yes' and productId in (";
			sql+= "select product.productId from review,product where review.productId = product.productId and product.productName=?)) review, product product ";
			sql+= "where review.productId=product.productId ";
			sql+= "order by reviewDate) data) ";
			sql+= "where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productName);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()){

				ReviewDTO dto = new ReviewDTO();
				
				dto.setUserId(rs.getString("userId"));
				dto.setProductId(rs.getString("productId"));
				dto.setProductOption(rs.getString("productOption"));
				dto.setRate(rs.getInt("rate"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReviewDate(rs.getString("reviewDate"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSavefileName(rs.getString("saveFileName"));
				
				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}
	
	
	//특정 상품 리뷰 상품평별 데이터의 갯수
	public int getPoductDataCountHeart(String productName, int rate) {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*),0)";
			sql+= "from review where writed='yes' "; 
			sql+= "and productId in (";
			sql+= "select product.productId from review,product ";
			sql+= "where review.productId = product.productId ";
			sql+= "and product.productName=? and rate=?)";


			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, productName);
			pstmt.setInt(2, rate);

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
