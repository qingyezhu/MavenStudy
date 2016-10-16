package com.qingyezhu.thrift.client.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.thrift.service.HelloThriftService;

public class HelloThriftClientDemo {

	private static final Logger logger = LoggerFactory.getLogger(HelloThriftClientDemo.class);

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 30000;

	public static void main(String[] args) {
		new HelloThriftClientDemo().startClient("hello: " + System.currentTimeMillis());
	}

	public void startClient(String str) {
		logger.info("hello thrift client start...........");
		TTransport transport = null;

		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			TProtocol protocol = new TBinaryProtocol(transport);
			HelloThriftService.Client client = new HelloThriftService.Client(protocol);
			transport.open();

			String result = client.sayHello(str);
			logger.info("str={}, result={}", str, result);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		} finally {
			if (transport != null) {
				transport.close();
			}
			logger.info("hello thrift client end...........");
		}

	}

}
