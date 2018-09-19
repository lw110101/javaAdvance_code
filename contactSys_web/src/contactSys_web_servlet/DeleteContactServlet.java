package contactSys_web_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contactSys_web_dao.ContactDao;
import contactSys_web_dao_Impl.ContactDaoImpl;
import contactSys_web_entity.Contact;

/**
 * Servlet implementation class DeleteContactServlet
 */
@WebServlet("/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �õ����ݹ�����idֵ
		String id = request.getParameter("id");
	
		// ͨ��idɾ����Ӧ����ϵ��
		ContactDao dao = new ContactDaoImpl();
		//�ڻ�����������Get��ʽ�ύ�����������ݣ����ظ��ύ���Ρ�
		Contact contact=dao.findById(id);
		if(contact!=null) {
			dao.deleteContact(id);
		}
		//���ز鿴��ϵ����ҳ��
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
