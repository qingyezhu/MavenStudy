package com.qingyezhu.common.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Animal implements Serializable, Cloneable, Comparable<Animal> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4219781293055444987L;
	private Integer id;
	private String name;

	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Animal(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
//		Animal animal = (Animal) super.clone();
//		return animal;
		return super.clone();
	}

	@Override
	public int compareTo(Animal o) {
		return new CompareToBuilder().append(id, o.id).append(name, o.name).toComparison();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
