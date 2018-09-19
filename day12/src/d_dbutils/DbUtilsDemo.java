package d_dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import util.JdbcUtil;

/**
 * DbUtils   �ر���Դ����������
	closeQuietly(��)
	commitAndCloseQuietly(Connection conn)    �ύ���ӣ�Ȼ��ر����� 
	boolean loadDriver(java.lang.String driverClassName)  װ�ز�ע��JDBC��������
 * @author hasee ����7:55:49
 */
public class DbUtilsDemo {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Test  /***closeQuietly(��)*/
	public void testDbUtils() {
		String sql="select * from admin";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt(1);
				String userName=rs.getString(2);
				String passwd = rs.getString(3);
				System.out.println(id+":  "+userName+"="+passwd);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn, pstmt, rs);
			//JdbcUtil.close(conn, pstmt, rs);		
		}
	}
	
	@Test  /***commitAndCloseQuietly(Connection conn)*/
	public void testDbUtils2() {
		String sql="select * from admin";
		try {
			boolean flag = DbUtils.loadDriver("com.mysql.jdbc.Driver");
			System.out.println("�Ƿ����ӳɹ�:"+flag);
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt(1);
				String userName=rs.getString(2);
				String passwd = rs.getString(3);
				System.out.println(id+":  "+userName+"="+passwd);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.commitAndCloseQuietly(conn);
		}
	}
}
