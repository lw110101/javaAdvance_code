package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo1
 */
@WebServlet("/SessionDemo1")
public class SessionDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����Ự����
		HttpSession session=request.getSession();
		session.setAttribute("name", "����");
		/**
		 * ����session
		 */
		session.setMaxInactiveInterval(30);//�ֲ���Чʱ����30��
		/*
		
		*/
		/**
		 * ����ر��������JSESSIONID��ʧ���⣺�ֶ�����һ��Ӳ�̱����cookie�������
		 */
		Cookie cookie=new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(1*30*24*60*60);
		response.addCookie(cookie);
	}

}
