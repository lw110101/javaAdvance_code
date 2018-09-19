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

/**�޸���ϵ�˵��߼�
 * Servlet implementation class UpdateContactServlet
 */
@WebServlet("/UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ����
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String age=request.getParameter("age");
		String phone=request.getParameter("phone");
		String qq=request.getParameter("qq");
		String email=request.getParameter("email");
		String ad=request.getParameter("ad");
		//��װ����
		Contact contact=new Contact();
		contact.setId(id);
		contact.setName(name);
		contact.setGender(gender);
		contact.setAge(Integer.parseInt(age));
		contact.setQq(qq);
		contact.setEmail(email);
		contact.setPhone(phone);
		contact.setAd(ad);
		//����dao��updateContact����
		ContactService service=new ContactServiceImpl();
		service.updateContact(contact);
		//3.��ת����ѯ��ϵ�˵�ҳ��
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
