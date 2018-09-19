package service;

import dao.UserDao;
import entity.User;


public class UserService {
	
	private UserDao ud = new UserDao();

	// Ä£ÄâµÇÂ½
	public User login(User user){
		return ud.login(user);
	}
	
	// Ä£Äâ×¢²á
	public void register(User user) {
		ud.register(user);
	}
}
