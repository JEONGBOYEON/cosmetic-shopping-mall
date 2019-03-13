package com.destination;

public class DestinationDTO {
	
	private String userId;
	private String destNickname;
	private String destNickname2;
	private String destName;
	private String destPhone;
	private String destTel;
	private int zip;
	private String addr1;
	private String addr2;
	private String addrKey;

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDestNickname() {
		return destNickname;
	}
	public void setDestNickname(String destNickname) {
		this.destNickname = destNickname;
	}
	
	public String getDestName() {
		return destName;
	}
	public void setDestName(String destName) {
		this.destName = destName;
	}
	
	public String getDestNickname2() {
		return destNickname2;
	}
	public void setDestNickname2(String destNickname2) {
		this.destNickname2 = destNickname2;
	}
	
	public String getDestPhone() {
		return destPhone;
	}
	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
	}
	
	public String getDestTel() {
		return destTel;
	}
	public void setDestTel(String destTel) {
		this.destTel = destTel;
	}
	
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public String getAddrKey() {
		return addrKey;
	}
	public void setAddrKey(String addrKey) {
		this.addrKey = addrKey;
		
		if(addrKey==null)
			addrKey="no";
		else
			addrKey="yes";
	}

}
