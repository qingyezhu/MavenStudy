package com.qingyezhu.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	final String OPERATOR = "admin";
	public void operator(Long id){
		logger.info("operator: id={}, operator={}", id, OPERATOR);
	}
	
	public void query(Long id){
		logger.info("query id={}, operator={}", id, OPERATOR);
		operator(id);
	}
	
	public void handler(Long id){
		query(id);
		logger.info("handler id={}, operator={}", id, OPERATOR);
	}
}
