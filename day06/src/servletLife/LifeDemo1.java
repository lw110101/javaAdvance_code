package servletLife;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeDemo1
 */
@WebServlet(urlPatterns= {"/LifeDemo1"},loadOnStartup=1)
public class LifeDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 */
	public LifeDemo1() {
		System.out.println("1.构造方法被调用,Servlet对象创建了!");
	}
	/**
	 * 初始化方法
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("2.初始化方法被调用了！");
	}
	/**
	 * service方法
	 */
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("3.service被调用了");
	}
	/**
	 * 销毁方法destroy
	 */
	@Override
	public void destroy() {
		System.out.println("4.销毁方法被调用，对象被销毁了! ");
	}
}
