package com.skipthedishes.products.builder;

import java.math.BigDecimal;

import com.skipthedishes.products.domain.Product;

public class ProductBuilder {

	private final Product product;

	public ProductBuilder() {
		this.product = new Product();
	}

	public ProductBuilder withId(Long id) {
		this.product.setId(id);
		return this;
	}

	public ProductBuilder withName(String name) {
		this.product.setName(name);
		return this;
	}

	public ProductBuilder withDescription(String description) {
		this.product.setDescription(description);
		return this;
	}

	public ProductBuilder withPrice(BigDecimal price) {
		this.product.setPrice(price);
		return this;
	}

	public ProductBuilder withStoreId(Long storeId) {
		this.product.setStoreId(storeId);
		return this;
	}

	public Product build() {
		return this.product;
	}

}
