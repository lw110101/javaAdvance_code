package c_c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App_C3P0 {
	//硬编码实现
	@Test
	public void testC3P0() {
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setJdbcUrl("jdbc:mysql:///day13");
			dataSource.setInitialPoolSize(3);
			dataSource.setMaxPoolSize(6);
			Connection conn = dataSource.getConnection();
			conn.createStatement().executeUpdate("delete from admin where id=3");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//2. XML配置方式，使用C3P0连接池管理连接
	@Test
	public void testXML() {
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		try {
			Connection conn = dataSource.getConnection();
			conn.createStatement().executeUpdate("delete from admin where id=2");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
