package com.skipthedishes.products.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.skipthedishes.model.interfaces.IProduct;

@Entity
public class Product implements IProduct {

	private static final long serialVersionUID = 6663682934692108145L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Please, inform the Store id")
	private Long storeId;

	@NotEmpty(message = "Please, inform the name of the product")
	@Column(length = 100)
	@Size(max = 100, message = "Please, inform {max} caracters or less")
	private String name;

	@NotEmpty(message = "Please, inform the description of the product")
	@Column(length = 500)
	@Size(max = 100, message = "Please, inform {max} caracters or less")
	private String description;

	@NotNull(message = "Please, inform the price of the product")
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

	@Override
	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}
