package b_statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class Demo3 {
	/**
	 * ִ��DQL���
	 */
	@Test
	public void testQuery() {
		Connection conn = null;
		Statement state = null;
		try {
			//1.��������
			conn = JdbcUtil.getConnection();
			//2.����Statement
			state = conn.createStatement();
			//׼��sql
			String sql = "select * from student";
			//4.ִ��sql
			ResultSet result=state.executeQuery(sql);
			//5.�������
			while(result.next()) {
				//ͨ������ֵ��ȡ����
				/*int id=result.getInt(1);
				String name=result.getString(2);
				String gender=result.getString(3);
				int age=result.getInt(4);*/
				//ͨ���ֶ�����ȡ����
				int id=result.getInt("id");
				String name=result.getString("name");
				String gender=result.getString("gender");
				int age=result.getInt("age");
				
				System.out.println(id+" "+name+" "+gender+" "+age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, state);
		}
	}
}
