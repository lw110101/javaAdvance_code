package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Admin;
import service.IAdminService;
import service.impl.AdminService;

/**
 * 1.登陆 2.退出 Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IAdminService service = new AdminService();

	// 跳转资源
	private String uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//编码
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");

		if ("out".equals(method)) {
			// 退出操作，调用推出方法
			out(request, response);
		} else if ("login".equals(method)) {
			// 登陆操作, 调用登陆方法
			login(request, response);
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取参数
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		Admin admin = new Admin();
		admin.setUserName(name);
		admin.setPassword(password);
		try {
			Admin logInfo = service.findByNameAndPwd(admin);
			if (logInfo == null) {
				// 登陆失败，回到登陆页面
				uri = "/login.jsp";
			} else {
				// 登陆成功
				//// 先，保存数据到session
				request.getSession().setAttribute("info", logInfo);
				// 【在线列表： 1. 先从servletContext中拿到在线列表集合; (onLineUserList)
				// 2. 当前用户放入“在线列表集合中”】
				ServletContext servletContext = getServletContext();
				@SuppressWarnings("unchecked")
				List<Admin> onlineList = (List<Admin>) servletContext.getAttribute("onlineList");
				if (onlineList != null) {
					// 实现3: 添加当前登陆者
					onlineList.add(logInfo);
				}
				// 再，跳转到首页显示servlet
				uri = "/ListEmpLoyeeServlet";
			}

		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		// 3. 跳转
		request.getRequestDispatcher(uri).forward(request, response);
	}

	private void out(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			// 销毁session
			session.invalidate();
		}
		// 3. 跳转(登陆)
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
