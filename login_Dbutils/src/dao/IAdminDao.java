package dao;

import entity.Admin;

public interface IAdminDao {

	/*�����û�������*/
	void save(Admin admin);
	
	/**�����û��������ѯ*/
	Admin findByAdmin(Admin admin);
	
	/*��ѯ�û����Ƿ����*/
	boolean userExist(String userName);
}
