package com.tech.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.model.ProductBean;
import com.tech.model.ProductCreateOrderBean;
import com.tech.model.ResponseBean;
import com.tech.processor.ProductsProcessor;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductsController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private ProductsProcessor productsProcessor;

	@ApiOperation(value = "Get the list of all the products across all the categories", response = ProductBean.class, responseContainer = "List")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list of products"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductBean>> products() {
		LOG.info("Received Request for products.");
		return productsProcessor.products();
	}

	@ApiOperation(value = "Get the list of all the products by category or of a price less than or greater than a specified price for that product", response = ProductBean.class, responseContainer = "List")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list of products by category or of a price less than or greater than a specified price for that product"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/products/categories/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductBean>> productsByCategory(@PathVariable(name = "category") String category,
			@RequestParam(name = "lessThanPrice", required = false) String lessThanPrice,
			@RequestParam(name = "greaterThanPrice", required = false) String greaterThanPrice) {
		LOG.info("Received Request for productsByCategory with category={}, lessThanPrice={}, greaterThanPrice={}",
				category, lessThanPrice, greaterThanPrice);
		return productsProcessor.productsByCategory(category, lessThanPrice, greaterThanPrice);
	}

	@ApiOperation(value = "Get the list of all the products by company or of a price less than or greater than a specified price for that company", response = ProductBean.class, responseContainer = "List")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list of products by company or of a price less than or greater than a specified price for that company"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/products/companies/{company}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductBean>> productsByCompany(@PathVariable(name = "company") String company,
			@RequestParam(name = "lessThanPrice", required = false) String lessThanPrice,
			@RequestParam(name = "greaterThanPrice", required = false) String greaterThanPrice) {
		LOG.info("Received Request for productsByCompany with company={}, lessThanPrice={}, greaterThanPrice={}", company, lessThanPrice,
				greaterThanPrice);
		return productsProcessor.productsByCompany(company, lessThanPrice, greaterThanPrice);		
	}

	@ApiOperation(value = "Get the discounted price of all the products by category", response = ProductBean.class, responseContainer = "List")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list of products discounted price of all the products by category"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/products/categories/{category}/discountPrice", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductBean>> productsByCategoryDiscountPrice(@PathVariable(name = "category") String category) {
		LOG.info("Received Request for productsByCategoryDiscountPrice with category={}", category);
		return productsProcessor.productsByCategoryDiscountPrice(category);
	}

	@ApiOperation(value = "Place order for a product", response = ResponseBean.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully placed order for product"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(value = "/products/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> productsPlaceOrder(@RequestBody ProductCreateOrderBean productCreateOrder) throws Exception {
		LOG.info("Received Request for ProductsPlaceOrder with productCreateOrder={}", productCreateOrder);
		return productsProcessor.productsPlaceOrder(productCreateOrder);
	}

	@ApiOperation(value = "Get the available stock details across products", response = ProductBean.class, responseContainer = "List")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list of available stock details across products"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/products/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductBean>> productsAvailableStocks() {
		LOG.info("Received Request for productsAvailableStocks.");
		return productsProcessor.productsAvailableStocks();
	}

	@ApiOperation(value = "Delete order for a product", response = ResponseBean.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully deleted order for product"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping("/products/order/{orderId}")
	public ResponseEntity<ResponseBean> productsDeleteOrder(@PathVariable(name = "orderId") String orderId) throws Exception {
		LOG.info("Received Request for productsDeleteOrder with orderId={}", orderId);
		return productsProcessor.productsDeleteOrder(orderId);
	}
}
