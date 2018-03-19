package com.skipthedishes.model.dto;

import java.math.BigDecimal;

import com.skipthedishes.model.interfaces.IProduct;

public class ProductDto implements IProduct {

	private static final long serialVersionUID = -5993388633771526509L;

	private Long id;

	private Long storeId;

	private String name;

	private String description;

	private BigDecimal price;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BigDecimal getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
