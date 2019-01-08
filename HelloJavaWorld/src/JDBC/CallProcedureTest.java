package JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 	MYSQL 存储过程示例：
	CREATE PROCEDURE `add_pro`(IN p_in_a VARCHAR(10),IN p_in_b VARCHAR(10),OUT p_out_c INT)
	BEGIN
	    SET p_out_c= LENGTH(p_in_a) + LENGTH(p_in_b);
	    SELECT p_out_c;
	END
	存储过程调用：
	SET @p_out=0;
	CALL add_pro('12','3234344',@p_out);
	SELECT @p_out;
 * @author ZH
 *
 */
public class CallProcedureTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("======");
		Connection con=DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false","test","test");
		CallableStatement call=con.prepareCall("{call add_pro(?,?,?)}");
		call.setString(1, "156");
		call.setString(2, "156");
		call.registerOutParameter(3, Types.INTEGER);
		call.execute();
		System.out.println(call.getInt(3));
	}
}
