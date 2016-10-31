package com.qingyezhu.spring.config.aop;

import java.util.Arrays;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAspect {

	private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);
	
	public void doBefore(JoinPoint jp){
		logger.info("before----name={}, class={}, args={}", new Object[]{jp.getSignature().getName(), jp.getTarget().getClass(), jp.getArgs()});
	}
	
	/**
	 * 环绕通知
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		StopWatch clock = new StopWatch();
		clock.start();
		
		Object ret = null;
		try {
			ret = pjp.proceed();
		} catch (Throwable e) {
			logger.error("catch error=" + e.getMessage(), e);
			throw e;
		}
		
		clock.stop();
		logger.info("ret={}, executeTime={}, name={}, class={}, args={}", new Object[]{ret, clock.getTime(), pjp.getSignature().getName(), pjp.getTarget().getClass(), Arrays.toString(pjp.getArgs())});
		return ret;
	}
	
	public void doAfter(JoinPoint jp){
		logger.info("after-------name={}, class={}, args={}", new Object[]{jp.getSignature().getName(), jp.getTarget().getClass(), jp.getArgs()});
	}
	
	public void doThrowing(JoinPoint jp, Throwable ex){
		logger.info("exception------name={}, class={}, args={}, exception={}", new Object[]{jp.getSignature().getName(), jp.getTarget().getClass(), jp.getArgs(), ex.getMessage()});
	}
}
