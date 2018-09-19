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
		
		// 拿到传递过来的id值
		String id = request.getParameter("id");
	
		// 通过id删除对应的联系人
		ContactService service=new ContactServiceImpl();

		//在火狐浏览器中以Get方式提交带参数的数据，会重复提交两次。
		Contact contact=service.findById(id);
		if(contact!=null) {
			service.deleteContact(id);
		}
		//返回查看联系人主页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
