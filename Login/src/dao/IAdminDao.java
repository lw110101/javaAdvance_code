package dao;

import entity.Admin;

public interface IAdminDao {
	/**
	 * ע�Ṧ��
	 */
	public void register(Admin admin) ;
	
	/**
	 * ��¼����
	 */
	public Admin login(Admin admin);

}
