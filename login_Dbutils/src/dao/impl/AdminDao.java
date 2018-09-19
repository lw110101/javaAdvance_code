package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.IAdminDao;
import entity.Admin;
import util.JdbcUtil;

public class AdminDao implements IAdminDao {
	private Connection conn;
	private QueryRunner qr = new QueryRunner();  // 创建核心工具类

	@Override /* 注册 */
	public void save(Admin admin) {
		String sql = "insert into admin(userName,password) values(?,?)";
		try {
			conn = JdbcUtil.getConnection();
			Object[] paramValues= {admin.getUserName(), admin.getPassword()};
			// 执行sql
			qr.update(conn, sql, paramValues );
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null);
		}
	}

	@Override /* 根据用户名与密码查询 */
	public Admin findByAdmin(Admin admin) {
		String sql = "select * from admin where userName=? and password=?";
		
		try {
			// 建立连接
			conn = JdbcUtil.getConnection();
			Object[] paramValues= {admin.getUserName(), admin.getPassword()};
			// 执行sql
			Admin rs_admin = qr.query(conn, sql, new BeanHandler<Admin>(Admin.class),paramValues );
			return rs_admin;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null);
		}
		
	}

	@Override
	public boolean userExist(String name) {
		String sql = "select id from admin where userName=?";
		try {
			// 建立连接
			conn = JdbcUtil.getConnection();
			// 执行sql
			Integer id = qr.query(conn, sql, new ScalarHandler<Integer>(),name);
			if(id!=null) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null);
		}
	}

}
