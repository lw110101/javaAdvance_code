package contactSys_jsp_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contactSys_jsp_entity.Contact;
import contactSys_jsp_service.ContactService;
import contactSys_jsp_service_Impl.ContactServiceImpl;

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
		ContactService service=new ContactServiceImpl();

		//�ڻ�����������Get��ʽ�ύ�����������ݣ����ظ��ύ���Ρ�
		Contact contact=service.findById(id);
		if(contact!=null) {
			service.deleteContact(id);
		}
		//���ز鿴��ϵ����ҳ��
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
