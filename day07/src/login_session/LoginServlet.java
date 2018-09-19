package login_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/**
 * 一：登录逻辑
 */
		request.setCharacterEncoding("utf-8");
		
		// 获取表单数据
		String name = request.getParameter("userName");
		String password = request.getParameter("userPwd");
		if ("张三".equals(name)) {
			if ("123456".equals(password)) {
			//一、登录成功后，把用户数据保存session对象中
				System.out.println("登录成功!");
				//创建session对象
				HttpSession session=request.getSession();
				//2.把数据保存到session域中
				session.setAttribute("loginName", name);
				//3.跳转到到用户主页
				response.sendRedirect(request.getContextPath()+"/IndexServlet");
			} else {
				System.out.println("密码错误!");
				//跳转到失败页面
				response.sendRedirect(request.getContextPath()+"/fail.html");
			}
		} else {
			System.out.println("帐号不存在！");
			response.sendRedirect(request.getContextPath()+"/fail.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
