package com.qingyezhu.spring.config.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.spring.config.model.User;
import com.qingyezhu.spring.config.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final List<User> list = new ArrayList<User>();
	
	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		synchronized (list) {
			flag = list.add(user);
		}
		logger.info("add user:{}, flag:{}", new Object[]{user, flag});
		return flag;
	}

	@Override
	public boolean remove(Integer id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		synchronized (list) {
			for(User user : list){
				if(user.getId().equals(id)){
					flag = list.remove(user);
					break;
				}
			}
		}
		logger.info("remove id:{}, flag:{}", new Object[]{id, flag});
		return flag;
	}

	@Override
	public List<User> query() {
		// TODO Auto-generated method stub
		List<User> src = new ArrayList<User>();
		List<User> dest = list;
//		Collections.copy(dest, src);
		Collections.copy(src, dest);
		logger.info("dest={}", dest);
		return dest;
	}

}
