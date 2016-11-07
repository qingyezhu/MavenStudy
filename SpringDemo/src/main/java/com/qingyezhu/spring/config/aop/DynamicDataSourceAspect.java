package com.qingyezhu.spring.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.qingyezhu.spring.config.utils.DataSourceHolder;

public class DynamicDataSourceAspect {

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
