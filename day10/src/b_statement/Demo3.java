package b_statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class Demo3 {
	/**
	 * 执行DQL语句
	 */
	@Test
	public void testQuery() {
		Connection conn = null;
		Statement state = null;
		try {
			//1.创建连接
			conn = JdbcUtil.getConnection();
			//2.创建Statement
			state = conn.createStatement();
			//准备sql
			String sql = "select * from student";
			//4.执行sql
			ResultSet result=state.executeQuery(sql);
			//5.遍历结果
			while(result.next()) {
				//通过索引值获取数据
				/*int id=result.getInt(1);
				String name=result.getString(2);
				String gender=result.getString(3);
				int age=result.getInt(4);*/
				//通过字段名获取数据
				int id=result.getInt("id");
				String name=result.getString("name");
				String gender=result.getString("gender");
				int age=result.getInt("age");
				
				System.out.println(id+" "+name+" "+gender+" "+age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, state);
		}
	}
}
