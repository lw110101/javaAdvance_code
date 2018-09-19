package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IEmployeeDao;
import entity.Employee;
import util.JdbcUtils;

public class EmployeeDao implements IEmployeeDao {

	@Override
	public List<Employee> getEmployees() {
		QueryRunner qr = JdbcUtils.getQueryRunner();
		String sql = "select * from employee";

		try {
			return qr.query(sql, new BeanListHandler<Employee>(Employee.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
