package c_exp;

import org.junit.Test;

public class App {

	@Test
	public void test() {
		AdminDao dao=new AdminDao();
		System.out.println(dao.findById(2));
		
		System.out.println(dao.getAll());
	}
}
