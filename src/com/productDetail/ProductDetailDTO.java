package com.productDetail;

import java.util.List;

public class ProductDetailDTO {
	
	private String productId;
	private String productCategory;
	private String productName;
	private String productOption;
	private int price;
	private String productDate;
	private int amount;
	private String color;
	private String texture;
	private String season;
	private String originalName;
	private String saveFileName;
	private String fileCategory;
	private String state;
	private List<String> optionList;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<String> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}
	public String getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductOption() {
		return productOption;
	}
	public void setProductOption(String productOption) {
		this.productOption = productOption;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	
}
