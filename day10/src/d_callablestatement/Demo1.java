package d_callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;

/**
 * callablestatementִ�д洢����
 * @author hasee ����9:30:31
 */
public class Demo1 {
	/**
	 * ������������Ĵ洢����
	 */
	@Test
	public void testIn() {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			//��ȡ����
			conn=JdbcUtil.getConnection();
			//׼��sql���
			String sql="CALL pro_findById(?);";
			//����callablestatement
			stmt=conn.prepareCall(sql);
			//���ò���
			stmt.setInt(1, 110);
			//���Ͳ�����ִ��sql
			rs=stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				System.out.println(id + " " + name + " " + gender + " " + age);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}
	/**
	 * ������������Ĵ洢����
	 */
	@Test
	public void testOut() {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			//��ȡ����
			conn=JdbcUtil.getConnection();
			//׼��sql���
			String sql="CALL pro_findById2(?,?);";
			//����callablestatement
			stmt=conn.prepareCall(sql);
			//�����������
			stmt.setInt(1, 110);
			//�����������
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			//���Ͳ�����ִ��sql
			stmt.executeQuery();//������Ƿ��ص�������У����Ƿ��ص����������
			String name=stmt.getString(2);
			System.out.println(name);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}
}
