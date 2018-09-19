package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Admin;
import exception.UserNoExistException;
import service.IAdminService;
import service.impl.AdminService;
import util.WebDbUtils;

/**
 * 控制台开发
 * 
 * @author hasee 下午3:35:27
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IAdminService service = new AdminService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 编码
		request.setCharacterEncoding("utf-8");
		// 获取操作类型
		String method = request.getParameter("method");
		if ("register".equals(method)) {
			register(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*   方式一：
		 * // 获取请求参数
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// 封装数据
		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setPassword(password);*/
		/***方式二：使用beanutils组件**/
		
		Admin admin = WebDbUtils.copyToBean(request, Admin.class);
		// 调用service保存注册
		try {
			service.register(admin);
			// 注册成功，跳转到成功页面
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (UserNoExistException e) {
			request.setAttribute("message", "用户名已经存在");
			// 注册失败，跳转到注册页面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// 其他错误, 跳转到错误页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");

		}
	}
}
