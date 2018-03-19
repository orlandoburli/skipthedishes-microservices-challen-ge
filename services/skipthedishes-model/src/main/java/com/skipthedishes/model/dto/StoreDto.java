package com.skipthedishes.model.dto;

import com.skipthedishes.model.interfaces.IStore;

public class StoreDto implements IStore {

	private static final long serialVersionUID = -3762066086457767686L;

	private Long id;

	private String name;

	private String address;

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
