package com.qingyezhu.common.util;

public class DBUserInfo {
	private final String type;
	private final String host;
	private final Integer port;
	private final String dbName;
	private final String username;
	private final String password;

	public DBUserInfo(DBUserInfo.DBUserInfoBuilder builder) {
		this.type = builder.getType();
		this.host = builder.getHost();
		this.port = builder.getPort();
		this.dbName = builder.getDbName();
		this.username = builder.getUsername();
		this.password = builder.getPassword();
	}

	/**
	 * 获取数据库类型：MySQL、Oracle等
	 * 
	 * @return
	 */
	public final String getType() {
		return type;
	}

	/**
	 * 获取数据库IP
	 * 
	 * @return
	 */
	public final String getHost() {
		return host;
	}

	/**
	 * 获取数据库端口
	 * 
	 * @return
	 */
	public final Integer getPort() {
		return port;
	}

	/**
	 * 获取数据库名
	 * 
	 * @return
	 */
	public final String getDbName() {
		return dbName;
	}

	/**
	 * 获取数据库对应库访问的用户名
	 * 
	 * @return
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * 获取数据库用户名对应的密码
	 * 
	 * @return
	 */
	public final String getPassword() {
		return password;
	}

	public static DBUserInfoBuilder newBuilder() {
		return new DBUserInfoBuilder();
	}

	public static class DBUserInfoBuilder {
		private String type;
		private String host;
		private Integer port;
		private String dbName;
		private String username;
		private String password;

		private DBUserInfoBuilder() {

		}

		/**
		 * 设置数据库类型：MySQL、Oracle等
		 * 
		 * @param type
		 * @return
		 */
		public DBUserInfoBuilder setType(String type) {
			this.type = type;
			return this;
		}

		/**
		 * 设置数据库的IP
		 * 
		 * @param host
		 * @return
		 */
		public DBUserInfoBuilder setHost(String host) {
			this.host = host;
			return this;
		}

		/**
		 * 设置数据库的端口
		 * 
		 * @param port
		 * @return
		 */
		public DBUserInfoBuilder setPort(Integer port) {
			this.port = port;
			return this;
		}

		/**
		 * 设置数据库名
		 * 
		 * @param dbName
		 * @return
		 */
		public DBUserInfoBuilder setDbName(String dbName) {
			this.dbName = dbName;
			return this;
		}

		/**
		 * 设置数据库访问对应库的用户名
		 * 
		 * @param username
		 * @return
		 */
		public DBUserInfoBuilder setUsername(String username) {
			this.username = username;
			return this;
		}

		/**
		 * 设置数据库用户名对应的密码
		 * 
		 * @param password
		 * @return
		 */
		public DBUserInfoBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		/**
		 * 获取数据库类型：MySQL、Oracle等
		 * 
		 * @return
		 */
		public final String getType() {
			return type;
		}

		/**
		 * 获取数据库IP
		 * 
		 * @return
		 */
		public final String getHost() {
			return host;
		}

		/**
		 * 获取数据库端口
		 * 
		 * @return
		 */
		public final Integer getPort() {
			return port;
		}

		/**
		 * 获取数据库名
		 * 
		 * @return
		 */
		public final String getDbName() {
			return dbName;
		}

		/**
		 * 获取数据库对应库访问的用户名
		 * 
		 * @return
		 */
		public final String getUsername() {
			return username;
		}

		/**
		 * 获取数据库用户名对应的密码
		 * 
		 * @return
		 */
		public final String getPassword() {
			return password;
		}

		public DBUserInfo build() {
			DBUserInfo userInfo = new DBUserInfo(this);
			return userInfo;
		}
	}
}
