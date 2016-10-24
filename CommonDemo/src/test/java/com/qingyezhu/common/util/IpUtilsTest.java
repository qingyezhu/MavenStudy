package com.qingyezhu.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(IpUtilsTest.class);
	@Test
	public void testIpToNumber(){
		String ipStr = "10.2.8.87";
		ipStr = "255.255.255.255";
		logger.info("ipStr={}, ipNum={}", ipStr, IpUtils.ipToNumber(ipStr));
	}
	
	@Test
	public void testNumberToIp(){
		long ipNum = 167905367;
		ipNum = 4294967295L;
		logger.info("ipNum={}, ipStr={}", ipNum, IpUtils.numberToIp(ipNum));
	}
}
