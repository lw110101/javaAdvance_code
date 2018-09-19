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

	// ����Ա����ʱ�򱣴沿�ű�
	public void save(Employee emp) {
		// sql���
		String dept_sql = "insert into dept(deptName) values(?)";
		String emp_sql = "insert into employee(empName,dID) values(?,?)";
		// ����id
		int deptID = 0;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			/***** ���沿�ţ���ȡ������ *******/
			// ����pstmt��һ����Ҫָ��������������ǡ�
			pstmt = conn.prepareStatement(dept_sql, Statement.RETURN_GENERATED_KEYS);
			// ���ò���
			pstmt.setString(1, emp.getDept().getDeptName());
			// ִ��sql
			pstmt.executeUpdate();
			// ��������ȡ���汣��Ĳ�����������������
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				deptID = rs.getInt(1);
			}
			/********* ����Ա����Ϣ **********/
			pstmt = conn.prepareStatement(emp_sql);
			// ����Ա������
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, deptID);
			// ִ��sql
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

	}
}
