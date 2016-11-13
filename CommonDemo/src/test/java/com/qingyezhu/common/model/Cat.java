package com.qingyezhu.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Cat extends Animal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5915372012923336144L;

	private String catStr;

	public Cat() {
		super();
	}

	public Cat(Integer id, String name) {
		super(id, name);
	}

	public Cat(Integer id, String name, String catStr) {
		super(id, name);
		this.catStr = catStr;
	}

	public String getCatStr() {
		return catStr;
	}

	public void setCatStr(String catStr) {
		this.catStr = catStr;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
