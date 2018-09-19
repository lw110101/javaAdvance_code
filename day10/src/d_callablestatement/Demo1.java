package d_callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;

/**
 * callablestatement执行存储过程
 * @author hasee 下午9:30:31
 */
public class Demo1 {
	/**
	 * 带有输入参数的存储过程
	 */
	@Test
	public void testIn() {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			//获取连接
			conn=JdbcUtil.getConnection();
			//准备sql语句
			String sql="CALL pro_findById(?);";
			//创建callablestatement
			stmt=conn.prepareCall(sql);
			//设置参数
			stmt.setInt(1, 110);
			//发送参数，执行sql
			rs=stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				System.out.println(id + " " + name + " " + gender + " " + age);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}
	/**
	 * 带有输出参数的存储过程
	 */
	@Test
	public void testOut() {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			//获取连接
			conn=JdbcUtil.getConnection();
			//准备sql语句
			String sql="CALL pro_findById2(?,?);";
			//创建callablestatement
			stmt=conn.prepareCall(sql);
			//设置输入参数
			stmt.setInt(1, 110);
			//设置输出参数
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			//发送参数，执行sql
			stmt.executeQuery();//结果不是返回到结果集中，而是返回到输出参数中
			String name=stmt.getString(2);
			System.out.println(name);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}
}
