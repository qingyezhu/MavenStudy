package com.qingyezhu.spring.aop;

import java.util.Arrays;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

	@Pointcut("execution(* com.qingyezhu.spring..*.*(..))")
//	@Pointcut("execution(* com.qingyezhu.spring.service.Handler*.*(..))")
	public void handlerPointCut(){
		
	}
	
	@Before("handlerPointCut()")
	public void doBefore(JoinPoint jp){
		logger.info("before----name={}, class={}, args={}", new Object[]{jp.getSignature().getName(), jp.getTarget().getClass(), jp.getArgs()});
	}
	
	/**
	 * 环绕通知
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	@Around("handlerPointCut()")
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
	
	public void doReturning(JoinPoint jp){
		logger.info("return-------name={}, class={}, args={}", new Object[]{jp.getSignature().getName(), jp.getTarget().getClass(), jp.getArgs()});
	}
}
