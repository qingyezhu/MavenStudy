package com.qingyezhu.thrift.server.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.thrift.service.HelloThriftService;


public class HelloThriftServerDemo {
	private static final Logger logger = LoggerFactory.getLogger(HelloThriftServerDemo.class);

	public static final int SERVER_PORT = 9091;

	public static void main(String[] args) {
		new HelloThriftServerDemo().startServer();
	}

	public void startServer() {
		logger.info("hello thrift server start...........");

		try {
			TProcessor tProcessor = new HelloThriftService.Processor<HelloThriftService.Iface>(new HelloThriftServiceImpl());
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args args = new TServer.Args(serverTransport);
			args.processor(tProcessor);
			args.protocolFactory(new TBinaryProtocol.Factory());

			TServer server = new TSimpleServer(args);
			server.serve();

		} catch (Exception e) {
			logger.error("catch error=" + e.getMessage(), e);
		}

	}

}
