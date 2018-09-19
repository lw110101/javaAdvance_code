package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.IAdminDao;
import entity.Admin;
import util.JdbcUtils;

public class AdminDao implements IAdminDao {

	@Override
	public void register(Admin admin) {
		try {	
			//获取连接
			QueryRunner qr = JdbcUtils.getQueryRunner();
			// 注册sql语句
			String sql = "insert into admin(userName,password) values(?,?)";
			// 执行语句
			qr.update(sql, admin.getUserName(), admin.getPassword());
//			System.out.println("注册成功！");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Admin login(Admin admin) {
		try {	
			//获取连接
			QueryRunner qr = JdbcUtils.getQueryRunner();
			// 登录sql语句
			String sql = "select * from admin where userName=? and password=?";
			// 执行语句
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), admin.getUserName(), admin.getPassword());
			
//			System.out.println("登录成功！");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
