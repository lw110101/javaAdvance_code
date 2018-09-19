package contactSys_mysql_servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contactSys_mysql_entity.Contact;
import contactSys_mysql_service.ContactService;
import contactSys_mysql_service_Impl.ContactServiceImpl;

/**��ʾ�����˵��߼�
 * Servlet implementation class ListContactServlet
 */
@WebServlet("/ListContactServlet")
public class ListContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//shift+alt+A   ^(.*)$  \1";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//��xml��ȡ������
		ContactService service=new ContactServiceImpl();
		List<Contact> list=service.checkContacts();
		
		//�ѽ�����͵�jsp
		request.setAttribute("list",list);
		request.getRequestDispatcher("/checkContacts.jsp").forward(request, response);

	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
