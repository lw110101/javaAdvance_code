package contactSys;

import java.util.List;
/**
 * ����һ����ϵ�˲����ӿ�
 * @author hasee
 */
public interface ContactOperator {
//�����ϵ��
	public void addContact(Contact contact);
//ɾ����ϵ��	
	public void deleteContact(String id);
//�޸���ϵ��
	public void updateContact(Contact contact);
//�鿴������ϵ��
	public List<Contact> checkContacts();
}
