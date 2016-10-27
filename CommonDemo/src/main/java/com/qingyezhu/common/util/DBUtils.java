package com.qingyezhu.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.common.exception.DBUtilsException;

public class DBUtils {
	private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

	private static class QueryRunnerInner {
		private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	}

	public static Connection getConn(DBUserInfo dbUserInfo) {
		Connection conn = null;
		String type = dbUserInfo.getType(), driverClass = null, url = null;
		if (type.equalsIgnoreCase("mysql")) {
			driverClass = "com.mysql.jdbc.Driver";
			url = String.format("jdbc:mysql://%s:%d/%s", dbUserInfo.getHost(), dbUserInfo.getPort(),
					dbUserInfo.getDbName());
		}
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, dbUserInfo.getUsername(), dbUserInfo.getPassword());
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
			throw new DBUtilsException(e.getMessage(), e);
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("catch error msg=" + e.getMessage(), e);
				throw new DBUtilsException(e.getMessage(), e);
			}
		}
	}

	public static <T> T queryBean(Connection conn, Class<T> clazz, String sql, Object... params){
		return queryBean(conn, clazz, null, sql, params);
	}
	
	public static <T> T queryBean(Connection conn, Class<T> clazz, Map<String, String> map, String sql, Object... params) {
		T ret = null;
		try {
			QueryRunner _QueryRunner = QueryRunnerInner.QUERY_RUNNER;
			if(MapUtils.isNotEmpty(map)){
				ret = _QueryRunner.query(conn, sql, new BeanHandler<T>(clazz, new BasicRowProcessor(new BeanProcessor(map))), params);
			}else{
				ret = _QueryRunner.query(conn, sql, new BeanHandler<T>(clazz, new BasicRowProcessor(new GenerousBeanProcessor())), params);
			}
		} catch (SQLException e) {
			logger.error("catch error msg=" + e.getMessage(), e);
			throw new DBUtilsException(e.getMessage(), e);
		}
		return ret;
	}
	
	public static <T> List<T> queryBeanList(Connection conn, Class<T> clazz, String sql, Object... params){
		return queryBeanList(conn, clazz, null, sql, params);
	}
	
	public static <T> List<T> queryBeanList(Connection conn, Class<T> clazz, Map<String, String> map, String sql, Object... params){
		List<T> ret = null;
		try {
			QueryRunner _QueryRunner = QueryRunnerInner.QUERY_RUNNER;
			if(MapUtils.isNotEmpty(map)){
				ret = _QueryRunner.query(conn, sql, new BeanListHandler<T>(clazz, new BasicRowProcessor(new BeanProcessor(map))), params);
			}else{
				ret = _QueryRunner.query(conn, sql, new BeanListHandler<T>(clazz, new BasicRowProcessor(new GenerousBeanProcessor())), params);
			}
		} catch (SQLException e) {
			logger.error("catch error msg=" + e.getMessage(), e);
			throw new DBUtilsException(e.getMessage(), e);
		}
		return ret;
	}

}
