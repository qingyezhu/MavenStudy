package com.qingyezhu.spring.config.mapper;

import java.util.List;

import com.qingyezhu.spring.config.model.User;

public interface UserMapper {
	List<User> queryUser();
	
	boolean saveUser(User user);
}
