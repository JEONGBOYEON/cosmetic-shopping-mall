package com.productDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productDetailDAO {

   private Connection conn = null;
   
   public productDetailDAO(Connection conn){
      this.conn = conn;
   }
   
   //상품데이터등록
   public void insertData(productDetailDTO dto){
      
      PreparedStatement pstmt = null;
      String sql; 
      
      try {
         sql = "insert into product (productId,productCategory,productName,productOption,price,color,texture,season,originalName,saveFileName,fileCategory) ";
         sql += "values (?,?,?,?,?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getProductId());
         pstmt.setString(2, dto.getProductCategory());
         pstmt.setString(3, dto.getProductName());
         pstmt.setString(4, dto.getProductOption());
         pstmt.setInt(5, dto.getPrice());
         //pstmt.setString(6, dto.getProductDate());
         //pstmt.setInt(7, dto.getAmount());
         pstmt.setString(6, dto.getColor());
         pstmt.setString(7, dto.getTexture());
         pstmt.setString(8, dto.getSeason());
         pstmt.setString(9, dto.getOriginalName());
         pstmt.setString(10, dto.getSaveFileName());
         pstmt.setString(11, dto.getFileCategory());
         
         pstmt.executeUpdate();
         pstmt.close();
         
      } catch (Exception e) {
         System.out.println(e.toString());
      }
   }
   
   //상세페이지 제품 조회_리스트용 사진
   public productDetailDTO getReadData(String productName){
      
      productDetailDTO dto = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql;
      
      try {
         sql = "select productId,productCategory,productName,productOption,price,to_char(productDate,'yyyy-mm-dd') productDate,amount,color,texture,season,originalName,saveFileName,filecategory " ;
         sql += "from product where productName=? and filecategory='list'";   
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, productName);
         
         rs = pstmt.executeQuery();

         if(rs.next()){
            
            dto = new productDetailDTO();
            dto.setProductId(rs.getString("productId"));
            dto.setProductCategory(rs.getString("productCategory"));
            dto.setProductName(rs.getString("productName"));
            dto.setProductOption(rs.getString("productOption"));
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
   
   
   //전체상품카운트
   public int getMaxNum(){
      int maxNum = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql;
      
      try {
         
         sql = "select nvl(max(num),0) from productDetail";
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
   
   //상세페이지 제품 조회_상세 이미지조회
   public List<productDetailImageDTO> getDetailImageList(String productName){
      
      List<productDetailImageDTO> lists = new ArrayList<productDetailImageDTO>();
      productDetailImageDTO dto = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql;
      
      try {
         sql = "select detailNum,productId,productName,originalName,saveFileName ";
         sql += "from productDetail where productName = ? ";   
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, productName);
         rs = pstmt.executeQuery();
         
         while(rs.next()){
        	 
            dto = new productDetailImageDTO();
            dto.setDetailNum(rs.getInt("detailNum"));
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
}