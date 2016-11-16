package com.qingyezhu.spring.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qingyezhu.spring.model.User;
import com.qingyezhu.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final List<User> list = new ArrayList<User>();
	
	@Override
	public boolean add(User user) {
		boolean flag = false;
		if(user != null && user.getId() != null){
			synchronized (list) {
				flag = list.add(user);
			}
		}
		logger.info("add user:{}, flag:{}", new Object[]{user, flag});
		return flag;
	}

	@Override
	public boolean remove(Integer id) {
		boolean flag = false;
		if(id != null){
			synchronized (list) {
				for(User user : list){
					if(user.getId().equals(id)){
						flag = list.remove(user);
						break;
					}
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
		Collections.copy(dest, src);
		logger.info("dest={}", dest);
		return dest;
	}

	@Override
	public List<User> update(Integer id) {
		if(id ==  null){
			return Collections.emptyList();
		}
		synchronized (list) {
			for(User user: list){
				if(id.equals(user.getId())){
					user.setAge(new Random().nextInt(100));
				}
			}
		}
		return query();
	}

}
