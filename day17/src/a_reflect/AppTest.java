package a_reflect;

import org.junit.Test;

public class AppTest {
	@Test
	public void testSave() throws Exception{
		AdminDao adminDao =new AdminDao();
		Admin admin = adminDao.findById(2);
		System.out.println(admin );
	}
	
}
