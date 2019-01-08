package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * BEGIN; INSERT INTO img_table(img_name,img_data) VALUES('img01',null);
 * #当前窗口可以查到新增记录，但是在其他窗口查询不到新增记录，事物还没有提交
 * SELECT * FROM img_table; 
 * rollback;
 * #当前窗口查不到新增记录，因为事物已经回滚
 *  SELECT * FROM img_table;
 * #================================================================= B
 * EGIN;
 * INSERT INTO img_table(img_name,img_data) VALUES('img01',null); 
 * SAVEPOINT a;
 * INSERT INTO img_table(img_name,img_data) VALUES('img02',null); 
 * ROLLBACK to a;
 * #执行DDL语句会隐式提交事物 
 * ALTER TABLE img_table MODIFY COLUMN img_name VARCHAR(10);
 * #提交事物 
 * COMMIT;
 * 注意：如果遇到未处理的SQLException异常那，事物会自动回滚，但是如果已经捕获异常，需要在异常块中显式回滚事物
 * @author zhaohe
 *
 */
public class TransactionRollbackTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try(Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test2","root","root");){
			//关闭自动提交，开启事物
			con.setAutoCommit(false);
			Savepoint point=null;
			try(Statement st=con.createStatement()){
				st.execute("INSERT INTO img_table(img_id,img_name,img_data) VALUES(3,'img01',null); ");
				point=con.setSavepoint();
				st.execute("INSERT INTO img_table(img_id,img_name,img_data) VALUES(4,'img01',null); ");
				st.execute("INSERT INTO img_table(img_id,img_name,img_data) VALUES(4,'img01',null); ");
			} catch(SQLException e){
				con.rollback(point);
			}
			con.commit();
		}
	}
}
