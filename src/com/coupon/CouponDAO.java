package com.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CouponDAO {

	private Connection conn = null;
	
	public CouponDAO(Connection conn){
		this.conn = conn;
	}
	
	
	//���� �����߱� ��ü ����Ʈ�� ���� ��ȯ
	public int couponGetCount(String userId){
		
		int totalDataCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql= "select count(*) from issue where userid=? ";
	
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,userId);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				totalDataCount = rs.getInt(1);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return totalDataCount;
		
	}
	
	//���� �����߱� ��ü ����Ʈ  
	public List<MyCouponDTO> couponGetLists(String userId){

		List<MyCouponDTO> lists = new ArrayList<MyCouponDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql= "select to_char(a.ISSUEDATE,'YYYY-MM-DD') ISSUEDATE,b.DISCOUNT DISCOUNT,b.COUPONNAME COUPONNAME,to_char(b.COUPONENDDAY,'YYYY-MM-DD') COUPONENDDAY,a.used used from issue a, coupon b where a.couponKey = b.couponKey and a.userid=? ";
	
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,userId);
			
			rs = pstmt.executeQuery();

			while(rs.next()){

				MyCouponDTO dto = new MyCouponDTO();

				dto.setIssueDate(rs.getString("ISSUEDATE"));
				dto.setDiscount(rs.getInt("DISCOUNT"));
				dto.setCouponName(rs.getString("COUPONNAME"));
				dto.setCouponEndDate(rs.getString("COUPONENDDAY"));
				dto.setUsed(rs.getString("used"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}



	//�����߱� ��ü ����Ʈ  
	public List<IssueDTO> couponGetLists() {
		
		List<IssueDTO> lists = new ArrayList<IssueDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			
			sql= "select couponkey,userid,to_char(issuedate,'YYYY-MM-DD') issuedate ";
			sql+= "from issue";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				IssueDTO dto = new IssueDTO();

				
				dto.setCouponKey(rs.getInt("couponkey"));
				dto.setUserId(rs.getString("userId"));
				dto.setIssueDate(rs.getString("issueDate"));
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}
	
	//�����߱� ����Ʈ insert
	public int couponInsertData(IssueDTO dto){
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into issue (userId,couponKey,issueDate) ";
			sql+= "values (?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,dto.getUserId());
			pstmt.setInt(2, dto.getCouponKey());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}
	
	

	//��ü ������ 
	public List<CouponDTO> getLists() {
		
		List<CouponDTO> lists = new ArrayList<CouponDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			
			sql= "select couponkey,couponName,discount,couponScore,couponGrade,to_char(couponStartDay,'YYYY-MM-DD') couponStartDay,to_char(couponEndDay,'YYYY-MM-DD') couponEndDay ";
			sql+= "from coupon";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				CouponDTO dto = new CouponDTO();

				
				dto.setCouponKey(rs.getInt("couponkey"));
				dto.setCouponName(rs.getString("couponName"));
				dto.setDiscount(rs.getInt("discount"));
				dto.setCouponScore(rs.getInt("couponScore"));
				dto.setCouponGrade(rs.getString("couponGrade"));
				dto.setCouponStartDate(rs.getString("couponStartDay"));
				dto.setCouponEndDate(rs.getString("couponEndDay"));
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}

	public int deleteData(int num) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "delete coupon where couponKey=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}

	//num�� max�� ���ϱ�
	public int getMaxNum(){
		
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(couponkey),0) from coupon";
			
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
	
	//�Է�
	public int insertData(CouponDTO dto){
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into coupon (couponKey,couponName,discount,couponGrade,couponScore,COUPONENDDAY,COUPONSTARTDAY) ";
			sql+= "values (?,?,?,?,?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getCouponKey());
			pstmt.setString(2, dto.getCouponName());
			pstmt.setInt(3, dto.getDiscount());
			pstmt.setString(4, dto.getCouponGrade());
			pstmt.setInt(5, dto.getCouponScore());
			pstmt.setString(6, dto.getCouponEndDate());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}
	
	//�Է��Ѹ�ŭ ��¥ ���ϱ�
	public final String testGetDate(int period) {
	     
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE, period);
	     
	    // Ư�� ������ ��¥�� ���� �̱�
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String strDate = df.format(cal.getTime());
	    return strDate;
	}


	
}
