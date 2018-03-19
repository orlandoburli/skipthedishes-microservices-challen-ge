package com.skipthedishes.model.dto;

import java.util.Calendar;

import com.skipthedishes.model.interfaces.ICustomer;

public class CustomerDto implements ICustomer {

	private static final long serialVersionUID = 8496800828721688820L;

	private Long id;

	private String email;

	private String name;

	private String address;

	private Calendar creation;

	private String password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getCreation() {
		return this.creation;
	}

	public void setCreation(Calendar creation) {
		this.creation = creation;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
