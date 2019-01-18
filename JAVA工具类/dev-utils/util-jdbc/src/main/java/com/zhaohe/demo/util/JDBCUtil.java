package com.zhaohe.demo.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	/**
	 * 只测试mysql通过
	 * 
	 * @param con
	 * @param dbName
	 */
	public static synchronized void createDBIfNoExist(Connection con, String dbName) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.execute("use " + dbName);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42000") && e.getMessage().contains("Unknown database")) {
				try {
					stmt.execute("create database " + dbName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			close(stmt);
			close(con);
		}
	}

	/**
	 * oracle、mysql测试通过
	 * 
	 * @param schemaPattern
	 *            数据库的名字
	 * @param tableName
	 * @param conn
	 * @return
	 */
	public static boolean isTableExists(String schemaPattern, String tableName, Connection conn) {
		boolean isExists = false;
		ResultSet rs = null;
		if (schemaPattern != null) {
			schemaPattern = schemaPattern.toUpperCase();
		}
		try {
			DatabaseMetaData meta = conn.getMetaData();
			String type[] = { "TABLE" };
			rs = meta.getTables(null, schemaPattern, tableName.toUpperCase(), type);
			isExists = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return isExists;
	}

	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(CallableStatement callStmt) {
		if (callStmt != null) {
			try {
				callStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
