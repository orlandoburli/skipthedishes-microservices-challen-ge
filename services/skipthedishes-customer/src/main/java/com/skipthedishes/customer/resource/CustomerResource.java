package com.skipthedishes.customer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.customer.exceptions.CustomerNotFoundException;
import com.skipthedishes.customer.exceptions.DuplicatedCustomerEmailException;
import com.skipthedishes.customer.exceptions.WeakPasswordException;
import com.skipthedishes.customer.service.CustomerService;
import com.skipthedishes.model.dto.CustomerDto;

@RestController
@RequestMapping("/Customer")
public class CustomerResource {

	private final CustomerService customerService;

	@Autowired
	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CustomerDto customerDto)
			throws DuplicatedCustomerEmailException, WeakPasswordException {
		this.customerService.create(customerDto);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/auth")
	public ResponseEntity<String> auth(@RequestBody CustomerDto customerDto) throws CustomerNotFoundException {
		final String authenticate = this.customerService.authenticate(customerDto.getEmail(),
				customerDto.getPassword());

		return new ResponseEntity<>(authenticate, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> get(@PathVariable("customerId") Long id) throws CustomerNotFoundException {

		final CustomerDto customer = this.customerService.findById(id);

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
