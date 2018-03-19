package com.skipthedishes.customer.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.skipthedishes.customer.domain.enums.CustomerSessionStatus;

@Entity
public class CustomerSession {

	@Id
	private String id;

	@NotNull
	private Long customerId;

	@CreationTimestamp
	private Calendar creationDate;

	@NotNull
	private CustomerSessionStatus status;

	private Calendar logoffDate;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Calendar getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public CustomerSessionStatus getStatus() {
		return this.status;
	}

	public void setStatus(CustomerSessionStatus status) {
		this.status = status;
	}

	public Calendar getLogoffDate() {
		return this.logoffDate;
	}

	public void setLogoffDate(Calendar logoffDate) {
		this.logoffDate = logoffDate;
	}
}
