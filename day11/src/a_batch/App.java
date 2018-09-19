package a_batch;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class App {

	@Test
	public void testBatch() {
		List<Admin> list=new ArrayList<Admin>();
		for(int i=1;i<10;i++) {
			Admin admin = new Admin();
			admin.setUser("¹ÜÀíÔ±"+i);
			admin.setPwd("root"+i);
			list.add(admin);
		}
		
		AdminDao dao=new AdminDao();
		dao.save(list);
	}
	
	@Test
	public void testStmtBatch() {
		AdminDao dao=new AdminDao();
		dao.stmtBatch();
	}
}
