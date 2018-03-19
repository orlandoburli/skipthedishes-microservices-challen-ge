package com.skipthedishes.model.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.skipthedishes.model.enums.OrderStatus;

public interface IOrder extends Serializable {

	Long getId();

	void setId(Long id);

	Calendar getDate();

	void setDate(Calendar date);

	Long getCustomerId();

	void setCustomerId(Long customerId);

	String getDeliveryAddress();

	void setDeliveryAddress(String deliveryAddress);

	String getContact();

	void setContact(String contact);

	Long getStoreId();

	void setStoreId(Long storeId);

	BigDecimal getTotal();

	void setTotal(BigDecimal total);

	OrderStatus getStatus();

	void setStatus(OrderStatus status);

	Calendar getLastUpdate();

	void setLastUpdate(Calendar lastUpdate);

	List<IOrderItem> getItens();

	void setItens(List<IOrderItem> itens);

}