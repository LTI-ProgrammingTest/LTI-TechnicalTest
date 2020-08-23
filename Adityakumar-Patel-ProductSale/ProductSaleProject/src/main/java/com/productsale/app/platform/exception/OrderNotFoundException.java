package com.productsale.app.platform.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OrderNotFoundException extends RuntimeException implements ExceptionMapper<OrderNotFoundException> {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
		super("No order found");
	}

	public OrderNotFoundException(String string) {
		super(string);
	}

	@Override
	public Response toResponse(OrderNotFoundException exception) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.NOT_FOUND)
				.entity(ExceptionResponseFactory.getException(Response.Status.NOT_FOUND, exception.getMessage()))
				.type("application/json").build();

	}

}
