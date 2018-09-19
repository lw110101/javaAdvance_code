package dao;

import entity.User;

public class UserDao {

	// 模拟登录
	public User login(User user) {
		if (user.getName().equals("lw") && user.getPassword().equals("123")) {
			//登录成功
			return user;
		} else
			//登录失败
			return null;
	}
	
	/**
	 * 模拟注册
	 */
	public void register(User user) {
		System.out.println("注册成功：用户，" + user.getName());
	}
}
