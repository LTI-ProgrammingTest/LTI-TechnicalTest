package com.productsale.app.modules.product.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.productsale.app.modules.product.model.Product;

public class ProductFilter {

	private static Map<CategoryBy, Function<Product, String>> categoryByMap = new HashMap<>();

	static {
		categoryByMap.put(CategoryBy.Category, p -> p.getCategory());
		categoryByMap.put(CategoryBy.Company, p -> p.getCompany());
	}

	public static Function<Product, String> getCategoryFunction(CategoryBy categoryBy) {

		return categoryByMap.getOrDefault(categoryBy, categoryByMap.get(CategoryBy.Category));
	}

	public enum CategoryBy {

		Category, Company
	}
}
