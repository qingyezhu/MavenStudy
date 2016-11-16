package com.qingyezhu.spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingyezhu.spring.service.HandlerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-annotation.xml"})
public class HandlerServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private HandlerService handlerService;
	
	@Test
	public void testHandler() {
		handlerService.handler("abcd");
		handlerService.handlerService("welcome to");
	}
}
