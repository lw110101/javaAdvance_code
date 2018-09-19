package dao;

import entity.Admin;

public interface IAdminDao {

	/*保存用户名密码*/
	void save(Admin admin);
	
	/**根据用户名密码查询*/
	Admin findByAdmin(Admin admin);
	
	/*查询用户名是否存在*/
	boolean userExist(String userName);
}
