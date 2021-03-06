package com.qingyezhu.spring.config;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingyezhu.spring.mapper.AreaMapper;
import com.qingyezhu.spring.mapper.UserMapper;
import com.qingyezhu.spring.model.Area;
import com.qingyezhu.spring.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class SpringMyBatisTest extends AbstractJUnit4SpringContextTests{

	private static final Logger logger = LoggerFactory.getLogger(SpringMyBatisTest.class);
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void testUser(){
		List<User> userList = userMapper.queryUser();
		logger.info("before list={}", userList);
		
		User user = new User();
		user.setNick("哈啊哈" + System.currentTimeMillis());
		logger.info("before={}, boolean={}, after={}", user, userMapper.saveUser(user), user);
		
		userList = userMapper.queryUser();
		logger.info("after list={}", userList);
	}
	
	@Resource
	private AreaMapper areaMapper;
	
	@Test
	public void testArea(){
		List<Area> areaList = areaMapper.queryArea();
		logger.info("list={}", areaList);
		Area area = new Area();
		area.setCode(new Random().nextInt());
		area.setName("地区" + System.currentTimeMillis());
		areaMapper.saveArea(area);
		areaList = areaMapper.queryArea();
		logger.info("list={}", areaList);
	}		
}
