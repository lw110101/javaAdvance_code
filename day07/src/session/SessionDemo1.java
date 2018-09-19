package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo1
 */
@WebServlet("/SessionDemo1")
public class SessionDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//保存会话数据
		HttpSession session=request.getSession();
		session.setAttribute("name", "张三");
		/**
		 * 设置session
		 */
		session.setMaxInactiveInterval(30);//局部有效时间是30秒
		/*
		
		*/
		/**
		 * 解决关闭浏览器，JSESSIONID丢失问题：手动发送一个硬盘保存的cookie给浏览器
		 */
		Cookie cookie=new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(1*30*24*60*60);
		response.addCookie(cookie);
	}

}
