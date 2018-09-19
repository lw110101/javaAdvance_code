package contactSys_web_test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contactSys_web_dao.ContactDao;
import contactSys_web_dao_Impl.ContactDaoImpl;
import contactSys_web_entity.Contact;

public class ContactDaoImplTest {
	ContactDao dao=null;
	//初始化对象实例
	@Before
	public void init() {
		dao=new ContactDaoImpl();
	}
	//测试添加联系人
	@Test
	public void testAddContact() {
		Contact contact=new Contact();
//		contact.setId("110");
		contact.setName("张三");
		contact.setGender("男");
		contact.setAd("江西南昌");
		contact.setAge(25);
		contact.setQq("111111");
		contact.setEmail("12331@qq.com");
		contact.setPhone("21211");
		dao.addContact(contact);
	}
	//测试删除联系人
	@Test
	public void testDeleteContact() {
		dao.deleteContact("e2fb106b15bb454b9a06b4bf4942eaa9");
	}
	@Test
	public void testUpdateContact() {
		Contact contact=new Contact();
		contact.setId("fac74ed4e0014ae6b092c7a755d0b708");
		contact.setName("陈七");
		contact.setGender("女");
		contact.setAd("江西萍乡");
		contact.setAge(45);
		contact.setQq("111451");
		contact.setEmail("4545431@qq.com");
		contact.setPhone("223511");
		dao.updateContact(contact);
	}
	@Test
	public void testCheckContacts() {
		List<Contact> list=dao.checkContacts();
		for(Contact c:list) {
			System.out.println(c);
		}
	}
	@Test
	public void testFindById() {
		Contact contact=dao.findById("7e2904a4e6204cb6a4967ba654770ccd");
		System.out.println("联系人："+contact);
	}
	
}
