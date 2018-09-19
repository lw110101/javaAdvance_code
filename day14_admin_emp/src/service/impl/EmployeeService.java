package service.impl;

import java.util.List;

import dao.impl.EmployeeDao;
import entity.Employee;
import service.IEmployeeService;

public class EmployeeService implements IEmployeeService {

	@Override
	public List<Employee> getEmployees() {
		try {
			return new EmployeeDao().getEmployees();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
