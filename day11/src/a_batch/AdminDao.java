package a_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import util.JdbcUtil;

/**
 * / 封装所有的与数据库的操作
 * @author hasee 下午3:50:51
 */
public class AdminDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	public void save(List<Admin> list) {
		//插入数据
		//String sql="insert into admin(userName,password) values(?,?)";
		String sql="insert into employee(empName,dept_id) values(?,?)";
		
		try {
		//获取连接
		conn=JdbcUtil.getConnection();
		//创建pstmt对象
		pstmt=conn.prepareStatement(sql);
		for(int i=0;i<list.size();i++) {
			Admin admin=list.get(i);
			//设置参数
			pstmt.setString(1, admin.getUser());
			pstmt.setString(2, admin.getPwd());
			//添加批处理
			pstmt.addBatch();
			// 测试：每5条执行一次批处理
			if(i%5==0) {
				//批量执行
				pstmt.executeBatch();
				//清空批处理
				pstmt.clearBatch();
			}
		}
		// 批量执行 
		pstmt.executeBatch();
		// 清空批处理
		pstmt.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt);
		}
	}
	
	//statement的批处理执行
	public void stmtBatch() {
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 创建stmtdui对象
			stmt = conn.createStatement();
			//准备数据
			String sql1="insert into admin(userName,password) values('root','root')";
			String sql2="update admin set userName='superAdmin' where id=10";
			
			//添加批处理
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			//执行批处理
			stmt.executeBatch();
			//清空批处理
			stmt.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, stmt);
		}
	}
}
