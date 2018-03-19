package com.skipthedishes.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.customer.domain.CustomerSession;

public interface CustomerSessionRepository extends JpaRepository<CustomerSession, String> {

}
