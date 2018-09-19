package c_preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

/**
 * 模拟用户登录
 * @author hasee 下午8:49:33
 */
public class Demo2 {

	/*private String name="rose";
	private String password="123456";*/
	//sql注入攻击
	private String name="rose45' OR 1=1 -- ";
	private String password="12345645";
	@Test
	public void statementTest() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//获取连接
			conn=JdbcUtil.getConnection();
			//创建statement
			stmt=conn.createStatement();
			//准备sql语句
			String sql="select * from users where name='"+name+"' and password='"+password+"'";
			//执行sql
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("登录成功!");
			}else {
				System.out.println("登录失败!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}	
	}
	
	@Test
	public void preparedsStatementTest() {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			//获取连接
			conn=JdbcUtil.getConnection();
			//准备sql语句
			String sql="select * from users where name=? and password=?";
			//创建statement
			stmt=conn.prepareStatement(sql);
			//设置参数
			stmt.setString(1, name);
			stmt.setString(2, password);
			//执行sql
			rs=stmt.executeQuery();
			if(rs.next()) {
				System.out.println("登录成功!");
			}else {
				System.out.println("登录失败!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}
		
	}
	
}
