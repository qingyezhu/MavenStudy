package com.qingyezhu.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class NumberCountVo {

	private int num;
	private int count = 1;
	
	public NumberCountVo(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		NumberCountVo other = (NumberCountVo) obj;
		if (num != other.num)
			return false;
		other.count ++;
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
