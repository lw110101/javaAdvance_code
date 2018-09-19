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
	// 1. 更新单个占位符
	public void testUpdate() throws Exception {
		String sql="delete from admin where id=?";
		//连接对象
		conn=JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr=new QueryRunner();
		qr.update(conn, sql, 12);
		// 关闭
		DbUtils.close(conn);
	}
	
	@Test
	// 1. 更新多个占位符
	public void testUpdate1() throws Exception {
		String sql="insert into admin(userName,password) values(?,?)";
		//连接对象
		conn=JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr=new QueryRunner();
		
		Object[] paramValues= {"liuwei","652593"};
		qr.update(conn, sql, paramValues);
		// 关闭
		DbUtils.close(conn);
	}
	@Test  /*批处理*/
	public void testBatch() throws SQLException {
		String sql="insert into admin(userName,password) values(?,?)";
		//连接对象
		conn=JdbcUtil.getConnection();
		//创建核心工具类
		QueryRunner qr=new QueryRunner();
		//批处理
		qr.batch(conn,sql, new Object[][] {{"管理员11","root11"},{"管理员12","root12"}});
		DbUtils.close(conn);
	}
}
