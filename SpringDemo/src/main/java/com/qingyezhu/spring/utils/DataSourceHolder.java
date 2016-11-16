package com.qingyezhu.spring.utils;

public class DataSourceHolder {
	public static final String DATA_SOURCE_MASTER = "master";
	public static final String DATA_SOURCE_SLAVE = "slave";
	public static final ThreadLocal<String> dataSource = new ThreadLocal<String>();

	public static void set(String alias){
		dataSource.set(alias);
	}
	
	public static String get(){
		return dataSource.get();
	}
	
	public static void clear(){
		dataSource.remove();
	}
}
