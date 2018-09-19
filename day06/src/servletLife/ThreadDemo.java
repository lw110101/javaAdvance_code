package servletLife;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadDemo
 */
@WebServlet("/ThreadDemo")
public class ThreadDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int count=1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setContentType("text/html;charset=utf-8");
		synchronized (ThreadDemo.class) {
			resp.getWriter().write("你是当前第"+count+"个访问者!");
			count++;
		}	
	}
}
