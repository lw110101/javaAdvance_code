package b_auto;

import org.junit.Test;

public class App {

	@Test
	public void test() {
		
		Dept d = new Dept();
		d.setDeptName("Ӧ�ÿ�����");
		//d.setDeptName("��̨ά����");
		
		Employee emp=new Employee();
		emp.setEmpName("����");
		//emp.setEmpName("����");
		emp.setDept(d);
		//����
		EmployeeDao dao=new EmployeeDao();
		dao.save(emp);
	}
}
