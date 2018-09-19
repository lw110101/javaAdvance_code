package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private static String url=null;
	private static String user=null;
	private static String password=null;
	private static String driverName=null;
	
	//创建驱动程序
	static {
		try {
			Properties proper=new Properties();
			//类文件路径
			InputStream in=JdbcUtil.class.getResourceAsStream("db.properties");
			//加载配置文件
			proper.load(in);
			//获取配置信息
			url=proper.getProperty("url");
			user=proper.getProperty("user");
			password=proper.getProperty("password");
			driverName=proper.getProperty("driverName");
			//加载驱动
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱动程序出错！");
		}
	}
	/**
	 * 获取连接
	 */
	public static Connection getConnection()  {
		
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}
	
	/**
	 * 关闭资源
	 */
	public static void close(Connection connection,Statement statement) {
		try {
			if (statement != null) {
				statement.close();
				statement=null;
			}
				
			if (connection != null && !connection.isClosed()) {
				connection.close();
				connection=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void close(Connection connection,Statement statement,ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs=null;
			}
			if (statement != null) {
				statement.close();
				statement=null;
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
				connection=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
