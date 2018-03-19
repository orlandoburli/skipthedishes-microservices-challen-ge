package com.skipthedishes.products.builder;

import com.skipthedishes.products.domain.Cousine;

public class CousineBuilder {

	private final Cousine cousine;

	public CousineBuilder() {
		this.cousine = new Cousine();
	}

	public CousineBuilder withName(String name) {
		this.cousine.setName(name);
		return this;
	}

	public CousineBuilder withLanguage(String language) {
		this.cousine.setLanguage(language);
		return this;
	}

	public CousineBuilder withCountry(String country) {
		this.cousine.setCountry(country);
		return this;
	}

	public Cousine build() {
		return this.cousine;
	}
}
