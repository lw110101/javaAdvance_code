package liuwei03.dao;

import liuwei03.entity.Admin;

public interface IAdminDao {
	/**
	 * 注册功能
	 */
	public void register(Admin admin) ;
	
	/**
	 * 登录验证功能
	 */
	public Admin login(Admin admin);

}
