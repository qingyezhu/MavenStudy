package com.qingyezhu.spring.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User {
	private Integer id;
	private String nick;
	private Integer age;
	private List<String> comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
