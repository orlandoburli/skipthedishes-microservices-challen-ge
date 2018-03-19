package com.skipthedishes.model.dto;

import java.io.Serializable;

public class CousineDto implements Serializable {

	private static final long serialVersionUID = -3387914292319144930L;

	private Long id;

	private String name;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
