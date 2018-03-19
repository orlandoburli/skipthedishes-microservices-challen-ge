package com.skipthedishes.customer.exceptions;

public class DuplicatedCustomerEmailException extends CustomerException {

	private static final long serialVersionUID = 7860339215220964862L;

	public DuplicatedCustomerEmailException(String message) {
		super(message);
	}

}
