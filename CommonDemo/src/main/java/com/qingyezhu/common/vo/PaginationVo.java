package com.qingyezhu.common.vo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PaginationVo<T> {

	private Long count;
	private List<T> data;
	public PaginationVo(Long count, List<T> data) {
		this.count = count;
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public List<T> getData() {
		return data;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
