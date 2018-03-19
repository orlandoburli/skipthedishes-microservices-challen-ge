package com.skipthedishes.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.products.domain.Cousine;

public interface CousineRepository extends JpaRepository<Cousine, Long> {

	List<Cousine> findByNameContainingIgnoreCaseAndLanguageAndCountryOrderByName(String name, String language,
			String country);

	Optional<Cousine> findByName(String name);

	Optional<Cousine> findByNameAndLanguage(String name, String language);

	Optional<Cousine> findByNameAndLanguageAndCountry(String name, String language, String country);

	List<Cousine> findByLanguageAndCountry(String language, String country);
}
