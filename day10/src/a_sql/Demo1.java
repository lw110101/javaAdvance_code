package a_sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class Demo1 {
	private String url="jdbc:mysql://localhost:3306/day12";
	private String user="root";
	private String password="root";

	/**
	 * 使用Driver接口连接数据库
	 * @throws SQLException
	 */
	@Test
	public void test1() throws SQLException {
		// 1.创建驱动程序类对象
		Driver drive = new com.mysql.jdbc.Driver(); // new copyrignt
		// Driver drive=new org.gjt.mm.mysql.Driver(); //old Copy
		// 2.设置用户名帐号与密码
		Properties proper = new Properties();
		proper.setProperty("user", user);
		proper.setProperty("password", password);
		// 3.链接数据库
		Connection conn = drive.connect(url, proper);
		System.out.println(conn);
	}

	/**
	 * 使用驱动管理器类连接数据库*(注册了两次)
	 * @throws SQLException
	 */
	@Test
	public void test2() throws SQLException {
		//1.创建驱动程序对象
		Driver drive = new com.mysql.jdbc.Driver();
		//2.注册一个驱动程序
		DriverManager.registerDriver(drive);
		//3.连接到数据库
		Connection conn=DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}
	
	@Test
	public void test3() throws Exception {
		//通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//链接数据库
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}
}
