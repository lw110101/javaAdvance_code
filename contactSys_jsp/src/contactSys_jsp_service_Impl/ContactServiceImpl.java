package contactSys_jsp_service_Impl;

import java.util.List;

import contactSys_jsp_dao.ContactDao;
import contactSys_jsp_dao_Impl.ContactDaoImpl;
import contactSys_jsp_entity.Contact;
import contactSys_jsp_exception.RepeatNameException;
import contactSys_jsp_service.ContactService;

public class ContactServiceImpl implements  ContactService{
	ContactDao dao=new ContactDaoImpl();
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
