package com.qingyezhu.common.designpattern.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperateImpl implements IOperate {

	private static final Logger logger = LoggerFactory.getLogger(OperateImpl.class);
	@Override
	public void work(Integer id, String name) {
		logger.info("time={}, id={}, name={}", new Object[]{System.currentTimeMillis(), id, name});
	}
	@Override
	public final void operator(Long operatorId) {
		System.out.println("operatorId:=" + operatorId);
		logger.info("operatorId={}", operatorId);
	}

}
