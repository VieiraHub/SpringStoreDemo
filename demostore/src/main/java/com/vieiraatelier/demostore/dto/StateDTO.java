package com.vieiraatelier.demostore.dto;

import java.io.Serializable;

import com.vieiraatelier.demostore.domain.State;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = 4209508474719076297L;

	private Integer id;
	private String name;

	public StateDTO() {
	}

	public StateDTO(State obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
