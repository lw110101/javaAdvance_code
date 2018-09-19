package contactSys_jsp_dao;

import java.util.List;

import contactSys_jsp_entity.Contact;

public interface ContactDao {
	//�����ϵ��
		public void addContact(Contact contact) ;
	//ɾ����ϵ��	
		public void deleteContact(String id);
	//�޸���ϵ��
		public void updateContact(Contact contact);
	//�鿴������ϵ��
		public List<Contact> checkContacts();
	//����id��ѯ��ϵ��
		public Contact findById(String id);
		//����Ƿ����ظ�����
		public boolean checkName(String name);
}
