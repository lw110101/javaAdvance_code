package contactSys_jsp_service;

import java.util.List;

import contactSys_jsp_entity.Contact;
import contactSys_jsp_exception.RepeatNameException;

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
