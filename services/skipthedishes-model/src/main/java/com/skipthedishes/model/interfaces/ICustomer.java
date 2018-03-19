package com.skipthedishes.model.interfaces;

import java.io.Serializable;
import java.util.Calendar;

public interface ICustomer extends Serializable {

	String getEmail();
	
	void setEmail(String email);

	String getName();

	void setName(String name);

	String getAddress();

	void setAddress(String address);

	Calendar getCreation();

	void setCreation(Calendar creation);

	String getPassword();

	void setPassword(String password);

	Long getId();

	void setId(Long id);
}