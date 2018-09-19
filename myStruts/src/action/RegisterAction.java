package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;

public class RegisterAction {
	public Object register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. ��ȡ�������ݣ���װ
		String name = request.getParameter("name");
		String password = request.getParameter("pwd");
		User user = new User();
		user.setName(name);
		user.setPassword(password);

		// 2. ����Service
		UserService userService = new UserService();
		userService.register(user);
		// 3. ��ת
//		request.getRequestDispatcher("/login.jsp").forward(request, response);
		//uri = request.getRequestDispatcher("/login.jsp");
		return "registerSuccess"; //����ע��ı�ǣ�   registerSuccess = /login.jsp
	}
}
