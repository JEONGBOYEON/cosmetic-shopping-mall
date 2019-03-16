package com.coupon;

public class IssueDTO {

	private String userId;
	private String issueDate;
	private int couponKey;
	private String used;
	
	
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public int getCouponKey() {
		return couponKey;
	}
	public void setCouponKey(int couponKey) {
		this.couponKey = couponKey;
	}
	
	
	
}
