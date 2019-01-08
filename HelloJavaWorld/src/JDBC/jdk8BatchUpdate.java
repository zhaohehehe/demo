package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdk8BatchUpdate {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
				"test", "test");) {
			boolean commit = con.getAutoCommit();
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.addBatch("INSERT INTO img_table(img_id,img_name,img_data) VALUES(3,'img01',null); ");
				st.addBatch("INSERT INTO img_table(img_id,img_name,img_data) VALUES(4,'img01',null); ");
				int[] a = st.executeBatch();
				System.out.println(a.length);
			} catch (SQLException e) {
				con.rollback();
			}
			con.commit();
			con.setAutoCommit(commit);
		}
	}
}
