package service.impl;

import dao.IAdminDao;
import dao.impl.AdminDao;
import entity.Admin;
import exception.UserNoExistException;
import service.IAdminService;

public class AdminService implements IAdminService {
	// 调用dao的方法
	private IAdminDao dao = new AdminDao();

	@Override
	public void register(Admin admin) throws UserNoExistException {
		try {
			// 先判断用户是否存在
			boolean userName = dao.userExist(admin.getUserName());
			if (userName) {
				// 存在的话，抛出异常
				throw new UserNoExistException("用户名已经存在,注册失败");
			}
			// 不存在，允许注册，数据保存
			dao.save(admin);
		} catch (UserNoExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override /// 登录
	public Admin login(Admin admin) {

		return dao.findByAdmin(admin);
	}

}
