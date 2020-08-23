package com.productsale.app.platform.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestException extends RuntimeException implements ExceptionMapper<BadRequestException> {

	private static final long serialVersionUID = 12L;

	public BadRequestException() {
		super("Bad Request by user");
	}

	public BadRequestException(String string) {
		super(string);
	}

	@Override
	public Response toResponse(BadRequestException exception) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(ExceptionResponseFactory.getException(Response.Status.BAD_REQUEST, exception.getMessage()))
				.type("application/json").build();
	}

}
