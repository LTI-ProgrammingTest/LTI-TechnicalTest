package com.tech.service;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.exception.NOOrderFoundException;
import com.tech.mapper.ProductCreateOrderMapper;
import com.tech.mapper.ProductMapper;
import com.tech.model.ProductBean;
import com.tech.model.ProductCreateOrderBean;
import static java.lang.String.format;

@Component
public class ProductServiceImpl implements ProductService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private Jdbi jdbi;

	@Override
	public List<ProductBean> retrieveAllProducts() {
		List<ProductBean> products = new ArrayList<ProductBean>();
		try (Handle handle = jdbi.open()) {
			products = handle.createQuery("SELECT * FROM products ORDER BY id ASC").map(new ProductMapper()).list();
		}
		return products;
	}

	@Override
	public ProductCreateOrderBean saveOrder(ProductCreateOrderBean productCreateOrder) {
		ProductCreateOrderBean productCreateOrderBean = new ProductCreateOrderBean();
		try (Handle handle = jdbi.open()) {
			productCreateOrderBean = handle.createUpdate("INSERT INTO orders(product_id, quantity) VALUES (?, ?)")
					.bind(0, Integer.valueOf(productCreateOrder.getProductId()))
					.bind(1, Integer.valueOf(productCreateOrder.getQuantity()))
					.executeAndReturnGeneratedKeys()
					.map(new ProductCreateOrderMapper())
					.one();
			LOG.info("Order productCreateOrderBean='{}' saved succesfully", productCreateOrderBean);
		}
		return productCreateOrderBean;
	}

	@Override
	public Integer retrieveTotalOrderedItemForProduct(ProductCreateOrderBean productCreateOrder) {
		Integer totalItemsOrdered;
		try (Handle handle = jdbi.open()) {
			totalItemsOrdered = handle.createQuery("select sum(quantity) from orders where product_id = ?")
					.bind(0, Integer.valueOf(productCreateOrder.getProductId()))
					.mapTo(Integer.class)
					.one();
			totalItemsOrdered = totalItemsOrdered != null ? totalItemsOrdered : 0;
			LOG.info("Retrieved totalItemsOrdered={}", totalItemsOrdered);
		}
		return totalItemsOrdered;
	}

	@Override
	public Integer retrieveAvailableStockForProduct(ProductCreateOrderBean productCreateOrder) {
		Integer totalAvilableStock;
		try (Handle handle = jdbi.open()) {
			totalAvilableStock = handle.createQuery("select items_in_stock from products where id = ?")
					.bind(0, Integer.valueOf(productCreateOrder.getProductId()))
					.mapTo(Integer.class)
					.one();
			LOG.info("Retrieved totalAvilableStock={}", totalAvilableStock);
		}
		return totalAvilableStock;
	}

	@Override
	public int deleteOrder(String orderId) throws Exception {
		try (Handle handle = jdbi.open()) {
			return handle.execute("delete from orders where id = ?", Integer.valueOf(orderId));
		}
	}
}
