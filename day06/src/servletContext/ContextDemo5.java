package servletContext;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContextDemo5")
public class ContextDemo5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("name", "lw");
		//转发
		RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/GetDataDemo");
		dispatcher.forward(req, resp);
		
		/*//重定向
		resp.sendRedirect("/day06/index.html");*/
	}
}
