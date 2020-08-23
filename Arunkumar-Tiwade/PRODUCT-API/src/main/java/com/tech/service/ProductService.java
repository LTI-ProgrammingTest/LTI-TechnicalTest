package com.tech.service;

import java.util.List;

import com.tech.model.ProductBean;
import com.tech.model.ProductCreateOrderBean;

public interface ProductService {

	public List<ProductBean> retrieveAllProducts();

	public ProductCreateOrderBean saveOrder(ProductCreateOrderBean productCreateOrder);

	public Integer retrieveTotalOrderedItemForProduct(ProductCreateOrderBean productCreateOrder);

	public Integer retrieveAvailableStockForProduct(ProductCreateOrderBean productCreateOrder);

	public int deleteOrder(String orderId) throws Exception;
}
