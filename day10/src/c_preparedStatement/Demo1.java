package c_preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import util.JdbcUtil;

/**
 * 使用preparedstatment执行sql语句
 * 
 * @author hasee 下午7:45:25
 */
public class Demo1 {
	/**
	 * 插入
	 */
	@Test
	public void insertTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 准备预编译的sql语句
			String sql = "INSERT INTO student VALUES(?,?,?,?)";
			// 创建preparedstatment
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setInt(1, 150);
			stmt.setString(2, "赵六");
			stmt.setString(3, "女");
			stmt.setInt(4, 32);
			// 发送参数,执行sql
			int i = stmt.executeUpdate();
			System.out.println(i + "行受到了影响");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	/**
	 * 修改
	 */
	@Test
	public void updateTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 准备预编译的sql语句
			String sql = "UPDATE student SET NAME=? WHERE id=?";
			// 创建preparedstatment
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setString(1, "赵六");
			stmt.setInt(2, 150);
			// 发送参数,执行sql
			int i = stmt.executeUpdate();
			System.out.println(i + "行受到了影响");
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
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 准备预编译的sql语句
			String sql = "DELETE FROM student WHERE id=?";
			// 创建preparedstatment
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setInt(1, 150);
			// 发送参数,执行sql
			int i = stmt.executeUpdate();
			System.out.println(i + "行受到了影响");
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
			// 获取连接
			conn = JdbcUtil.getConnection();
			/*
			 * // 准备预编译的sql语句 String sql = "select * from student where id=?"; //
			 * 创建preparedstatment stmt = conn.prepareStatement(sql); //设置参数 stmt.setInt(1,
			 * 101); //发送参数,执行sql ResultSet rs=stmt.executeQuery(); if(rs.next()) { int
			 * id=rs.getInt("id"); String name=rs.getString("name"); String
			 * gender=rs.getString("gender"); int age=rs.getInt("age");
			 * System.out.println(id+" "+name+" "+gender+" "+age); }
			 */
			// 准备预编译的sql语句
			String sql = "select * from student ";
			// 创建preparedstatment
			stmt = conn.prepareStatement(sql);
			// 执行sql语句
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
