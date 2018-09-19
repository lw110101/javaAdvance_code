package b_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 使用Statement对象执行静态sql语句
 * hasee 上午10:52:53
 */
public class Demo1 {

	private String url="jdbc:mysql://localhost:3306/day12";
	private String user="root";
	private String password="root";
	/**
	 * 执行DDL语句(创建表)
	 */
	@Test
	public void test() {
		Connection conn=null;
		Statement statement=null;
		
		try {
			//建立连接
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
			//创建statement对象
			statement=conn.createStatement();
			String sql="CREATE TABLE student(id INT, NAME VARCHAR(20))";
			//String sql="drop table student";
			int i=statement.executeUpdate(sql);
			System.out.println("影响了"+i+"行");
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			//关闭连接
		} finally {
			if(statement!=null) 
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			if(conn!=null) 
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			
		}
	}
}
