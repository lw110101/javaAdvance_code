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
 * 1.��½ 2.�˳� Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IAdminService service = new AdminService();

	// ��ת��Դ
	private String uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");

		if ("out".equals(method)) {
			// �˳������������Ƴ�����
			out(request, response);
		} else if ("login".equals(method)) {
			// ��½����, ���õ�½����
			login(request, response);
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ����
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		Admin admin = new Admin();
		admin.setUserName(name);
		admin.setPassword(password);
		try {
			Admin logInfo = service.findByNameAndPwd(admin);
			if (logInfo == null) {
				// ��½ʧ�ܣ��ص���½ҳ��
				uri = "/login.jsp";
			} else {
				// ��½�ɹ�
				//// �ȣ��������ݵ�session
				request.getSession().setAttribute("info", logInfo);
				// �������б� 1. �ȴ�servletContext���õ������б���; (onLineUserList)
				// 2. ��ǰ�û����롰�����б����С���
				ServletContext servletContext = getServletContext();
				@SuppressWarnings("unchecked")
				List<Admin> onlineList = (List<Admin>) servletContext.getAttribute("onlineList");
				if (onlineList != null) {
					// ʵ��3: ��ӵ�ǰ��½��
					onlineList.add(logInfo);
				}
				// �٣���ת����ҳ��ʾservlet
				uri = "/ListEmpLoyeeServlet";
			}

		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		// 3. ��ת
		request.getRequestDispatcher(uri).forward(request, response);
	}

	private void out(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			// ����session
			session.invalidate();
		}
		// 3. ��ת(��½)
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
