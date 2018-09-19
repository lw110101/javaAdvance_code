package servletLife;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class InitDemo
 */
@WebServlet(urlPatterns= "/InitDemo")
public class InitDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*@Override//有参的init方法，不要覆盖，生命周期方法，一定被tomcat调用
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("有参的init方法！");
	}*/
	@Override//无参的init方法，初始化代码写在这里
	public void init() throws ServletException {
		System.out.println("无参的init方法！");
	}
	
}
