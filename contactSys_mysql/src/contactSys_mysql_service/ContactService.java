package contactSys_mysql_service;

import java.util.List;

import contactSys_mysql_entity.Contact;
import contactSys_mysql_exception.RepeatNameException;

public interface ContactService {
	//添加联系人
		public void addContact(Contact contact)throws RepeatNameException;
	//删除联系人	
		public void deleteContact(String id);
	//修改联系人
		public void updateContact(Contact contact);
	//查看所有联系人
		public List<Contact> checkContacts();
	//根据id查询联系人
		public Contact findById(String id);
}
