package com.tech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class ProductCreateOrderBean {

	@ApiModelProperty(hidden = true)
	private String id;
	private String productId;
	private String quantity;

	public String getId() {
		return id;
	}

	@JsonIgnore
	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductCreateOrderBean [id=" + id + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
}
