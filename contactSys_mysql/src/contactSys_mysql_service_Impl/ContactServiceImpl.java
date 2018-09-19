package contactSys_mysql_service_Impl;

import java.util.List;

import contactSys_mysql_dao.ContactDao;
import contactSys_mysql_dao_Impl.ContactDaoMySQLImpl;
import contactSys_mysql_entity.Contact;
import contactSys_mysql_exception.RepeatNameException;
import contactSys_mysql_service.ContactService;

public class ContactServiceImpl implements  ContactService{
	ContactDao dao=new ContactDaoMySQLImpl();
	@Override
	public void addContact(Contact contact) throws RepeatNameException {
		if(dao.checkName(contact.getName())) {
			
			throw new RepeatNameException("姓名重复，不可使用");
		}
		dao.addContact(contact);
	}

	@Override
	public void deleteContact(String id) {
		dao.deleteContact(id);
	}

	@Override
	public void updateContact(Contact contact) {
		dao.updateContact(contact);
	}

	@Override
	public List<Contact> checkContacts() {
		
		return dao.checkContacts();
	}

	@Override
	public Contact findById(String id) {
		
		return dao.findById(id);
	}
	
}
