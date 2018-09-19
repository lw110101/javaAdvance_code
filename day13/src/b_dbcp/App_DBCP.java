package b_dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class App_DBCP {

	@Test	// 1. Ӳ���뷽ʽʵ�����ӳ�
	/*1.����jar��
	 * 2.����������
	 */
	public void testDBCP() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setInitialSize(3);
		dataSource.setUrl("jdbc:mysql:///day13");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setMaxActive(6);
		dataSource.setMaxIdle(3000);   // ������ʱ��
		//��ȡ����
		try {
			Connection conn = dataSource.getConnection();
			ResultSet rs= conn.createStatement().executeQuery("select * from admin where id=10");
			if(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Test  // ʹ�������ļ�ʵ��
	public void testPro() {
		try {
			Properties property=new Properties();
			InputStream inStream = this.getClass().getResourceAsStream("db.properties");
			property.load(inStream);
			DataSource dataSource=BasicDataSourceFactory.createDataSource(property);
			Connection conn = dataSource.getConnection();
			conn.createStatement().executeUpdate("delete from admin where id=4");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
