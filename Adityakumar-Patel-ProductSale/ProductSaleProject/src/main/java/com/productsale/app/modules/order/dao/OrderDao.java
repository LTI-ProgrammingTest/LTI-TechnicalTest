package com.productsale.app.modules.order.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.productsale.app.modules.order.model.Order;

@RegisterBeanMapper(Order.class)
public interface OrderDao {

	@SqlQuery("select * from orders where id =?")
	Optional<Order> getOrderById(int id);
	
	@SqlUpdate("insert into orders (productId,price,quantity) values (:productId, :price, :quantity)")
	void placeOrder(@Bind("productId") int prodId, @Bind("price") double price, @Bind("quantity") int quantity);

	@SqlUpdate("delete from orders where id = ?")
	void deleteOrder(int id);

	@SqlQuery("select * from orders order by 1")
	List<Order> getAllOrders();
}
