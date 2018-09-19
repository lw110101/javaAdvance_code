package c_jdbc;

import org.junit.Test;

public class AdminDaoTest {

	@Test
	public void test() {
		AdminDao adminDao = new AdminDao();
		
		//adminDao.insert(new Admin("π‹¿Ì‘±10","root10"));
		
		//adminDao.delete(11);
		
		/*List<Admin> list = adminDao.findAll();
		System.out.println(list);
		*/
		Admin admin = adminDao.findById(13);
		System.out.println(admin);
	}
}
