package com.tech.model;

public class ResponseBean {

	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "SuccessResponseBean [response=" + response + "]";
	}
}
