package com.skipthedishes.model.interfaces;

import java.io.Serializable;

public interface IStore extends Serializable {

	Long getId();

	void setId(Long id);

	String getName();

	void setName(String name);

	String getAddress();

	void setAddress(String address);

	Long getCousineId();

	void setCousineId(Long cousineId);

}