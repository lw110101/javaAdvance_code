package response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//请求重定向
/**
 * Servlet implementation class ResponseDemo2
 */
@WebServlet("/ResponseDemo2")
public class ResponseDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		/*
		response.setStatus(302);
		response.setHeader("location", "/FirstWeb/testLocation.html");
		*/
		response.sendRedirect("/FirstWeb/testLocation.html");
	}

}
