package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 可更新的结果集：数据通常只能来源于一个表，而且查询的结果集必须包含主键，否则会更新失败 
 * 不过并不是所有数据库都支持：
 *
 * @author zhaohe
 *
 */
public class 可更新结果集 {
	/**
	 * 查看是否支持某些配置
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void test() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
				"test", "test");) {
			// 查看是否支持
			// false
			System.out.println(con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
			// true
			System.out.println(con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			// true
			System.out.println(con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE));
		}

	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
				"test", "test");
				PreparedStatement sta =con.prepareStatement("select * from img_table",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = sta.executeQuery();) {
			// 将指针移动到最后一行
			rs.last();
			int rowCount = rs.getRow();
			System.out.println("总共记录数 ：" + rowCount);
			for (int i = rowCount; i > 0; i--) {
				rs.absolute(i);
				System.out.println(rs.getString(1) + " ; " + rs.getString(2));
				// 数据库中所有第二列的值都会被修改
				rs.updateString(2, "new " + rs.getString(2));
				// 提交修改
				rs.updateRow();
			}

		}
	}
}
