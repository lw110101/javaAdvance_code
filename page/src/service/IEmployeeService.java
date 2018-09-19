package service;

import entity.Employee;
import util.PageBean;

public interface IEmployeeService {

	public void getAll(PageBean<Employee> pb) ;
}
