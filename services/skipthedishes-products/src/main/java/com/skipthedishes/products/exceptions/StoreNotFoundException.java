package com.skipthedishes.products.exceptions;

public class StoreNotFoundException extends ProductException {

	private static final long serialVersionUID = 8862215636454627246L;

	public StoreNotFoundException(String message) {
		super(message);
	}

}
