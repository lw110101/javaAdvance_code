package dao;

import entity.Employee;
import util.PageBean;

public interface IEmployeeDao {

	//��ȡ��������
	public void getAll(PageBean<Employee> pb) ;
	
	//ͳ���ܼ�¼��
	public int getTotalCount() ;
}
