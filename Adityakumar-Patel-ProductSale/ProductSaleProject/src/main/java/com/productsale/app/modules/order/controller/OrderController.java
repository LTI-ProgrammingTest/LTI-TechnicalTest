package com.productsale.app.modules.order.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.productsale.app.modules.order.model.Order;
import com.productsale.app.modules.order.service.OrderService;

@Path("order")
public class OrderController {

	private OrderService orderSerive = OrderService.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrders() {

		return orderSerive.getAllOrders();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCountry(Order order) {
		orderSerive.placeOrder(order);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCountry(@PathParam("id") int id) {
		orderSerive.removeOrder(id);
		return Response.ok().build();

	}
}
