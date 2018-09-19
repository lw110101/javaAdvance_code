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
 * ����id���Ҷ�Ӧ����ϵ��
 * @author hasee
 */
@WebServlet("/QueryContactServlet")
public class QueryContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// �õ����ݹ�����idֵ
		String id = request.getParameter("id");
		// ͨ��id���Ҷ�Ӧ����ϵ��
		ContactService service=new ContactServiceImpl();
		Contact contact=service.findById(id);
		//ת����jsp
		request.setAttribute("contact", contact);
		request.getRequestDispatcher("/updateContact.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
