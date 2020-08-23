package com.tech.model;

public class ProductBean {
	private String id;
	private String category;
	private String company;
	private String product;
	private String color;
	private String description;
	private String price;
	private String discount;
	private String itemsInStock;
	private String discountPrice;
	private String availableItemInStock;
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getItemsInStock() {
		return itemsInStock;
	}

	public void setItemsInStock(String itemsInStock) {
		this.itemsInStock = itemsInStock;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getAvailableItemInStock() {
		return availableItemInStock;
	}

	public void setAvailableItemInStock(String availableItemInStock) {
		this.availableItemInStock = availableItemInStock;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", category=" + category + ", company=" + company + ", product=" + product
				+ ", color=" + color + ", description=" + description + ", price=" + price + ", discount=" + discount
				+ ", itemsInStock=" + itemsInStock + ", discountPrice=" + discountPrice + ", availableItemInStock="
				+ availableItemInStock + ", message=" + message + "]";
	}
}
