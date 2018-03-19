package com.skipthedishes.model.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

public interface IProduct extends Serializable {

	Long getId();

	void setId(Long id);

	Long getStoreId();

	void setStoreId(Long storeId);

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

}