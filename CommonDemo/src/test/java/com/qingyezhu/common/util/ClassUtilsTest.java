package com.qingyezhu.common.util;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.common.dao.impl.UserDAO;

public class ClassUtilsTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassUtilsTest.class);

	@Test
	public void testGenericType(){
		logger.info("GenericType={}", Arrays.toString(ClassUtils.getGenericType(UserDAO.class)));
	}
	
	@Test
	public void testAllFields(){
		logger.info("AllFields={}", ClassUtils.getAllField(UserDAO.class));
	}
	
	@Test
	public void testAllMethods(){
		logger.info("AllMethods={}", ClassUtils.getAllMethod(UserDAO.class));
	}
}
