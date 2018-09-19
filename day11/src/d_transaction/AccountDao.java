package d_transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import util.JdbcUtil;

public class AccountDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/********û��ʹ������ʵ��ת��**********/
	@Test
	public void testNotTrainsAction() {
		String sql_zs="update account set money=money+500 where accountName='����'";
		String sql_ls="update account set money=money-500 where accountName='����'";
		try {
			conn=JdbcUtil.getConnection();
			/***��һ��ִ��sql**/
			pstmt=conn.prepareStatement(sql_zs);
			pstmt.executeUpdate();
			/***��һ��ִ��sql**/
			pstmt=conn.prepareStatement(sql_ls);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, pstmt,rs);
		}
	}
	/********ʹ������ʵ��ת��**********/
	@Test
	public void testTrainsAction() {
		String sql_zs="update account set money=money+500 where accountName=?";
		String sql_ls="update account set money=money-500 where accountName=?";
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			/***��һ��ִ��sql**/
			pstmt=conn.prepareStatement(sql_zs);
			pstmt.setString(1, "����");
			pstmt.executeUpdate();
			/***��һ��ִ��sql**/
			pstmt=conn.prepareStatement(sql_ls);
			pstmt.setString(1, "����");
			pstmt.executeUpdate();
		}catch(Exception e) {
			// ���� �����쳣����Ҫ�ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			// �������еĲ���ִ�гɹ�, �ύ����
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.close(conn, pstmt,rs);
		}
	}
	/********ʹ������ʵ��ת��,�ع���ָ��λ��**********/
	@Test
	public void testTrains() {
		/***��һ��sql**/
		String sql_zs1="update account set money=money+500 where accountName=?";
		String sql_ls1="update account set money=money-500 where accountName=?";
		/***�ڶ���sql**/
		String sql_zs2="update account set money=money+500 where accountName=?";
		String sql_ls2="update account set money=money-500 where accountName=?";
		Savepoint sp=null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			/***��һ��ִ��sql**/
			pstmt=conn.prepareStatement(sql_zs1);
			pstmt.setString(1, "����");
			pstmt.executeUpdate();
		
			pstmt=conn.prepareStatement(sql_ls1);
			pstmt.setString(1, "����");
			pstmt.executeUpdate();
			//�ع���ָ��λ��
			sp=conn.setSavepoint();
			/*******�ڶ���ִ��sql*****/
			pstmt=conn.prepareStatement(sql_zs2);
			pstmt.setString(1, "����");
			pstmt.executeUpdate();
			
			pstmt=conn.prepareStatement(sql_ls2);
			pstmt.setString(1, "����");
			pstmt.executeUpdate();
		}catch(Exception e) {
			// ���� �����쳣����Ҫ�ع�����
			try {
				conn.rollback(sp);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			// �������еĲ���ִ�гɹ�, �ύ����
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.close(conn, pstmt,rs);
		}
	}
	
}
