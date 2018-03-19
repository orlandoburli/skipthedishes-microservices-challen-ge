package com.skipthedishes.orders.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.skipthedishes.model.interfaces.IOrderItem;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements IOrderItem {

	private static final long serialVersionUID = 6290790270370289243L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
