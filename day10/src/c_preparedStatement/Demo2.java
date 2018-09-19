package c_preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

/**
 * ģ���û���¼
 * @author hasee ����8:49:33
 */
public class Demo2 {

	/*private String name="rose";
	private String password="123456";*/
	//sqlע�빥��
	private String name="rose45' OR 1=1 -- ";
	private String password="12345645";
	@Test
	public void statementTest() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//��ȡ����
			conn=JdbcUtil.getConnection();
			//����statement
			stmt=conn.createStatement();
			//׼��sql���
			String sql="select * from users where name='"+name+"' and password='"+password+"'";
			//ִ��sql
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("��¼�ɹ�!");
			}else {
				System.out.println("��¼ʧ��!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}	
	}
	
	@Test
	public void preparedsStatementTest() {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			//��ȡ����
			conn=JdbcUtil.getConnection();
			//׼��sql���
			String sql="select * from users where name=? and password=?";
			//����statement
			stmt=conn.prepareStatement(sql);
			//���ò���
			stmt.setString(1, name);
			stmt.setString(2, password);
			//ִ��sql
			rs=stmt.executeQuery();
			if(rs.next()) {
				System.out.println("��¼�ɹ�!");
			}else {
				System.out.println("��¼ʧ��!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}
		
	}
	
}
