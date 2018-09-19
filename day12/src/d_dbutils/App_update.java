package d_dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import util.JdbcUtil;

public class App_update {
	private Connection conn;

	@Test
	// 1. ���µ���ռλ��
	public void testUpdate() throws Exception {
		String sql="delete from admin where id=?";
		//���Ӷ���
		conn=JdbcUtil.getConnection();
		// ����DbUtils���Ĺ��������
		QueryRunner qr=new QueryRunner();
		qr.update(conn, sql, 12);
		// �ر�
		DbUtils.close(conn);
	}
	
	@Test
	// 1. ���¶��ռλ��
	public void testUpdate1() throws Exception {
		String sql="insert into admin(userName,password) values(?,?)";
		//���Ӷ���
		conn=JdbcUtil.getConnection();
		// ����DbUtils���Ĺ��������
		QueryRunner qr=new QueryRunner();
		
		Object[] paramValues= {"liuwei","652593"};
		qr.update(conn, sql, paramValues);
		// �ر�
		DbUtils.close(conn);
	}
	@Test  /*������*/
	public void testBatch() throws SQLException {
		String sql="insert into admin(userName,password) values(?,?)";
		//���Ӷ���
		conn=JdbcUtil.getConnection();
		//�������Ĺ�����
		QueryRunner qr=new QueryRunner();
		//������
		qr.batch(conn,sql, new Object[][] {{"����Ա11","root11"},{"����Ա12","root12"}});
		DbUtils.close(conn);
	}
}
