package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;

public class LoginAction {
	/**
	 * 处理登陆请求
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;

		// 1. 获取请求数据，封装
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);

		// 2. 调用Service
		UserService userService = new UserService();
		User userInfo = userService.login(user);
		// 3. 跳转
		if (userInfo == null) {
			// 登陆失败
//			request.getRequestDispatcher("/login.jsp").forward(request,
//					response);
//			uri = request.getRequestDispatcher("/login.jsp");
			uri = "loginFaild";   // loginFaild  = /login.jsp
		} else {
			// 登陆成功
			request.getSession().setAttribute("userInfo", userInfo);
			// 首页
//			response.sendRedirect(request.getContextPath() + "/index.jsp");
//			uri = "/index.jsp";
			uri = "loginSuccess";  // loginSuccess = /index.jsp
		}
		return uri;
	}
}
