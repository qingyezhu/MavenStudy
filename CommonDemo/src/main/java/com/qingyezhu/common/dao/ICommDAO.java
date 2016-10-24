package com.qingyezhu.common.dao;

import java.io.Serializable;

public interface ICommDAO<T, K extends Serializable> {
	
	public T get(K id);
	
	public boolean saveOrUpdate(T entity);
}
