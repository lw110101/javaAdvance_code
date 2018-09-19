package login_session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * ������¼��ҳ�߼�
		 */
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		String html = "";
		//1.�õ�session����
		HttpSession session=request.getSession(false);
		//���û���ҳ���ж�session��Ϊ���Ҵ���ָ�������Բ���Ϊ��¼�ɹ������ܷ�����Դ
		if(session==null) {
			//û�е�¼�ɹ������ص�¼ҳ��
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//��ȡ����
		String name=(String)session.getAttribute("loginName");
		if(name==null) {
			//û�е�¼�ɹ������ص�¼ҳ��
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//д��ʾҳ��
		html+="<html><body>��ӭ����!"+name+"<a href='"+request.getContextPath()+"/LogoutServlet'>����ȫ�˳���</a></body></html>";
		writer.write(html);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
