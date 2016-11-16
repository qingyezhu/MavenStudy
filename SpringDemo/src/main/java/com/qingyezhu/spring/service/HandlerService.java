package com.qingyezhu.spring.service;

import org.springframework.stereotype.Service;

@Service
public final class HandlerService extends AbstractHandler {

	@Override
	public void handlerService(String handler) {
		logger.info("handlerService={}", handler);
	}

}
