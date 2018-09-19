package b_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * ʹ��Statement����ִ�о�̬sql���
 * hasee ����10:52:53
 */
public class Demo1 {

	private String url="jdbc:mysql://localhost:3306/day12";
	private String user="root";
	private String password="root";
	/**
	 * ִ��DDL���(������)
	 */
	@Test
	public void test() {
		Connection conn=null;
		Statement statement=null;
		
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
			//����statement����
			statement=conn.createStatement();
			String sql="CREATE TABLE student(id INT, NAME VARCHAR(20))";
			//String sql="drop table student";
			int i=statement.executeUpdate(sql);
			System.out.println("Ӱ����"+i+"��");
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			//�ر�����
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
