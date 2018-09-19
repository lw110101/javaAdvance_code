package a_filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(filterName = "/EncodingFilter", urlPatterns = { "/IndexServlet" })
public class EncodingFilter implements Filter {

	public EncodingFilter() {
		System.out.println("1.创建第二个过滤器实例");

	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("2.第二个过滤器的初始化");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 设置POST提交的请求的编码
		request.setCharacterEncoding("UTF-8");
		/*// 设置响应体的编码
		response.setCharacterEncoding("UTF-8");
		// 设置页面打开时候时候的编码格式、 设置响应体的编码
		response.setContentType("text/html;charset=UTF-8");*/
		
		//get提交方式     tomcat8以后不用修改

		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
