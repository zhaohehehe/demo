package com.zhaohe.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetJDBCConnUtil {

	public static String getDBAttr(Object db, String key) throws Exception {
		if (db instanceof net.sf.json.JSONObject) {
			net.sf.json.JSONObject jsonObj = (net.sf.json.JSONObject) db;
			if (jsonObj.containsKey(key) && !"".equals(jsonObj.getString(key))) {
				return jsonObj.getString(key);
			}
			throw new Exception(jsonObj + "不包含" + key + "字段！");
		} else if (db instanceof org.json.JSONObject) {
			org.json.JSONObject jsonObj = (org.json.JSONObject) db;
			if (jsonObj.has(key) && !"".equals(jsonObj.getString(key))) {
				return jsonObj.getString(key);
			}
			throw new Exception(jsonObj + "不包含" + key + "字段！");
		} else if (db instanceof com.google.gson.JsonObject) {
			com.google.gson.JsonObject jsonObj = (com.google.gson.JsonObject) db;
			if (jsonObj.has(key) && !"".equals(jsonObj.get(key).getAsString())) {
				return jsonObj.get(key).getAsString();
			}
			throw new Exception(jsonObj + "不包含" + key + "字段！");
		} else {
			throw new Exception("不支持" + db + "类型的数据库json Object!");
		}
	}

	public static String getDBDriver(String dbType) throws Exception {
		if ("oracle".equals(dbType)) {
			return "oracle.jdbc.driver.OracleDriver";
		} else if ("mysql".equals(dbType)) {
			return "com.mysql.jdbc.Driver";
		} else if ("sqlserver".equals(dbType)) {
			return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		} else {
			throw new Exception("暂不支持" + dbType + "类型的数据库");
		}
	}

	/**
	 * @param dbType
	 * @param dbName
	 * @param dbIp
	 * @param dbPort
	 * @param dbDriver
	 * @param dbOracleSID
	 *            针对oracle实例号,mysql可以为空
	 * @return
	 */
	private static String getDBUrl(String dbType, String dbName, String dbIp, String dbPort, String dbDriver,
			String dbOracleSID) {
		StringBuilder sb = null;
		switch (dbType) {
		case "oracle":
			sb = new StringBuilder("jdbc:oracle:thin:@").append(dbIp).append(":").append(dbPort).append("/")
					.append(dbOracleSID);
			break;
		case "mysql":
			sb = new StringBuilder("jdbc:mysql://").append(dbIp).append(":").append(dbPort).append("/").append(dbName)
					.append("?useUnicode=false&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useOldAliasMetadataBehavior=true");
			break;
		case "sqlserver":
			sb = new StringBuilder("jdbc:microsoft:sqlserver://").append(dbIp).append(":").append(dbPort)
					.append(";DatabaseName=").append(dbName);
			break;
		default:
			return null;
		}
		return sb.toString();
	}

	/**
	 * @param db
	 *            类型为Json类，目前支持net.sf.json.JSONObject和com.google.gson.
	 *            JsonObject和import org.json.JSONObject
	 * @return
	 * @throws Exception
	 */
	public static Connection getJDBCConnection(Object db) throws Exception {
		String dbType = getDBAttr(db, "dbType");
		String dbName = getDBAttr(db, "dbName");
		String dbUser = getDBAttr(db, "dbUser");
		String dbPwd = getDBAttr(db, "dbPwd");
		String dbIp = getDBAttr(db, "dbIp");
		String dbPort = getDBAttr(db, "dbPort");
		String dbOracleSID = null;
		if ("oracle".equals(dbType)) {
			dbOracleSID = getDBAttr(db, "dbOracleSID");
		}
		String dbDriver = getDBDriver(dbType);
		String dbUrl = getDBUrl(dbType, dbName, dbIp, dbPort, dbDriver, dbOracleSID);
		Connection con = null;
		try {
			Class.forName(dbDriver).newInstance();
			con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			DriverManager.setLoginTimeout(1000);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getJDBCConnection(String url, String username, String password) throws SQLException {
		Connection con = null;
		String driver = null;
		if (url.indexOf("mysql") > 0) {
			driver = "com.mysql.jdbc.Driver";
		} else if (url.indexOf("oracle") > 0) {
			driver = "oracle.jdbc.driver.OracleDriver";
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = (Connection) DriverManager.getConnection(url, username, password);
		return con;
	}
}
