package com.cognizant.exception;

public class ServiceDownException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceDownException(String msg) {
		super(msg);
	}

}
