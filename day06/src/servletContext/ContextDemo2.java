package servletContext;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextDemo2
 */
@WebServlet(
		urlPatterns = { "/ContextDemo2" }, 
		initParams = { 
				@WebInitParam(name = "userName", value = "张三"), 
				@WebInitParam(name = "password", value = "123456")
		})
public class ContextDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
//获取web的应用参数
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context=this.getServletContext();
		
		String aaaValue=context.getInitParameter("aaa");
		System.out.println(aaaValue);
		
		Enumeration<String> enums=context.getInitParameterNames();
		while(enums.hasMoreElements()) {
			String param=enums.nextElement();
			String value=context.getInitParameter(param);
			System.out.println(param+"="+value);
		}
		System.out.println(context.getAttribute("name"));
	}
}
