package JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * create table img_table( img_id int auto_increment primary key, img_name
 * varchar(255), img_data mediumblob )
 * 
 * @author zhaohe
 *
 */
public class SaveBlob {
	private static Connection conn;
	private static PreparedStatement insert;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false", "test", "test");
			insert = conn.prepareStatement("insert into img_table(img_id,img_name,img_data) values(1236,?,?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert() {
		File file = new File("D:\\Workspace\\zhaohe\\HelloWorld\\src\\com\\shengsiyuan\\study\\jdbc\\连接池管理数据库连接.png");
		try (InputStream is = new FileInputStream(file)) {
			insert.setString(1, "测试");
			insert.setBinaryStream(2, is, file.length());
			int affect = insert.executeUpdate();
			if (affect == 1) {
				System.out.println("成功");
			} else {
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		new SaveBlob().insert();
	}
}
