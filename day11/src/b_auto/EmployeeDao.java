package b_auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JdbcUtil;

public class EmployeeDao {
	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	// 保存员工的时候保存部门表
	public void save(Employee emp) {
		// sql语句
		String dept_sql = "insert into dept(deptName) values(?)";
		String emp_sql = "insert into employee(empName,dID) values(?,?)";
		// 部门id
		int deptID = 0;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			/***** 保存部门，获取自增长 *******/
			// 创建pstmt【一、需要指定返回自增长标记】
			pstmt = conn.prepareStatement(dept_sql, Statement.RETURN_GENERATED_KEYS);
			// 设置参数
			pstmt.setString(1, emp.getDept().getDeptName());
			// 执行sql
			pstmt.executeUpdate();
			// 【二、获取上面保存的部门子增长的主键】
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				deptID = rs.getInt(1);
			}
			/********* 保存员工信息 **********/
			pstmt = conn.prepareStatement(emp_sql);
			// 设置员工参数
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, deptID);
			// 执行sql
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

	}
}
