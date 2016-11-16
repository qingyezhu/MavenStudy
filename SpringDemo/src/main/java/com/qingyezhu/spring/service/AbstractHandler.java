package com.qingyezhu.spring.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingyezhu.spring.model.User;

public abstract class AbstractHandler extends AbstractParentHandler{
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init(){
		logger.info("UserService={}", userService);
	}
	
	@Override
	public final void handler(String handlerName){
		//当用代理的方法被final修饰时，其对象中的属性并不会注入进去
		if(handlerName.equals(DEFAULT_HANDLER)){
			handlerDefault(handlerName);
		}else{
			handlerService(handlerName);
		}
		logger.info("handler={}", handlerName);
		logger.info("UserService={}", userService);
		if(userService != null){
			//被调用的方法是被代理，且当前方法是final时，则需要进行判断，因为此时，被调用的对象userService没有被注入到代理对象中，故为Null
			userService.add(new User());
		}
	}
	
	@Override
	public void handlerDefault(String handlerName) {
		logger.info("handlerDefault={}", handlerName);
	}
}
