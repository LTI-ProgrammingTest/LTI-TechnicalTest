package com.productsale.app.modules.order.service;

import java.util.List;

import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.productsale.app.modules.order.dao.OrderDao;
import com.productsale.app.modules.order.model.Order;
import com.productsale.app.modules.product.model.Product;
import com.productsale.app.modules.product.service.ProductService;
import com.productsale.app.platform.database.JDBIConnection;
import com.productsale.app.platform.exception.NoStockAvailableException;
import com.productsale.app.platform.exception.OrderNotFoundException;

public class OrderService implements IOrderService {

	private static final OrderService instance = new OrderService();

	// Single ton instance
	private static final ProductService productService = ProductService.getInstance();
	private final static Logger log = LoggerFactory.getLogger(ProductService.class);

	private OrderDao orderDao;

	private OrderService() {
		orderDao = JDBIConnection.jdbi().onDemand(OrderDao.class);
	}

	public static OrderService getInstance() {
		return instance;
	}

	@Transaction
	public void placeOrder(Order order) {

		synchronized (this) {

			int remainStock = productService.getRemainStock(order.getProductId());
			int quantity = order.getQuantity();

			remainStock = remainStock - quantity;
			if (remainStock < 0) {
				throw new NoStockAvailableException();
			}

			productService.updateStock(remainStock, order.getProductId());
			insertOrder(order.getProductId(), order.getPrice(), order.getQuantity());

			log.info("Placed order :" + order);

		}
	}

	private void insertOrder(int productId, double price, int quanity) {
		orderDao.placeOrder(productId, price, quanity);
	}

	
	@Transaction
	public void removeOrder(int id) {
		synchronized (this) {
			Order findOrder = orderDao.getOrderById(id).orElseThrow(OrderNotFoundException::new);
			Product product = productService.getProductById(findOrder.getProductId());

			productService.updateStock(product.getStock() + findOrder.getQuantity(), product.getId());
			orderDao.deleteOrder(id);
			log.info("remove order id :" + id);
		}

	}

	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}
}
