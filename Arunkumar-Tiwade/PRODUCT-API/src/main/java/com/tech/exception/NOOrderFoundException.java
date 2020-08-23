package com.tech.exception;

public class NOOrderFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NOOrderFoundException(final String message) {
		super(message);
	}
}
