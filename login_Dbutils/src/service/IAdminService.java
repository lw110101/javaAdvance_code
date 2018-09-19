package service;

import entity.Admin;
import exception.UserNoExistException;

public interface IAdminService {

	/****** ×¢²á ************/
	void register(Admin admin) throws UserNoExistException;

	/********** µÇÂ¼ ************/
	Admin login(Admin admin);
	
}
