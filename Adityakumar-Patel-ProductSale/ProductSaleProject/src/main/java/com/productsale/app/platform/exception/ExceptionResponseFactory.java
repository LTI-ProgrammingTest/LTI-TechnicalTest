package com.productsale.app.platform.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

public class ExceptionResponseFactory {

	private final static Map<Response.Status, ExceptionResponse> map = new HashMap<Response.Status, ExceptionResponse>();

	public static ExceptionResponse getException(Response.Status status, String message) {
		ExceptionResponse eR = map.get(status);

		if (eR == null) {
			eR = new ExceptionResponse();
			eR.setStatus(status.getStatusCode());
			map.put(status, eR);
		}
		eR.setTimestamp(new Date());
		eR.setMessage(message);
		return eR;

	}
}
