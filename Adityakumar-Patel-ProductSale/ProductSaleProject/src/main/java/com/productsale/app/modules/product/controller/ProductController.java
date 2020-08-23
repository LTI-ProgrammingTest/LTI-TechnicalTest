package com.productsale.app.modules.product.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.productsale.app.modules.product.model.Product;
import com.productsale.app.modules.product.service.ProductFilter;
import com.productsale.app.modules.product.service.ProductService;

@Path("products")
public class ProductController {

	private ProductService productService = ProductService.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getStock() {

		return productService.getAllProducts();
	}

	@GET
	@Path("/category")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, List<Product>> getProductsSortByCategory(@QueryParam("grePrice") double greaterPrice,
			@QueryParam("lesPrice") double lessPrice) {

		return productService.getAllProductsBy(ProductFilter.CategoryBy.Category, greaterPrice, lessPrice);
	}

	@GET
	@Path("/company")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, List<Product>> getProductsSortByCompanies(@QueryParam("grePrice") double greaterPrice,
			@QueryParam("lesPrice") double lessPrice) {

		return productService.getAllProductsBy(ProductFilter.CategoryBy.Company, greaterPrice, lessPrice);
	}

	@GET
	@Path("/discountprice")
	@Produces(MediaType.APPLICATION_JSON)
	public List<?> getProductsByDiscountPrice() {
		return productService.getProductsByDiscountPrice();
	}

	@GET
	@Path("/stock/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<?, ?> getStockDetail(@PathParam("id") int productId) {

		return productService.getStockDetail(productId);
	}

}
