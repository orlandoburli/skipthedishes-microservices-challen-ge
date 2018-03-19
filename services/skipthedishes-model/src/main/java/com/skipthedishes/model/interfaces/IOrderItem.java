package com.skipthedishes.model.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

public interface IOrderItem extends Serializable {

	Long getId();

	void setId(Long id);

	Long getProductId();

	void setProductId(Long productId);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	Long getQuantity();

	void setQuantity(Long quantity);

	BigDecimal getTotal();

	void setTotal(BigDecimal total);

}