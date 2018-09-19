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
 * ����̨����
 * 
 * @author hasee ����3:35:27
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
		// ����
		request.setCharacterEncoding("utf-8");
		// ��ȡ��������
		String method = request.getParameter("method");
		if ("register".equals(method)) {
			register(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*   ��ʽһ��
		 * // ��ȡ�������
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// ��װ����
		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setPassword(password);*/
		/***��ʽ����ʹ��beanutils���**/
		
		Admin admin = WebDbUtils.copyToBean(request, Admin.class);
		// ����service����ע��
		try {
			service.register(admin);
			// ע��ɹ�����ת���ɹ�ҳ��
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (UserNoExistException e) {
			request.setAttribute("message", "�û����Ѿ�����");
			// ע��ʧ�ܣ���ת��ע��ҳ��
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// ��������, ��ת������ҳ��
			response.sendRedirect(request.getContextPath() + "/error.jsp");

		}
	}
}
