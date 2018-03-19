package com.skipthedishes.customer.exceptions;

public abstract class CustomerException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerException(String message) {
		super(message);
	}
}
