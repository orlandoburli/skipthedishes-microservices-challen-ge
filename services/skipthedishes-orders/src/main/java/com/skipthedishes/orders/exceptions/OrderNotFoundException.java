package com.skipthedishes.orders.exceptions;

public class OrderNotFoundException extends OrderException {

	private static final long serialVersionUID = -1201146234988767073L;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
