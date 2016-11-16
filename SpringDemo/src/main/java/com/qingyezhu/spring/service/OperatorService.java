package com.qingyezhu.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
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
	
	void calc(Integer numa, Integer numb){
		logger.info("numa={}, numb={}", numa, numb);
	}
	
	protected String getName(Integer id, String name){
		String ret = String.format("id=%d, name=%s, timestap=%d", id, name, System.currentTimeMillis());
		logger.info("ret={}", ret);
		return ret;
	}
}
