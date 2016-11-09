package com.qingyezhu.common.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperWatcher implements Watcher {

	private static final Logger logger = LoggerFactory.getLogger(ZookeeperWatcher.class);

	private ZookeeperClient client;

	public ZookeeperWatcher(ZookeeperClient client) {
		this.client = client;
	}

	@Override
	public void process(WatchedEvent event) {
		String path = event.getPath();
		logger.info("path={}, type={}, state={}", new Object[]{path, event.getType(), event.getState()});
		if(path != null){
			logger.info("path={}, exists={}", new Object[]{path, client.exists(path)});
		}
	}

}
