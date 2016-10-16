package com.qingyezhu.spring.config;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseUnitTest {
	private String springXmlPath;
	private ClassPathXmlApplicationContext context;
	
	public BaseUnitTest(){
		
	}
	public BaseUnitTest(String springXmlPath){
		this.springXmlPath = springXmlPath;
	}
	
	@Before
	public void before(){
		if(StringUtils.isBlank(springXmlPath)){
			springXmlPath = "classpath*:spring-*.xml";
		}
		
		context = new ClassPathXmlApplicationContext(springXmlPath.split("[,\\s]+"));
		context.start();
	}
	
	@After
	public void after(){
		if(context != null){
			context.destroy();
		}
	}
	
	protected <T> T getBean(Class<T> clazz){
		return context.getBean(clazz);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getBean(String beanId){
		return (T) context.getBean(beanId);
	}
}
