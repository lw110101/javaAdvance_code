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
	 * ʹ��Driver�ӿ��������ݿ�
	 * @throws SQLException
	 */
	@Test
	public void test1() throws SQLException {
		// 1.�����������������
		Driver drive = new com.mysql.jdbc.Driver(); // new copyrignt
		// Driver drive=new org.gjt.mm.mysql.Driver(); //old Copy
		// 2.�����û����ʺ�������
		Properties proper = new Properties();
		proper.setProperty("user", user);
		proper.setProperty("password", password);
		// 3.�������ݿ�
		Connection conn = drive.connect(url, proper);
		System.out.println(conn);
	}

	/**
	 * ʹ���������������������ݿ�*(ע��������)
	 * @throws SQLException
	 */
	@Test
	public void test2() throws SQLException {
		//1.���������������
		Driver drive = new com.mysql.jdbc.Driver();
		//2.ע��һ����������
		DriverManager.registerDriver(drive);
		//3.���ӵ����ݿ�
		Connection conn=DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}
	
	@Test
	public void test3() throws Exception {
		//ͨ���õ��ֽ������ķ�ʽ���ؾ�̬����飬�Ӷ�ע����������
		Class.forName("com.mysql.jdbc.Driver");
		//�������ݿ�
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}
}
