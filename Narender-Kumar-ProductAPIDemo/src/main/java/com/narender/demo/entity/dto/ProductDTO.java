package com.narender.demo.entity.dto;

import java.math.BigDecimal;

public class ProductDTO {

	
	private String category;
	private String company;
	private String product;
	private String color;
	private String description;
	private BigDecimal price;
	private BigDecimal discount;
	private Integer itemInStock;

	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Integer getItemInStock() {
		return itemInStock;
	}
	public void setItemInStock(Integer itemInStock) {
		this.itemInStock = itemInStock;
	}
	
	
}
