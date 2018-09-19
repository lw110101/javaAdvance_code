package b_statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class Demo2 {
	/**
	 * 执行DML语句
	 */
	@Test
	public void insertTest() {

		Connection conn = null;

		Statement statement = null;
		try {
			// 1.create connection
			conn = JdbcUtil.getConnection();
			// 2.create statement
			statement = conn.createStatement();
			// 3.DML_insert语句
			int i = statement.executeUpdate("INSERT INTO student VALUES(120,'李四','女',18)");
			// 4.outout
			System.out.println(i + "行收到影响");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 5.close resourse
			JdbcUtil.close(conn, statement);
		}
	}

	@Test

	public void updateTest() {

		Connection conn = null;
		Statement statement = null;
		//模拟用户输入
		int age=19;
		int id=120;
		try {
			// 1.create connection
			conn = JdbcUtil.getConnection();
			// 2.create statement
			statement = conn.createStatement();
			// 3.DML_update语句
			int i = statement.executeUpdate("UPDATE student SET age="+age+" WHERE id="+id+" ");
			// 4.outout
			System.out.println(i + "行收到影响");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 5.close resourse
			JdbcUtil.close(conn, statement);
		}
	}

	@Test
	public void deleteTest() {

		Connection conn = null;
		Statement statement = null;
		int id=130;
		try {
			// 1.create connection
			conn = JdbcUtil.getConnection();
			// 2.create statement
			statement = conn.createStatement();
			// 3.DML_delete语句
			int i = statement.executeUpdate("DELETE FROM student WHERE id="+id+"");
			// 4.outout
			System.out.println(i + "行收到影响");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 5.close resourse
			JdbcUtil.close(conn, statement);
		}
	}

}
