package a_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import util.JdbcUtil;

/**
 * / ��װ���е������ݿ�Ĳ���
 * @author hasee ����3:50:51
 */
public class AdminDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	public void save(List<Admin> list) {
		//��������
		//String sql="insert into admin(userName,password) values(?,?)";
		String sql="insert into employee(empName,dept_id) values(?,?)";
		
		try {
		//��ȡ����
		conn=JdbcUtil.getConnection();
		//����pstmt����
		pstmt=conn.prepareStatement(sql);
		for(int i=0;i<list.size();i++) {
			Admin admin=list.get(i);
			//���ò���
			pstmt.setString(1, admin.getUser());
			pstmt.setString(2, admin.getPwd());
			//���������
			pstmt.addBatch();
			// ���ԣ�ÿ5��ִ��һ��������
			if(i%5==0) {
				//����ִ��
				pstmt.executeBatch();
				//���������
				pstmt.clearBatch();
			}
		}
		// ����ִ�� 
		pstmt.executeBatch();
		// ���������
		pstmt.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt);
		}
	}
	
	//statement��������ִ��
	public void stmtBatch() {
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ����stmtdui����
			stmt = conn.createStatement();
			//׼������
			String sql1="insert into admin(userName,password) values('root','root')";
			String sql2="update admin set userName='superAdmin' where id=10";
			
			//���������
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			//ִ��������
			stmt.executeBatch();
			//���������
			stmt.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, stmt);
		}
	}
}
