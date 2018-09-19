package servletContext;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextDemo3
 */
@WebServlet("/ContextDemo4")
public class ContextDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context=this.getServletContext();
		//获取域对象数据
		String name=(String)context.getAttribute("name");
		System.out.println("name="+name);
		
		Object student=context.getAttribute("student");
		System.out.println(student);
	}
	
	

}
