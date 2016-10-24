package com.qingyezhu.common.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5320366752835405652L;
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
