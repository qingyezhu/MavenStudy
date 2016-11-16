package com.qingyezhu.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractParentHandler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final String DEFAULT_HANDLER = "abc";

	public void handler(String handlerName) {
		if (handlerName.equals(DEFAULT_HANDLER)) {
			handlerDefault(handlerName);
		} else {
			handlerService(handlerName);
		}
		logger.info("handler={}", handlerName);
	}

	public abstract void handlerDefault(String handlerName);
	public abstract void handlerService(String handler);
}
