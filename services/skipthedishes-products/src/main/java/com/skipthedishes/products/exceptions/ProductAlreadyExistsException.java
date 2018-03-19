package com.skipthedishes.products.exceptions;

public class ProductAlreadyExistsException extends ProductException {

	private static final long serialVersionUID = -6244897204736444503L;

	public ProductAlreadyExistsException(String message) {
		super(message);
	}

}
