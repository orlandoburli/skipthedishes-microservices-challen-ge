package com.skipthedishes.orders.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.dto.OrderDto;
import com.skipthedishes.orders.exceptions.OrderException;
import com.skipthedishes.orders.service.OrderService;

@RestController
@RequestMapping("/Order")
public class OrderResource {

	private final OrderService orderService;

	@Autowired
	public OrderResource(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderDto> create(@RequestBody OrderDto order) throws OrderException {
		final OrderDto orderCreated = this.orderService.create(order);

		return new ResponseEntity<>(orderCreated, HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Long orderId) throws OrderException {
		final OrderDto order = this.orderService.findById(orderId);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/customer")
	public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@RequestHeader("AUTHENTICATION_TOKEN") String token)
			throws OrderException {
		final List<OrderDto> order = this.orderService.findByTokenSession(token);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
