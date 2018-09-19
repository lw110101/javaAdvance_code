package dao;

import entity.Employee;
import util.PageBean;

public interface IEmployeeDao {

	//获取所有数据
	public void getAll(PageBean<Employee> pb) ;
	
	//统计总记录数
	public int getTotalCount() ;
}
