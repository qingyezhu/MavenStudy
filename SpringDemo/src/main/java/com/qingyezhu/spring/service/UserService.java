package com.qingyezhu.spring.service;

import java.util.List;

import com.qingyezhu.spring.model.User;

public interface UserService {
	boolean add(User user);
	
	boolean remove(Integer id);
	
	List<User> query();
	
	List<User> update(Integer id);
}
