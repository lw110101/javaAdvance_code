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

	/********没有使用事务实现转账**********/
	@Test
	public void testNotTrainsAction() {
		String sql_zs="update account set money=money+500 where accountName='张三'";
		String sql_ls="update account set money=money-500 where accountName='李四'";
		try {
			conn=JdbcUtil.getConnection();
			/***第一次执行sql**/
			pstmt=conn.prepareStatement(sql_zs);
			pstmt.executeUpdate();
			/***第一次执行sql**/
			pstmt=conn.prepareStatement(sql_ls);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, pstmt,rs);
		}
	}
	/********使用事务实现转账**********/
	@Test
	public void testTrainsAction() {
		String sql_zs="update account set money=money+500 where accountName=?";
		String sql_ls="update account set money=money-500 where accountName=?";
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			/***第一次执行sql**/
			pstmt=conn.prepareStatement(sql_zs);
			pstmt.setString(1, "李四");
			pstmt.executeUpdate();
			/***第一次执行sql**/
			pstmt=conn.prepareStatement(sql_ls);
			pstmt.setString(1, "张三");
			pstmt.executeUpdate();
		}catch(Exception e) {
			// 二、 出现异常，需要回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			// 三、所有的操作执行成功, 提交事务
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.close(conn, pstmt,rs);
		}
	}
	/********使用事务实现转账,回滚到指定位置**********/
	@Test
	public void testTrains() {
		/***第一次sql**/
		String sql_zs1="update account set money=money+500 where accountName=?";
		String sql_ls1="update account set money=money-500 where accountName=?";
		/***第二次sql**/
		String sql_zs2="update account set money=money+500 where accountName=?";
		String sql_ls2="update account set money=money-500 where accountName=?";
		Savepoint sp=null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			/***第一次执行sql**/
			pstmt=conn.prepareStatement(sql_zs1);
			pstmt.setString(1, "张三");
			pstmt.executeUpdate();
		
			pstmt=conn.prepareStatement(sql_ls1);
			pstmt.setString(1, "李四");
			pstmt.executeUpdate();
			//回滚到指定位置
			sp=conn.setSavepoint();
			/*******第二次执行sql*****/
			pstmt=conn.prepareStatement(sql_zs2);
			pstmt.setString(1, "张三");
			pstmt.executeUpdate();
			
			pstmt=conn.prepareStatement(sql_ls2);
			pstmt.setString(1, "李四");
			pstmt.executeUpdate();
		}catch(Exception e) {
			// 二、 出现异常，需要回滚事务
			try {
				conn.rollback(sp);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			// 三、所有的操作执行成功, 提交事务
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.close(conn, pstmt,rs);
		}
	}
	
}
