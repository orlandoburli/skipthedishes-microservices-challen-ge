package com.skipthedishes.customer.exceptions;

public class WeakPasswordException extends CustomerException {

	private static final long serialVersionUID = 1646681767677833207L;

	public WeakPasswordException(String message) {
		super(message);
	}

}
