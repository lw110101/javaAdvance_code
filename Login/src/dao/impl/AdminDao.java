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
			//��ȡ����
			QueryRunner qr = JdbcUtils.getQueryRunner();
			// ע��sql���
			String sql = "insert into admin(userName,password) values(?,?)";
			// ִ�����
			qr.update(sql, admin.getUserName(), admin.getPassword());
//			System.out.println("ע��ɹ���");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Admin login(Admin admin) {
		try {	
			//��ȡ����
			QueryRunner qr = JdbcUtils.getQueryRunner();
			// ��¼sql���
			String sql = "select * from admin where userName=? and password=?";
			// ִ�����
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), admin.getUserName(), admin.getPassword());
			
//			System.out.println("��¼�ɹ���");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
