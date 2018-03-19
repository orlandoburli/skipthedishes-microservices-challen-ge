package com.skipthedishes.customer.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.skipthedishes.model.interfaces.ICustomer;

@Entity
public class Customer implements ICustomer {

	private static final long serialVersionUID = 155455253089108690L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 200, nullable = false)
	@NotEmpty(message = "Please, inform your e-mail")
	private String email;

	@Column(length = 200, nullable = false)
	@NotEmpty(message = "Please, inform your name")
	private String name;

	@Column(length = 200, nullable = false)
	@NotEmpty(message = "Please, inform your address")
	private String address;

	@CreationTimestamp
	private Calendar creation;

	@NotEmpty(message = "Please, inform your pasword")
	private String password;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
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
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Calendar getCreation() {
		return this.creation;
	}

	@Override
	public void setCreation(Calendar creation) {
		this.creation = creation;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
}
