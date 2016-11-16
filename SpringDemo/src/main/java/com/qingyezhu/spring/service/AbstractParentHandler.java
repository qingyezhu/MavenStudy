package com.qingyezhu.spring.service;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.spring.utils.DataSourceHolder;

public abstract class AbstractParentHandler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final String DEFAULT_HANDLER = "abc";
	
	@Resource(name = "appConfig")
	protected Map<String, String> appConfig;

	@Resource(name = "dataSourceHolder")
	protected DataSourceHolder dataSourceHolder;
	
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
