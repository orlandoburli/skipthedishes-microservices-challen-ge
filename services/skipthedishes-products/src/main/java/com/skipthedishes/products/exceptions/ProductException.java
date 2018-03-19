package com.skipthedishes.products.exceptions;

public abstract class ProductException extends Exception {

	private static final long serialVersionUID = -3808758539353412462L;

	public ProductException(String message) {
		super(message);
	}
}
