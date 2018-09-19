package service;

import entity.Admin;
import exception.UserNoExistException;

public interface IAdminService {

	/****** ע�� ************/
	void register(Admin admin) throws UserNoExistException;

	/********** ��¼ ************/
	Admin login(Admin admin);
	
}
