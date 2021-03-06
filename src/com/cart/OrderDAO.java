package com.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.coupon.CouponDTO;
import com.destination.DestinationDTO;

public class OrderDAO {
	
	private Connection conn = null;

	public OrderDAO(Connection conn) {
		this.conn = conn;
	}
	
	//사용자 기본 배송지 가져오기
	public DestinationDTO getOrderDest(String userId){
		
		DestinationDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select addrKey,destNickname,destName,zip,addr1,addr2,destPhone,destTel ";
			sql+= "from destination where userId=? and addrKey='yes'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
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
	
	//사용자 기본 배송지 가져오기
	public int getMemberPoint(String userId){

		int memberPoint = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select point from member where userId=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if(rs.next()){

				memberPoint = rs.getInt("point");

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return memberPoint;

	}
	
	//사용자 주문 목록 가져오기
	public List<OrderListDTO> getOrderList(String userId){
		
		List<OrderListDTO> lists = new ArrayList<OrderListDTO>();
		
		OrderListDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select cart.userId userId,cart.productId productId,productName,productOption,cart.amount amount,cart.price price,originalName,saveFileName ";
			sql+= "from cart,product ";
			sql+= "where cart.productId=product.productId ";
			sql+= "and userId=? and orderSelect='yes'";	
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				dto = new OrderListDTO();
				
				dto.setUserId(rs.getString("userId"));
				dto.setProductId(rs.getString("productId"));
				dto.setProductName(rs.getString("productName"));
				dto.setProductOption(rs.getString("productOption"));
				dto.setAmount(rs.getInt("amount"));
				dto.setPrice(rs.getInt("price"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				
				lists.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}
	
	//사용자가 주문한 물품 개수
	public int getOrderCount(String userId){
		
		int totalOrderCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from cart "; 
			sql+= "where userId=? and orderSelect='yes'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				totalOrderCount = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return totalOrderCount;
		
	}
	
	//사용가능한 쿠폰 정보 가져오기
	public List<CouponDTO> getUserCoupon(String userId,int totalPrice){
		
		List<CouponDTO> lists = new ArrayList<CouponDTO>();
		
		CouponDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select userId,issueDate,coupon.couponKey couponKey,couponName,couponScore,discount,couponStartDay,couponEndDay,couponGrade,used ";
			sql+= "from coupon,issue ";
			sql+= "where issue.couponKey = coupon.couponKey ";
			sql+= "and userId=? and used='N' and ?>=couponScore";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, totalPrice);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				dto = new CouponDTO();
				
				dto.setCouponKey(rs.getInt("couponKey"));
				dto.setCouponName(rs.getString("couponName"));
				dto.setDiscount(rs.getInt("discount"));
				
				lists.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	} 
	
	//num의 max값 구하기
	public int getMaxNum(){
		
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(ordernum),0) from orderTable";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				maxNum = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return maxNum;
		
	}
	
	//주문 데이터 입력(상품관련)
	public void insertOrderDataProduct(int maxNum, OrderDTO orderDto){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into orderTable (orderNum,userId,productId,amount,price,eMail) ";
			sql+= "values (?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, orderDto.getUserId());
			pstmt.setString(3, orderDto.getProductId());
			pstmt.setInt(4, orderDto.getAmount());
			pstmt.setInt(5, orderDto.getPrice());
			pstmt.setString(6, orderDto.geteMail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//주문 데이터 입력(주소)
	public void insertOrderDataAddr(int maxNum, OrderDTO orderDto) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update orderTable set zip=?,addr1=?,addr2=?,addrKey=? ";
			sql+= "where orderNum=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, orderDto.getZip());
			pstmt.setString(2, orderDto.getAddr1());
			pstmt.setString(3, orderDto.getAddr2());
			pstmt.setString(4, orderDto.getAddrKey());
			pstmt.setInt(5, maxNum);
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//주문 성공시 issue테이블에 update
	public int useCouponUpdate(int couponKey,String userId){
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update issue set used='Y' where userId=? and couponKey=? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,userId);
			pstmt.setInt(2,couponKey);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}
	
	//주문 성공시 장바구니에서 구매품목 삭제
	public void deleteCartProduct(String userId, String productId){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "delete cart where userId=? and productId=? and orderSelect='yes'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//주문 성공시 멤버테이블 포인트 추가
	public void updateMemberPoint(String userId, int point){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update member set point=? where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, point);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	//orderTable에서 주문 정보 가져오기
	public List<OrderDTO> getCompleteOrder(int maxNum,String userId){
		
		List<OrderDTO> lists = new ArrayList<OrderDTO>();
		
		OrderDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select orderNum,orderDate,userId,orderTable.productId productId,productName,orderTable.zip zip,orderTable.addr1 addr1, ";
			sql+= "orderTable.addr2 addr2,orderTable.addrkey addrkey,orderTable.amount amount,orderTable.price price,deliveryFee,email ";
			sql+= "from orderTable,product where orderTable.productId = product.productId ";
			sql+= "and orderNum=? and userId=?";

			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				dto = new OrderDTO();
				
				dto.setOrderNum(rs.getInt("orderNum"));
				dto.setOrderDate(rs.getString("orderDate"));
				dto.setUserId(rs.getString("userId"));
				dto.setProductId(rs.getString("productId"));
				dto.setProductName(rs.getString("productName"));
				dto.setZip(rs.getInt("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddrKey(rs.getString("addrkey"));
				dto.setAmount(rs.getInt("amount"));
				dto.setPrice(rs.getInt("price"));
				dto.setDeliveryFee(rs.getInt("deliveryFee"));
				dto.seteMail(rs.getString("email"));
				
				lists.add(dto);
					
			}
		
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}
	
	//리뷰 추가
	public void insertReview(OrderDTO dto){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into review (userId,productId,writed) ";
			sql+= "values (?,?,'no') ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,dto.getUserId());
			pstmt.setString(2,dto.getProductId());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//사용자 기간별 주문조회
	//7일전
	public List<OrderDTO> getUserOrder7dayLists(String userId){

		List<OrderDTO> lists = new ArrayList<OrderDTO>();
		OrderDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select orderNum,to_char(orderDate,'yyyy-mm-dd') orderDate,userId,ordertable.productId productId,productName,zip,addr1,addr2,addrKey,ordertable.amount amount,";
			sql+= "ordertable.price price,deliveryFee,eMail,originalName,saveFileName ";
			sql+= "from ordertable,product where ordertable.productId = product.productId ";
			sql+= "and userId=? and to_char(orderDate,'yyyy-mm-dd')>=(select to_char(sysdate-(interval '7' day),'yyyy-mm-dd') from dual) ";


			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			
			while(rs.next()){

				dto = new OrderDTO();
				
				dto.setOrderNum(rs.getInt("orderNum"));
				dto.setOrderDate(rs.getString("orderDate"));
				dto.setUserId(rs.getString("userId"));
				dto.setProductId(rs.getString("productId"));
				dto.setProductName(rs.getString("productName"));
				dto.setZip(rs.getInt("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddrKey(rs.getString("addrKey"));
				dto.setAmount(rs.getInt("amount"));
				dto.setPrice(rs.getInt("price"));
				dto.setDeliveryFee(rs.getInt("deliveryFee"));
				dto.seteMail(rs.getString("eMail"));
				dto.setOriginalName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFileName"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}
	
	//1달전
		public List<OrderDTO> getUserOrder1monthLists(String userId){

			List<OrderDTO> lists = new ArrayList<OrderDTO>();
			OrderDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;

			try {

				sql = "select orderNum,to_char(orderDate,'yyyy-mm-dd') orderDate,userId,ordertable.productId productId,productName,zip,addr1,addr2,addrKey,ordertable.amount amount,";
				sql+= "ordertable.price price,deliveryFee,eMail,originalName,saveFileName ";
				sql+= "from ordertable,product where ordertable.productId = product.productId ";
				sql+= "and userId=? and to_char(orderDate,'yyyy-mm-dd')>=(select to_char(sysdate-(interval '1' month),'yyyy-mm-dd') from dual) ";


				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, userId);

				rs = pstmt.executeQuery();
				
				while(rs.next()){

					dto = new OrderDTO();
					
					dto.setOrderNum(rs.getInt("orderNum"));
					dto.setOrderDate(rs.getString("orderDate"));
					dto.setUserId(rs.getString("userId"));
					dto.setProductId(rs.getString("productId"));
					dto.setProductName(rs.getString("productName"));
					dto.setZip(rs.getInt("zip"));
					dto.setAddr1(rs.getString("addr1"));
					dto.setAddr2(rs.getString("addr2"));
					dto.setAddrKey(rs.getString("addrKey"));
					dto.setAmount(rs.getInt("amount"));
					dto.setPrice(rs.getInt("price"));
					dto.setDeliveryFee(rs.getInt("deliveryFee"));
					dto.seteMail(rs.getString("eMail"));
					dto.setOriginalName(rs.getString("originalName"));
					dto.setSaveFileName(rs.getString("saveFileName"));

					lists.add(dto);

				}

				rs.close();
				pstmt.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return lists;

		}
		
		//3개월전
		public List<OrderDTO> getUserOrder3monthLists(String userId){

			List<OrderDTO> lists = new ArrayList<OrderDTO>();
			OrderDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;

			try {

				sql = "select orderNum,to_char(orderDate,'yyyy-mm-dd') orderDate,userId,ordertable.productId productId,productName,zip,addr1,addr2,addrKey,ordertable.amount amount,";
				sql+= "ordertable.price price,deliveryFee,eMail,originalName,saveFileName ";
				sql+= "from ordertable,product where ordertable.productId = product.productId ";
				sql+= "and userId=? and to_char(orderDate,'yyyy-mm-dd')>=(select to_char(sysdate-(interval '3' month),'yyyy-mm-dd') from dual) ";


				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, userId);

				rs = pstmt.executeQuery();
				
				while(rs.next()){

					dto = new OrderDTO();
					
					dto.setOrderNum(rs.getInt("orderNum"));
					dto.setOrderDate(rs.getString("orderDate"));
					dto.setUserId(rs.getString("userId"));
					dto.setProductId(rs.getString("productId"));
					dto.setProductName(rs.getString("productName"));
					dto.setZip(rs.getInt("zip"));
					dto.setAddr1(rs.getString("addr1"));
					dto.setAddr2(rs.getString("addr2"));
					dto.setAddrKey(rs.getString("addrKey"));
					dto.setAmount(rs.getInt("amount"));
					dto.setPrice(rs.getInt("price"));
					dto.setDeliveryFee(rs.getInt("deliveryFee"));
					dto.seteMail(rs.getString("eMail"));
					dto.setOriginalName(rs.getString("originalName"));
					dto.setSaveFileName(rs.getString("saveFileName"));

					lists.add(dto);

				}

				rs.close();
				pstmt.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return lists;

		}
		
		//6개월 전
		public List<OrderDTO> getUserOrder6monthLists(String userId){

			List<OrderDTO> lists = new ArrayList<OrderDTO>();
			OrderDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;

			try {

				sql = "select orderNum,to_char(orderDate,'yyyy-mm-dd') orderDate,userId,ordertable.productId productId,productName,zip,addr1,addr2,addrKey,ordertable.amount amount,";
				sql+= "ordertable.price price,deliveryFee,eMail,originalName,saveFileName ";
				sql+= "from ordertable,product where ordertable.productId = product.productId ";
				sql+= "and userId=? and to_char(orderDate,'yyyy-mm-dd')>=(select to_char(sysdate-(interval '6' month),'yyyy-mm-dd') from dual) ";


				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, userId);

				rs = pstmt.executeQuery();
				
				while(rs.next()){

					dto = new OrderDTO();
					
					dto.setOrderNum(rs.getInt("orderNum"));
					dto.setOrderDate(rs.getString("orderDate"));
					dto.setUserId(rs.getString("userId"));
					dto.setProductId(rs.getString("productId"));
					dto.setProductName(rs.getString("productName"));
					dto.setZip(rs.getInt("zip"));
					dto.setAddr1(rs.getString("addr1"));
					dto.setAddr2(rs.getString("addr2"));
					dto.setAddrKey(rs.getString("addrKey"));
					dto.setAmount(rs.getInt("amount"));
					dto.setPrice(rs.getInt("price"));
					dto.setDeliveryFee(rs.getInt("deliveryFee"));
					dto.seteMail(rs.getString("eMail"));
					dto.setOriginalName(rs.getString("originalName"));
					dto.setSaveFileName(rs.getString("saveFileName"));

					lists.add(dto);

				}

				rs.close();
				pstmt.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return lists;

		}
		
		//1년전 
		public List<OrderDTO> getUserOrder1yearLists(String userId){

			List<OrderDTO> lists = new ArrayList<OrderDTO>();
			OrderDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;

			try {

				sql = "select orderNum,to_char(orderDate,'yyyy-mm-dd') orderDate,userId,ordertable.productId productId,productName,zip,addr1,addr2,addrKey,ordertable.amount amount,";
				sql+= "ordertable.price price,deliveryFee,eMail,originalName,saveFileName ";
				sql+= "from ordertable,product where ordertable.productId = product.productId ";
				sql+= "and userId=? and to_char(orderDate,'yyyy-mm-dd')>=(select to_char(sysdate-(interval '1' year),'yyyy-mm-dd') from dual) ";


				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, userId);

				rs = pstmt.executeQuery();
				
				while(rs.next()){

					dto = new OrderDTO();
					
					dto.setOrderNum(rs.getInt("orderNum"));
					dto.setOrderDate(rs.getString("orderDate"));
					dto.setUserId(rs.getString("userId"));
					dto.setProductId(rs.getString("productId"));
					dto.setProductName(rs.getString("productName"));
					dto.setZip(rs.getInt("zip"));
					dto.setAddr1(rs.getString("addr1"));
					dto.setAddr2(rs.getString("addr2"));
					dto.setAddrKey(rs.getString("addrKey"));
					dto.setAmount(rs.getInt("amount"));
					dto.setPrice(rs.getInt("price"));
					dto.setDeliveryFee(rs.getInt("deliveryFee"));
					dto.seteMail(rs.getString("eMail"));
					dto.setOriginalName(rs.getString("originalName"));
					dto.setSaveFileName(rs.getString("saveFileName"));

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
