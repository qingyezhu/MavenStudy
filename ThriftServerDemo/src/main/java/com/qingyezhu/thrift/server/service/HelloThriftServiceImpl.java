package com.qingyezhu.thrift.server.service;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.thrift.service.HelloThriftService.Iface;

public class HelloThriftServiceImpl implements Iface {

	private static final Logger logger = LoggerFactory.getLogger(HelloThriftServiceImpl.class);

	public String sayHello(String str) throws TException {
		String ret = String.format("hi, %s start study thrift, times:%d", str, System.currentTimeMillis());
		logger.info("ret={}", ret);
		return ret;
	}

}
