package contactSys;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestContactOperatorImp {

	ContactOperator operator=null;
	//初始化对象实例
	@Before
	public void init() {
		operator = new ContactOperatorImp();
	}

	@Test
	public void testAddContact() {
		Contact contact=new Contact();
		contact.setId("003");
		contact.setName("李四");
		contact.setGender("男");
		contact.setAge(30);
		contact.setPhone("123456");
		contact.setQq("4545545");
		contact.setEmail("12144354@qq.com");
		contact.setAd("广州");
		operator.addContact(contact);
	}
	
	@Test
	public void testUpdateContact() {
		Contact contact=new Contact();
		contact.setId("003");
		operator.updateContact(contact);	
	}
	
	@Test
	public void testDeleteContact() {
		
		operator.deleteContact("003");
	}
	
	@Test
	public void testCheckContact() {
		List<Contact> list=operator.checkContacts();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
