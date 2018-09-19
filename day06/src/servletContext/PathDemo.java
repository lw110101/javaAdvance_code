package servletContext;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PathDemo
 */
@WebServlet("/PathDemo")
public class PathDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		
		/*//ת��
		req.getRequestDispatcher("/target.html").forward(req, resp);
		
		//�ض�λ
		resp.sendRedirect("/day06/target.html");*/
		//html������
		resp.getWriter().write("<html><body><a href='/day06/target.html'>������</a></body></html>");
		//html���ύ
		resp.getWriter().write("<html><body><form action='/day06/target.html'><input type='submit' value='�ύ��'/></form></body></html>");
	
	}
}
