package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.IAdminDao;
import entity.Admin;
import util.JdbcUtils;

public class AdminDao implements IAdminDao {

	@Override
	public Admin findByNameAndPwd(Admin admin) {
		QueryRunner qr = JdbcUtils.getQueryRunner();
		String sql = "select * from admin where userName=? and password=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), admin.getUserName(), admin.getPassword());
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
