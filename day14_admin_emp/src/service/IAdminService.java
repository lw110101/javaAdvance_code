package service;

import entity.Admin;

public interface IAdminService {
	
	Admin findByNameAndPwd(Admin admin);
}
