package service.impl;

import dao.impl.AdminDao;
import entity.Admin;
import service.IAdminService;

public class AdminService implements IAdminService {

	@Override
	public Admin findByNameAndPwd(Admin admin) {
		try {
			return new AdminDao().findByNameAndPwd(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
