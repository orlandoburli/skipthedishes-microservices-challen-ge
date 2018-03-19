package com.skipthedishes.products.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.skipthedishes.model.interfaces.IStore;

@Entity
public class Store implements IStore {

	private static final long serialVersionUID = -7694753045210988380L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = 100)
	private String name;

	@Column(length = 300)
	private String address;

	@NotNull
	private Long cousineId;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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
	public Long getCousineId() {
		return this.cousineId;
	}

	@Override
	public void setCousineId(Long cousineId) {
		this.cousineId = cousineId;
	}
}
