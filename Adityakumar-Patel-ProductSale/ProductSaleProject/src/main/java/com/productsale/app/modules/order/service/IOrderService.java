package com.productsale.app.modules.order.service;

import java.util.List;

import com.productsale.app.modules.order.model.Order;

public interface IOrderService {

	void placeOrder(Order order);

	void removeOrder(int id);

	List<Order> getAllOrders();
}
