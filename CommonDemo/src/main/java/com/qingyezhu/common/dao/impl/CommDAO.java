package com.qingyezhu.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Type;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.common.dao.ICommDAO;
import com.qingyezhu.common.util.ClassUtils;

public class CommDAO<T, K extends Serializable> implements ICommDAO<T, K> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<T> tClazz;
	protected Class<K> kClazz;

	@SuppressWarnings("unchecked")
	private void init() {
		Type[] typeArr = ClassUtils.getGenericType(getClass());
		if (ArrayUtils.isNotEmpty(typeArr)) {
			tClazz = (Class<T>) typeArr[0];
			kClazz = (Class<K>) typeArr[1];
		} else {
			throw new RuntimeException("init CommDAO exception");
		}
	}

	public CommDAO() {
		init();
	}

	@Override
	public T get(K id) {
		return null;
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		return false;
	}

}
