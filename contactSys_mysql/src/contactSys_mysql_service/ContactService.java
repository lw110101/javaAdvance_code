package contactSys_mysql_service;

import java.util.List;

import contactSys_mysql_entity.Contact;
import contactSys_mysql_exception.RepeatNameException;

public interface ContactService {
	//�����ϵ��
		public void addContact(Contact contact)throws RepeatNameException;
	//ɾ����ϵ��	
		public void deleteContact(String id);
	//�޸���ϵ��
		public void updateContact(Contact contact);
	//�鿴������ϵ��
		public List<Contact> checkContacts();
	//����id��ѯ��ϵ��
		public Contact findById(String id);
}
