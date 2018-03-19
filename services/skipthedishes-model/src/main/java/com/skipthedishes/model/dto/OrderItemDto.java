package com.skipthedishes.model.dto;

import java.math.BigDecimal;

import com.skipthedishes.model.interfaces.IOrderItem;

public class OrderItemDto implements IOrderItem {

	private static final long serialVersionUID = 5643816433942223785L;

	private Long id;

	private Long productId;

	private BigDecimal price;

	private Long quantity;

	private BigDecimal total;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getProductId() {
		return this.productId;
	}

	@Override
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public Long getQuantity() {
		return this.quantity;
	}

	@Override
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public BigDecimal getTotal() {
		return this.total;
	}

	@Override
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
