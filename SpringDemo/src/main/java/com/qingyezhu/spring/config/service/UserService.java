package com.qingyezhu.spring.config.service;

import java.util.List;

import com.qingyezhu.spring.config.model.User;

public interface UserService {
	boolean add(User user);
	
	boolean remove(Integer id);
	
	List<User> query();
}
