package com.productsale.app.modules.product.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.productsale.app.modules.product.dao.ProductDao;
import com.productsale.app.modules.product.model.Product;
import com.productsale.app.modules.product.service.ProductFilter.CategoryBy;
import com.productsale.app.platform.database.JDBIConnection;
import com.productsale.app.platform.exception.BadRequestException;
import com.productsale.app.platform.exception.ProductNotFoundException;

public class ProductService implements IProductService {

	private static final ProductService Instance = new ProductService();

	private final static Logger log = LoggerFactory.getLogger(ProductService.class);

	private ProductDao productDao;

	public static ProductService getInstance() {
		return Instance;
	}

	private ProductService() {
		productDao = JDBIConnection.jdbi().onDemand(ProductDao.class);

	}

	public Product getProductById(int productId) {

		return productDao.getProduct(productId).orElseThrow(ProductNotFoundException::new);
	}

	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public Map<?, ?> getStockDetail(int productId) {
		Optional<Product> product = productDao.getProduct(productId);

		return product.map((p) -> {
			Map<String, Object> m = new HashMap<>();
			m.put("id", p.getId());
			m.put("name", p.getProduct());
			m.put("remainStock", p.getStock());
			return m;
		}).orElseThrow(ProductNotFoundException::new);
	}

	public Map<String, List<Product>> getAllProductsBy(CategoryBy categoryBy, double grePrice, double lesPrice) {

		log.info("Called getAllProductsBy method");

		if (grePrice < 0 || lesPrice < 0) {
			throw new BadRequestException("greater or lesser price should be positive");
		}

		List<Product> products;

		if (grePrice > 0 && lesPrice > 0) {
			products = productDao.getAllProductsInBetweenPrice(grePrice, lesPrice);
		} else if (lesPrice > 0) {
			products = productDao.getAllProductsLessThanPrice(lesPrice);
		} else {
			products = productDao.getAllProductsGreaterThanPrice(grePrice > 0 ? grePrice : 0);
		}

		Function<Product, String> categoryFunction = ProductFilter.getCategoryFunction(categoryBy);
		Map<String, List<Product>> categoryMap = products.stream().collect(Collectors.groupingBy(categoryFunction));
		return categoryMap;
	}

	public List<Map<String, Object>> getProductsByDiscountPrice() {

		log.info("Called getProductsByDiscountPrice method");

		List<Product> products = getAllProducts();

		return products.stream().map(e -> {
			Map<String, Object> m = new LinkedHashMap<>();

			m.put("id", e.getId());
			m.put("company", e.getCompany());
			m.put("category", e.getCategory());
			m.put("product", e.getProduct());
			m.put("color", e.getColor());
			m.put("price", e.getPrice());
			m.put("discount", e.getDiscount());
			m.put("discountPrice", e.getPrice() - ((e.getPrice() * e.getDiscount()) / 100));
			m.put("stock", e.getStock());
			m.put("description", e.getDescription());

			return m;
		}).collect(Collectors.toList());
	}

	public int getRemainStock(int productId) {

		log.info("Called getRemainStock method of product :" + productId);
		return productDao.getRemainStock(productId);
	}

	public void updateStock(int remainStock, int productId) {
		log.info("Called updateStock :" + remainStock);
		productDao.updateStockForProduct(remainStock, productId);
	}
}
