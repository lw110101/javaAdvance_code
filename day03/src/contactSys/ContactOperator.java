package contactSys;

import java.util.List;
/**
 * 定义一个联系人操作接口
 * @author hasee
 */
public interface ContactOperator {
//添加联系人
	public void addContact(Contact contact);
//删除联系人	
	public void deleteContact(String id);
//修改联系人
	public void updateContact(Contact contact);
//查看所有联系人
	public List<Contact> checkContacts();
}
