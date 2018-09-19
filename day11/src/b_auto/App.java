package b_auto;

import org.junit.Test;

public class App {

	@Test
	public void test() {
		
		Dept d = new Dept();
		d.setDeptName("应用开发部");
		//d.setDeptName("后台维护部");
		
		Employee emp=new Employee();
		emp.setEmpName("张三");
		//emp.setEmpName("李四");
		emp.setDept(d);
		//调用
		EmployeeDao dao=new EmployeeDao();
		dao.save(emp);
	}
}
