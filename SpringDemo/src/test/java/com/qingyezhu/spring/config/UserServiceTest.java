package com.qingyezhu.spring.config;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.spring.config.model.User;
import com.qingyezhu.spring.config.service.UserService;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserServiceTest extends BaseUnitTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	public UserServiceTest() {
		super("classpath*:spring-config.xml");
	}

	@Test
	public void test() {
		UserService userService = getBean(UserService.class);
		logger.info("UserService={}", userService);
		
		User user = getBean("user");
		logger.info("User={}", user);
		
		userService.add(user);
		
		User user1 = new User();
		user1.setId(2);
		user1.setNick("no no");
		user1.setComments(Arrays.asList("h1", "a2", "c3"));
		userService.remove(2);
		
		userService.add(user1);
		
		logger.info("UserList={}", userService.query());
	}
}
