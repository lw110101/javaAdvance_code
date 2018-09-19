package test;

import org.junit.Test;

import dao.IEmployeeDao;
import dao.impl.EmployeeDao;

public class TestDao {

	@Test
	public void testGetTotalCount() {
		IEmployeeDao dao=new EmployeeDao();
		int totalCount = dao.getTotalCount();
		System.out.println("总的记录数:"+totalCount);
	}
}
