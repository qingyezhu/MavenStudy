package com.qingyezhu.spring.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.qingyezhu.spring.service.HandlerService;

@RunWith(BlockJUnit4ClassRunner.class)
public class HandlerServiceTest extends BaseUnitTest {

	public HandlerServiceTest() {
		super("classpath*:spring-config.xml");

	}

	@Test
	public void testHandler() {
		HandlerService handlerService = getBean("handlerService");
		handlerService.handler("abc");
	}
}
