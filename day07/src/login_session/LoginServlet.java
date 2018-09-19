package login_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/**
 * һ����¼�߼�
 */
		request.setCharacterEncoding("utf-8");
		
		// ��ȡ������
		String name = request.getParameter("userName");
		String password = request.getParameter("userPwd");
		if ("����".equals(name)) {
			if ("123456".equals(password)) {
			//һ����¼�ɹ��󣬰��û����ݱ���session������
				System.out.println("��¼�ɹ�!");
				//����session����
				HttpSession session=request.getSession();
				//2.�����ݱ��浽session����
				session.setAttribute("loginName", name);
				//3.��ת�����û���ҳ
				response.sendRedirect(request.getContextPath()+"/IndexServlet");
			} else {
				System.out.println("�������!");
				//��ת��ʧ��ҳ��
				response.sendRedirect(request.getContextPath()+"/fail.html");
			}
		} else {
			System.out.println("�ʺŲ����ڣ�");
			response.sendRedirect(request.getContextPath()+"/fail.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
