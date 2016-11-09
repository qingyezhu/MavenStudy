
package com.qingyezhu.common.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperClientTest {

	private static final Logger logger = LoggerFactory.getLogger(ZookeeperClientTest.class);

	private ZookeeperClient client;

	@Before
	public void create() {
		client = new ZookeeperClient();
	}

	private String path = "/test";
	private String charset = "utf-8";

	@Test
	public void testExists() {
		logger.info("exists={}", client.exists(path));
		
		logger.info("test exists={}", client.exists(path, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				//该事件监听只有一次机会，一旦监听接收过消息，就不再接收了
				String path = event.getPath();
				logger.info("test path={}, type={}, state={}", new Object[]{path, event.getType(), event.getState()});
				if(path != null){
					logger.info("path={}, exists={}", new Object[]{path, client.exists(path)});
				}
			}
		}));
		
		try {
			Thread.sleep(10000000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		String str = "hell world";
		try {
			client.create(path, str.getBytes(charset));
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
	}

	@Test
	public void testGetData() {
		try {
			logger.info("data={}", new String(client.getData(path), charset));
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
	}

	@Test
	public void testSetData() {
		String str = "你好呀";
		try {
			client.setData(path, str.getBytes(charset));
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
	}
	
	@Test
	public void testGetChild(){
		logger.info("child={}", client.getChild(path));
	}
}
