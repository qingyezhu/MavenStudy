package com.qingyezhu.springmvc.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobTask extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(JobTask.class);
	
	private static int count = 0;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("c={}, t={}", count, System.currentTimeMillis());
	}

}
