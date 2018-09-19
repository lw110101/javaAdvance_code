package contactSys_web_dao;

import java.util.List;

import contactSys_web_entity.Contact;

public interface ContactDao {
	//添加联系人
		public void addContact(Contact contact);
	//删除联系人	
		public void deleteContact(String id);
	//修改联系人
		public void updateContact(Contact contact);
	//查看所有联系人
		public List<Contact> checkContacts();
	//根据id查询联系人
		public Contact findById(String id);
}
