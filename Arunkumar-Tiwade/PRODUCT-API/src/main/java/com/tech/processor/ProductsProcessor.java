package com.tech.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tech.exception.NOOrderCreationException;
import com.tech.exception.NOOrderFoundException;
import com.tech.model.ProductBean;
import com.tech.model.ProductCreateOrderBean;
import com.tech.model.ResponseBean;
import com.tech.service.ProductServiceImpl;
import static java.lang.String.format;

@Component
public class ProductsProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsProcessor.class);

	@Autowired
	private ProductServiceImpl productServiceImpl;

	public ResponseEntity<List<ProductBean>> products() {
		LOG.info("Retrieving list of products accross all categories.");
		List<ProductBean> products = productServiceImpl.retrieveAllProducts();
		products.forEach(x -> x.setDiscount(x.getDiscount().concat("%")));
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductBean>> productsByCategory(String category, String lessThanPrice, String greaterThanPrice) {
		LOG.info("Validating request parameters to retrieve products by category.");
		if (StringUtils.isEmpty(category)) {
			LOG.info("Category '{}' is null or empty.", category);
			ProductBean messageBean = new ProductBean();
			messageBean.setMessage(format("Category '%s' is null or empty.", category));
			return new ResponseEntity<>(new ArrayList<ProductBean>(Arrays.asList(messageBean)), HttpStatus.BAD_REQUEST);
		}
		LOG.info(
				"Retrieving list of products by category or of a price less than or greater than a specified price for that product.");
		List<ProductBean> products = productServiceImpl.retrieveAllProducts();
		products.forEach(x -> x.setDiscount(x.getDiscount().concat("%")));
		List<ProductBean> productsByCategory = products.stream().filter(x -> x.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
		if (!StringUtils.isEmpty(lessThanPrice)) {
			productsByCategory = productsByCategory.stream()
					.filter(x -> Integer.valueOf(x.getPrice()) < Integer.valueOf(lessThanPrice))
					.collect(Collectors.toList());
		}
		if (!StringUtils.isEmpty(greaterThanPrice)) {
			productsByCategory = productsByCategory.stream()
					.filter(x -> Integer.valueOf(x.getPrice()) > Integer.valueOf(greaterThanPrice))
					.collect(Collectors.toList());
		}
		return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductBean>> productsByCompany(String company, String lessThanPrice, String greaterThanPrice) {
		LOG.info("Validating request parameters to retrieve products by Company.");
		if (StringUtils.isEmpty(company)) {
			LOG.info("Company '{}' is null or empty.", company);
			ProductBean messageBean = new ProductBean();
			messageBean.setMessage(format("Company '%s' is null or empty.", company));
			return new ResponseEntity<>(new ArrayList<ProductBean>(Arrays.asList(messageBean)), HttpStatus.BAD_REQUEST);
		}
		LOG.info(
				"Retrieving list of all the products by company or of a price less than or greater than a specified price for that company.");
		List<ProductBean> products = productServiceImpl.retrieveAllProducts();
		products.forEach(x -> x.setDiscount(x.getDiscount().concat("%")));
		List<ProductBean> productsByCompany = products.stream().filter(x -> x.getCompany().equalsIgnoreCase(company))
				.collect(Collectors.toList());
		if (!StringUtils.isEmpty(lessThanPrice)) {
			productsByCompany = productsByCompany.stream()
					.filter(x -> Integer.valueOf(x.getPrice()) < Integer.valueOf(lessThanPrice))
					.collect(Collectors.toList());
		}
		if (!StringUtils.isEmpty(greaterThanPrice)) {
			productsByCompany = productsByCompany.stream()
					.filter(x -> Integer.valueOf(x.getPrice()) > Integer.valueOf(greaterThanPrice))
					.collect(Collectors.toList());
		}
		return new ResponseEntity<>(productsByCompany, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductBean>> productsByCategoryDiscountPrice(String category) {
		LOG.info("Validating request parameters to retrieve discount price of products by category.");
		if (StringUtils.isEmpty(category)) {
			LOG.info("Category '{}' is null or empty.", category);
			ProductBean messageBean = new ProductBean();
			messageBean.setMessage(format("Category '%s' is null or empty.", category));
			return new ResponseEntity<>(new ArrayList<ProductBean>(Arrays.asList(messageBean)), HttpStatus.BAD_REQUEST);
		}
		List<ProductBean> products = productServiceImpl.retrieveAllProducts();
		List<ProductBean> productsByCategory = products.stream().filter(x -> x.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
		productsByCategory.forEach(x -> {
			int discount = 100 - Integer.valueOf(x.getDiscount());
			float discountPrice = Integer.valueOf(x.getPrice()) * (float) discount / 100;
			x.setDiscountPrice(String.valueOf(discountPrice));
			x.setDiscount(x.getDiscount().concat("%"));
		});
		return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
	}

	public ResponseEntity<ResponseBean> productsPlaceOrder(ProductCreateOrderBean productCreateOrder) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		LOG.info("Validating request parameters to place order for a product.");
		if (StringUtils.isEmpty(productCreateOrder) && StringUtils.isEmpty(productCreateOrder.getProductId())) {
			LOG.info("ProductId='{}' is null or empty.", productCreateOrder.getProductId());
			responseBean.setResponse(format("ProductId='%s' is null or empty.", productCreateOrder.getProductId()));
			return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
		}
		Integer totalAvilableStock = productServiceImpl.retrieveAvailableStockForProduct(productCreateOrder);
		Integer totalItemsOrdered = productServiceImpl.retrieveTotalOrderedItemForProduct(productCreateOrder);
		if (totalAvilableStock == totalItemsOrdered) {
			LOG.info("Could not create order because of no items available in Stock for the product.");
			responseBean
					.setResponse("Could not create order because of no items available in Stock for the product.");
			return new ResponseEntity<>(responseBean, HttpStatus.OK);
		}
		Integer actualAvailableStock = totalAvilableStock - totalItemsOrdered;
		LOG.info("actualAvailableStock={}", actualAvailableStock);
		if (actualAvailableStock < Integer.valueOf(productCreateOrder.getQuantity())) {
			LOG.info(format("Could not create order because of '%s' items available in Stock for the product.",
					actualAvailableStock));
			responseBean.setResponse(
					format("Could not create order because of '%s' items available in Stock for the product.",
							actualAvailableStock));
			return new ResponseEntity<>(responseBean, HttpStatus.OK);
		}
		ProductCreateOrderBean productCreateOrderBean = productServiceImpl.saveOrder(productCreateOrder);
		if (!StringUtils.isEmpty(productCreateOrderBean.getId())) {
			LOG.info("Order orderId='{}' created successfully", productCreateOrderBean.getId());
			responseBean.setResponse(format("Order orderId='%s' created successfully", productCreateOrderBean.getId()));
		} else {
			throw new NOOrderCreationException("No order created.");
		}
		return new ResponseEntity<>(responseBean, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductBean>> productsAvailableStocks() {
		List<ProductBean> products = productServiceImpl.retrieveAllProducts();
		products.forEach(product -> {
			ProductCreateOrderBean productCreateOrder = new ProductCreateOrderBean();
			productCreateOrder.setProductId(product.getId());
			Integer totalItemsOrdered = productServiceImpl.retrieveTotalOrderedItemForProduct(productCreateOrder);
			product.setAvailableItemInStock(String.valueOf(Integer.valueOf(product.getItemsInStock()) - totalItemsOrdered));
			product.setItemsInStock(null);
		});
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<ResponseBean> productsDeleteOrder(String orderId) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		LOG.info("Validating request parameters.");
		if (StringUtils.isEmpty(orderId)) {
			LOG.info("OrderId '{}' is null or empty.", orderId);
			responseBean.setResponse(format("OrderId '%s' is null or empty.", orderId));
			return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
		}
		int rows = productServiceImpl.deleteOrder(orderId);
		if (rows != 0) {
			LOG.info("Deleted orderId='{}' succesfully.", orderId);
			responseBean.setResponse(format("Deleted orderId='%s' succesfully.", orderId));
			return new ResponseEntity<>(responseBean, HttpStatus.OK);
		} else {
			LOG.info("No orderId='{}' found.", orderId);
			responseBean.setResponse(format("No orderId='%s' found.", orderId));
			return new ResponseEntity<>(responseBean, HttpStatus.NOT_FOUND);
		}
	}
}
