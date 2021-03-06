package login_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/*三、安全退出：	删除掉session对象中指定的loginName属性即可！ */	
		HttpSession session=request.getSession(false);
	
		if(session!=null) {
			//2.删除属性
			session.removeAttribute("loginName");	
		}
		//跳转登录页面
		response.sendRedirect(request.getContextPath()+"/login.html"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
