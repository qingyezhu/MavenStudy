package com.qingyezhu.spring.mapper;

import java.util.List;

import com.qingyezhu.spring.model.User;

public interface UserMapper {
	List<User> queryUser();
	
	boolean saveUser(User user);
}
