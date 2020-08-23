package com.productsale.app.platform.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoStockAvailableException extends RuntimeException implements ExceptionMapper<NoStockAvailableException> {

	private static final long serialVersionUID = 12L;

	public NoStockAvailableException() {
		super("No stock available for selected product");
	}

	public NoStockAvailableException(String string) {
		super(string);
	}

	@Override
	public Response toResponse(NoStockAvailableException exception) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(ExceptionResponseFactory.getException(Response.Status.NOT_FOUND, exception.getMessage()))
				.type("application/json").build();
	}

}
