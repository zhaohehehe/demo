package JDBC.RowSet;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		RowSetTest rowSetTest = new RowSetTest();
		// rowSetTest.testCachedRowSet1();
		// rowSetTest.testCachedRowSet2(1, 2);
		rowSetTest.testJdbcRowSet();
	}

	public void testJdbcRowSet() throws SQLException {
		RowSetFactory factory = RowSetProvider.newFactory();
		try (JdbcRowSet js = factory.createJdbcRowSet()) {
			js.setUrl("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false");
			js.setUsername("test");
			js.setPassword("test");
			js.setCommand("select * from img_table");
			js.execute();
			ResultSet rs = js.getStatement().getResultSet();
			while (rs.next()) {
				Blob data = rs.getBlob(3);
				System.out.println(rs.getString(1) + rs.getString(2) + data);
			}
		}

	}

	/**
	 * 离线更新：连接中途可以断开（只指针对一个页块）
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testCachedRowSet1() throws ClassNotFoundException, SQLException {
		CachedRowSet cachedRowSet = this.fillCachedRowSetMethod(2, 2);
		// Moves the cursor to the end of this ResultSet object, just after the
		// last row. This method has no effect if the result set contains no
		// rows.
		// cachedRowSet.afterLast();cachedRowSet.previous()，配合使用逆序输出；cachedRowSet.next()，正序输出
		cachedRowSet.afterLast();
		while (cachedRowSet.previous()) {
			System.out.println(cachedRowSet.getString(1) + " " + cachedRowSet.getString(2));
			cachedRowSet.updateString(2, cachedRowSet.getString(2) + "new");
			cachedRowSet.updateRow();
		}
		// 重连，同步到真实数据库
		Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false", "test",
				"test");
		conn.setAutoCommit(false);// 一定要设置
		cachedRowSet.acceptChanges(conn); // 由于之前连接资源已断，因此要调用有参版本的
	}

	/**
	 * 滚动分页：中途连接不能断开（因为内存中只能存储一页，循环遍历每页记录时，内存中的页块不断替换，离线就找不到了。但是针对某一页是可以离线的）
	 * 
	 * @param pageSize
	 * @param page
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testCachedRowSet2(int pageSize, int page) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		// 关闭所有资源
		Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false", "test",
				"test");
		ResultSet rs = conn.createStatement().executeQuery("select * from img_table");
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		/*
		 * 最终每次获取的cache记录就是：记录开始位置，记录开始位置+1，记录开始位置+2，...，记录开始位置+pageSize-1
		 */
		cachedRowSet.setPageSize(pageSize);// 设置缓存块大小
		cachedRowSet.populate(rs, (page - 1) * pageSize + 1);// 设置记录开始位置，位置从1开始而不是0
		// Moves the cursor to the end of this ResultSet object, just after the
		// last row. This method has no effect if the result set contains no
		// rows.
		// cachedRowSet.afterLast();cachedRowSet.previous()，配合使用逆序输出；cachedRowSet.next()，正序输出
		// 先输出当前页
		cachedRowSet.afterLast();
		while (cachedRowSet.previous()) {
			System.out.println(cachedRowSet.getString(1) + " " + cachedRowSet.getString(2));
			cachedRowSet.updateString(2, cachedRowSet.getString(2) + "new");
			cachedRowSet.updateRow();
		}
		// 然后下一页
		int i = 0;
		while (cachedRowSet.nextPage()) {
			i++;
			System.out.println("====第" + i + "页====================");
			while (cachedRowSet.next()) {
				System.out.println(cachedRowSet.getString(1) + " " + cachedRowSet.getString(2));
				cachedRowSet.updateString(2, cachedRowSet.getString(2) + "new");
				cachedRowSet.updateRow();
			}
		}
	}

	/**
	 * 离线CachedRowSet
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 * @throws ClassNotFoundException
	 */
	public CachedRowSet fillCachedRowSetMethod(int pageSize, int page) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try (// 关闭所有资源
				Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
						"test", "test");
				ResultSet rs = conn.createStatement().executeQuery("select * from img_table");) {
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet cache = factory.createCachedRowSet();
			/*
			 * 最终每次获取的cache记录就是：记录开始位置，记录开始位置+1，记录开始位置+2，...，记录开始位置+pageSize-1
			 */
			cache.setPageSize(pageSize);// 设置缓存块大小
			cache.populate(rs, (page - 1) * pageSize + 1);// 设置记录开始位置，位置从1开始而不是0
			return cache;// 这样返回的RowSet仍然能用，说明被离线缓存了
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
