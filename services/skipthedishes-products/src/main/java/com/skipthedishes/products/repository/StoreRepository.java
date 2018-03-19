package com.skipthedishes.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.products.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

	List<Store> findByCousineId(Long cousineId);

	List<Store> findByNameContainingIgnoreCase(String name);

	Optional<Store> findByNameIgnoreCase(String name);
}
