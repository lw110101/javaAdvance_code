package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;

public class LoginAction {
	/**
	 * �����½����
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;

		// 1. ��ȡ�������ݣ���װ
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);

		// 2. ����Service
		UserService userService = new UserService();
		User userInfo = userService.login(user);
		// 3. ��ת
		if (userInfo == null) {
			// ��½ʧ��
//			request.getRequestDispatcher("/login.jsp").forward(request,
//					response);
//			uri = request.getRequestDispatcher("/login.jsp");
			uri = "loginFaild";   // loginFaild  = /login.jsp
		} else {
			// ��½�ɹ�
			request.getSession().setAttribute("userInfo", userInfo);
			// ��ҳ
//			response.sendRedirect(request.getContextPath() + "/index.jsp");
//			uri = "/index.jsp";
			uri = "loginSuccess";  // loginSuccess = /index.jsp
		}
		return uri;
	}
}
