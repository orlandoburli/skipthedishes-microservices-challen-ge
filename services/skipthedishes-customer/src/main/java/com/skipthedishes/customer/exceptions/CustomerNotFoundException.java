package com.skipthedishes.customer.exceptions;

public class CustomerNotFoundException extends CustomerException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
