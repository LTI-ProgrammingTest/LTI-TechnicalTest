package com.narender.demo.entity;

import java.math.BigDecimal;

public class Product {

	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	private String category;
	private String company;
	private String product;
	private String color;
	private String description;
	private BigDecimal price;
	private BigDecimal discount;
	private Integer itemInStock;

	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(Long id, String category, String company, String product, String color, String description, BigDecimal price,
			BigDecimal discount, Integer itemInStock) {
		super();
		this.id = id;
		this.category = category;
		this.company = company;
		this.product = product;
		this.color = color;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.itemInStock = itemInStock;
	}

	public Product(String category, String company, String product, String color, String description, BigDecimal price,
			BigDecimal discount, Integer itemInStock) {
		super();
		this.category = category;
		this.company = company;
		this.product = product;
		this.color = color;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.itemInStock = itemInStock;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((itemInStock == null) ? 0 : itemInStock.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (itemInStock == null) {
			if (other.itemInStock != null)
				return false;
		} else if (!itemInStock.equals(other.itemInStock))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", company=" + company + ", product=" + product
				+ ", color=" + color + ", description=" + description + ", price=" + price + ", discount=" + discount
				+ ", itemInStock=" + itemInStock + "]";
	}
	
	
}
