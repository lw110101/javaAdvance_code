package dao;

import entity.Admin;

public interface IAdminDao {

	Admin findByNameAndPwd(Admin admin);
}
