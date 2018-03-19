package com.skipthedishes.products.exceptions;

public class ProductNotFoundException extends ProductException {

	private static final long serialVersionUID = 328515946042548480L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
