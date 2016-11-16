package com.qingyezhu.spring.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.spring.service.OperatorService;

@RunWith(BlockJUnit4ClassRunner.class)
public class OperatorServiceTest extends BaseUnitTest {

	private static final Logger logger = LoggerFactory.getLogger(OperatorServiceTest.class);

	public OperatorServiceTest() {
		super("classpath*:spring-config.xml");
	}

	@Test
	public void testOpeartor(){
		OperatorService operatorService = getBean(OperatorService.class);
		operatorService.operator(123L);
	}
	
	@Test
	public void testQuery(){
		OperatorService operatorService = getBean(OperatorService.class);
		operatorService.query(111L);
	}
	
	@Test
	public void testHandlerFinal(){
		OperatorService operatorService = getBean(OperatorService.class);
		operatorService.handler(111L);
	}
	
}
