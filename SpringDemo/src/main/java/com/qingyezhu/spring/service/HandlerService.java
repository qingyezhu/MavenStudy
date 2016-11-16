package com.qingyezhu.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandlerService extends AbstractHandler {
	@Autowired
	private OperatorService operatorService;
	
	//使用Cglib代理时，当前类一定不要是final，否则运行出错，由于Cglib是基于继承实现的
	@Override
	public void handlerService(String handler) {
		logger.info("handlerService={}", handler);
		operatorService.calc(1, 2);
		operatorService.getName(123, "hello");
	}

}
