package com.qingyezhu.common.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaCacheTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GuavaCacheTest.class);
	
	@Test
	public void testMaxSize(){
		//当添加时，达到最大时，则根据权重清除
		//每次get或containsKey都会在操作前和操作后对该段进行过期清理
		//每次put/remove/replace之前都会对该段进行过期清理
		
		final int maxSize = 100;
		Cache<Integer, Integer> cache = CacheBuilder.newBuilder().removalListener(new RemovalListener<Integer, Integer>() {

			@Override
			public void onRemoval(RemovalNotification<Integer, Integer> notification) {
					logger.info("key={}, val={}", notification.getKey(), notification.getValue());
			}
		}).maximumSize(maxSize).expireAfterAccess(5, TimeUnit.SECONDS).build();
		
		for(int i = 0;i < 10;i ++){
			cache.put(i, i);
		}
		logger.info("size={}", cache.size());
		logger.info("map={}", cache.asMap());
		
		for(int i = 10;i < 20;i ++){
			cache.put(i, i);
		}

		logger.info("size={}", cache.size());
		logger.info("map={}", cache.asMap());
		
		for(int i = 20;i < 30;i ++){
			cache.put(i, i);
		}

		logger.info("size={}", cache.size());
		logger.info("map={}", cache.asMap());
		

		for(int i = 20;i < 30;i ++){
			cache.put(i, i);
		}

		logger.info("size={}", cache.size());
		logger.info("map={}", cache.asMap());
	}

}
