package com.productsale.app.modules.product.service;

import java.util.List;
import java.util.Map;

import com.productsale.app.modules.product.model.Product;
import com.productsale.app.modules.product.service.ProductFilter.CategoryBy;

public interface IProductService {

	Product getProductById(int productId);

	List<Product> getAllProducts();

	List<Map<String, Object>> getProductsByDiscountPrice();

	Map<String, List<Product>> getAllProductsBy(CategoryBy categoryBy, double grePrice, double lesPrice);

	int getRemainStock(int productId);

	Map<?, ?> getStockDetail(int productId);

	void updateStock(int remainStock, int productId);
}
