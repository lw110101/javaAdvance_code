package service.impl;

import dao.IEmployeeDao;
import dao.impl.EmployeeDao;
import entity.Employee;
import service.IEmployeeService;
import util.PageBean;

public class EmployeeService implements IEmployeeService {

	private IEmployeeDao dao = new EmployeeDao();

	@Override
	public void getAll(PageBean<Employee> pb) {

		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
