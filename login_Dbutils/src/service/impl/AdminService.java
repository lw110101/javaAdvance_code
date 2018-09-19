package service.impl;

import dao.IAdminDao;
import dao.impl.AdminDao;
import entity.Admin;
import exception.UserNoExistException;
import service.IAdminService;

public class AdminService implements IAdminService {
	// ����dao�ķ���
	private IAdminDao dao = new AdminDao();

	@Override
	public void register(Admin admin) throws UserNoExistException {
		try {
			// ���ж��û��Ƿ����
			boolean userName = dao.userExist(admin.getUserName());
			if (userName) {
				// ���ڵĻ����׳��쳣
				throw new UserNoExistException("�û����Ѿ�����,ע��ʧ��");
			}
			// �����ڣ�����ע�ᣬ���ݱ���
			dao.save(admin);
		} catch (UserNoExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override /// ��¼
	public Admin login(Admin admin) {

		return dao.findByAdmin(admin);
	}

}
