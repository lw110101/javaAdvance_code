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
		
		// 拿到传递过来的id值
		String id = request.getParameter("id");
	
		// 通过id删除对应的联系人
		ContactDao dao = new ContactDaoImpl();
		//在火狐浏览器中以Get方式提交带参数的数据，会重复提交两次。
		Contact contact=dao.findById(id);
		if(contact!=null) {
			dao.deleteContact(id);
		}
		//返回查看联系人主页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
