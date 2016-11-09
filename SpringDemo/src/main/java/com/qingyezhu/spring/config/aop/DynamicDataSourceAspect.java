package com.qingyezhu.spring.config.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.spring.config.utils.DataSourceHolder;

public class DynamicDataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	public void beforeDataSource(JoinPoint jp){
		logger.info("method={}, args={}", jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
	}
	
	public Object aroundDataSource(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = null;
		try{
			DataSourceHolder.set(DataSourceHolder.DATA_SOURCE_SLAVE);
			obj = pjp.proceed();
		}finally{
			DataSourceHolder.clear();
		}
		return obj;
	}
}
