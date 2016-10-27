package com.qingyezhu.common.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.common.model.Channel;

public class DBUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(DBUtilsTest.class);

	@Test
	public void testConn() {
		DBUserInfo dbUserInfo = DBUserInfo.newBuilder().setType("mysql").setHost("10.10.52.197").setPort(3306)
				.setDbName("videomobiledbdev").setUsername("root").setPassword("198_3ffhew0bvfM").build();
		Connection conn = DBUtils.getConn(dbUserInfo);
		logger.info("DBUserInfo={}, Connection={}", dbUserInfo, conn);
	}

	@Test
	public void testQueryBean() {
		DBUserInfo dbUserInfo = DBUserInfo.newBuilder().setType("mysql").setHost("10.10.52.197").setPort(3306)
				.setDbName("videomobiledbdev").setUsername("root").setPassword("198_3ffhew0bvfM").build();
		Connection conn = DBUtils.getConn(dbUserInfo);

		Channel channel = DBUtils.queryBean(conn, Channel.class, "select id, name, group_id, channel_type from c_upgrade_channel where id = ?", 2);
		logger.info("Channel={}", channel);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("group_id", "groupId");
		map.put("channel_type", "channelType");
		channel = DBUtils.queryBean(conn, Channel.class, map, "select id, name, group_id, channel_type from c_upgrade_channel where id = ?", 2);
		logger.info("Channel={}", channel);
	}
	

	@Test
	public void testQueryBeanList() {
		DBUserInfo dbUserInfo = DBUserInfo.newBuilder().setType("mysql").setHost("10.10.52.197").setPort(3306)
				.setDbName("videomobiledbdev").setUsername("root").setPassword("198_3ffhew0bvfM").build();
		Connection conn = DBUtils.getConn(dbUserInfo);

		List<Channel> list = DBUtils.queryBeanList(conn, Channel.class, "select id, name, group_id, channel_type from c_upgrade_channel where group_id = ? limit 4", 100000000);
		logger.info("Channel={}", list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("group_id", "groupId");
		map.put("channel_type", "channelType");
		list = DBUtils.queryBeanList(conn, Channel.class, map, "select id, name, group_id, channel_type from c_upgrade_channel where group_id = ? limit 4", 100000000);
		logger.info("Channel={}", list);
	}
}
