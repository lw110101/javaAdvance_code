package c_preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import util.JdbcUtil;

/**
 * ʹ��preparedstatmentִ��sql���
 * 
 * @author hasee ����7:45:25
 */
public class Demo1 {
	/**
	 * ����
	 */
	@Test
	public void insertTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ׼��Ԥ�����sql���
			String sql = "INSERT INTO student VALUES(?,?,?,?)";
			// ����preparedstatment
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setInt(1, 150);
			stmt.setString(2, "����");
			stmt.setString(3, "Ů");
			stmt.setInt(4, 32);
			// ���Ͳ���,ִ��sql
			int i = stmt.executeUpdate();
			System.out.println(i + "���ܵ���Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	/**
	 * �޸�
	 */
	@Test
	public void updateTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ׼��Ԥ�����sql���
			String sql = "UPDATE student SET NAME=? WHERE id=?";
			// ����preparedstatment
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setString(1, "����");
			stmt.setInt(2, 150);
			// ���Ͳ���,ִ��sql
			int i = stmt.executeUpdate();
			System.out.println(i + "���ܵ���Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Test
	public void deleteTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ׼��Ԥ�����sql���
			String sql = "DELETE FROM student WHERE id=?";
			// ����preparedstatment
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setInt(1, 150);
			// ���Ͳ���,ִ��sql
			int i = stmt.executeUpdate();
			System.out.println(i + "���ܵ���Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Test
	public void queryTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			/*
			 * // ׼��Ԥ�����sql��� String sql = "select * from student where id=?"; //
			 * ����preparedstatment stmt = conn.prepareStatement(sql); //���ò��� stmt.setInt(1,
			 * 101); //���Ͳ���,ִ��sql ResultSet rs=stmt.executeQuery(); if(rs.next()) { int
			 * id=rs.getInt("id"); String name=rs.getString("name"); String
			 * gender=rs.getString("gender"); int age=rs.getInt("age");
			 * System.out.println(id+" "+name+" "+gender+" "+age); }
			 */
			// ׼��Ԥ�����sql���
			String sql = "select * from student ";
			// ����preparedstatment
			stmt = conn.prepareStatement(sql);
			// ִ��sql���
			 rs= stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				System.out.println(id + " " + name + " " + gender + " " + age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt,rs);
		}
	}
}
