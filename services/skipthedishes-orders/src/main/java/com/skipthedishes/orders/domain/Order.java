package com.skipthedishes.orders.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.skipthedishes.model.enums.OrderStatus;
import com.skipthedishes.model.interfaces.IOrder;
import com.skipthedishes.model.interfaces.IOrderItem;

@Entity
@Table(name = "tb_order")
public class Order implements IOrder {

	private static final long serialVersionUID = 1497521351698033608L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreationTimestamp
	private Calendar date;

	private Long customerId;

	private String deliveryAddress;

	private String contact;

	private Long storeId;

	private BigDecimal total;

	private OrderStatus status;

	@UpdateTimestamp
	private Calendar lastUpdate;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = OrderItem.class)
	@JoinColumn(name = "orderId")
	private List<IOrderItem> itens;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Calendar getDate() {
		return this.date;
	}

	@Override
	public void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public Long getCustomerId() {
		return this.customerId;
	}

	@Override
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	@Override
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public String getContact() {
		return this.contact;
	}

	@Override
	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public Long getStoreId() {
		return this.storeId;
	}

	@Override
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Override
	public BigDecimal getTotal() {
		return this.total;
	}

	@Override
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public OrderStatus getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public Calendar getLastUpdate() {
		return this.lastUpdate;
	}

	@Override
	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public List<IOrderItem> getItens() {
		return this.itens;
	}

	@Override
	public void setItens(List<IOrderItem> itens) {
		this.itens = itens;
	}
}
