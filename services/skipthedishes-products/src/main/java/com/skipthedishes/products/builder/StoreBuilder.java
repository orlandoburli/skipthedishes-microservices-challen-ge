package com.skipthedishes.products.builder;

import com.skipthedishes.products.domain.Store;

public class StoreBuilder {

	private final Store store;

	public StoreBuilder() {
		this.store = new Store();
	}

	public StoreBuilder withName(String name) {
		this.store.setName(name);
		return this;
	}

	public StoreBuilder withAddress(String address) {
		this.store.setAddress(address);
		return this;
	}

	public StoreBuilder withCousineId(Long cousineId) {
		this.store.setCousineId(cousineId);
		return this;
	}

	public Store build() {
		return this.store;
	}
}
