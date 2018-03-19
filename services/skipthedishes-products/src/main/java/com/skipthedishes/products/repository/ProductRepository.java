package com.skipthedishes.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.products.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameContainingIgnoreCaseOrderByName(String name);

	Optional<Product> findByNameAndStoreId(String name, Long storeId);

	List<Product> findByStoreId(Long storeId);
}
