package contactSys_jsp_dao;

import java.util.List;

import contactSys_jsp_entity.Contact;

public interface ContactDao {
	//添加联系人
		public void addContact(Contact contact) ;
	//删除联系人	
		public void deleteContact(String id);
	//修改联系人
		public void updateContact(Contact contact);
	//查看所有联系人
		public List<Contact> checkContacts();
	//根据id查询联系人
		public Contact findById(String id);
		//简称是否有重复姓名
		public boolean checkName(String name);
}
