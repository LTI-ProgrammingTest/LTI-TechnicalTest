package com.tech.exception;

public class NOOrderCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public NOOrderCreationException(final String message) {
		super(message);
	}
}
