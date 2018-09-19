package a_filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class HelloFilter
 */
@WebFilter(filterName="/HelloFilter" ,value = {"/HelloServlet"})
public class HelloFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public HelloFilter() {
		System.out.println("1.创建过滤器实例");
	}

	public void init(FilterConfig fConfig) throws ServletException {

		System.out.println("2.初始化函数被调用了");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("3.开始执行过滤器业务处理");

			//放行
		chain.doFilter(request, response);
		
		System.out.println("5.servlet执行完，回到过滤器");
	}

	public void destroy() {
		
		System.out.println("6.对象销毁");
	}

}
