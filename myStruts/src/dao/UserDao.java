package dao;

import entity.User;

public class UserDao {

	// ģ���¼
	public User login(User user) {
		if (user.getName().equals("lw") && user.getPassword().equals("123")) {
			//��¼�ɹ�
			return user;
		} else
			//��¼ʧ��
			return null;
	}
	
	/**
	 * ģ��ע��
	 */
	public void register(User user) {
		System.out.println("ע��ɹ����û���" + user.getName());
	}
}
