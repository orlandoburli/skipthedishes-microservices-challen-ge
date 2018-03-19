package com.skipthedishes.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.orders.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
