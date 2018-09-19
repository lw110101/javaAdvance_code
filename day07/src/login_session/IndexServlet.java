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
		 * 二：登录主页逻辑
		 */
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		String html = "";
		//1.得到session对象
		HttpSession session=request.getSession(false);
		//在用户主页，判断session不为空且存在指定的属性才视为登录成功！才能访问资源
		if(session==null) {
			//没有登录成功，跳回登录页面
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//获取数据
		String name=(String)session.getAttribute("loginName");
		if(name==null) {
			//没有登录成功，跳回登录页面
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//写提示页面
		html+="<html><body>欢迎回来!"+name+"<a href='"+request.getContextPath()+"/LogoutServlet'>【安全退出】</a></body></html>";
		writer.write(html);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
