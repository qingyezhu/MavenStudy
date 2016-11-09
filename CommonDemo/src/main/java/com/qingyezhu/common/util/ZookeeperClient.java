package com.qingyezhu.common.util;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperClient {
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperClient.class);

	private ZooKeeper zooKeeper;

	private String connectString = "192.168.1.101:2181,192.168.1.102:2181,192.168.1.103:2181";
	private int sessionTimeout = 10000;
	
	public ZookeeperClient(){
		try {
			zooKeeper = new ZooKeeper(connectString, sessionTimeout, new ZookeeperWatcher(this));
			logger.info("zookeeper={}", zooKeeper);
		} catch (IOException e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
	}

	public String create(String path, byte[] data) {
		String ret = null;
		try {
			ret = zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
		return ret;
	}

	public void setData(String path, byte[] data) {
		setData(path, data, -1);
	}

	public void setData(String path, byte[] data, int version) {
		try {
			zooKeeper.setData(path, data, version);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
	}

	public byte[] getData(String path, boolean watch, Stat stat) {
		byte[] data = null;
		try {
			data = zooKeeper.getData(path, watch, stat);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
		return data;
	}

	public byte[] getData(String path) {
		return getData(path, true, null);
	}

	public void delete(String path) {
		delete(path, -1);
	}

	public void delete(String path, int version) {
		try {
			zooKeeper.delete(path, version);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
	}

	public boolean exists(String path) {
		return exists(path, true);
	}

	public boolean exists(String path, boolean watch) {
		Stat stat = null;
		try {
			stat = zooKeeper.exists(path, watch);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
		return stat != null;
	}
	
	public List<String> getChild(String path){
		return getChild(path, true);
	}
	
	public List<String> getChild(String path, boolean watch){
		List<String> list = null;
		try {
			list = zooKeeper.getChildren(path, watch);
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}
		return list;
	}
	

}
