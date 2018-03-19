package com.skipthedishes.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByEmail(String email);

}
