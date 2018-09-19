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
		System.out.println("1.����������ʵ��");
	}

	public void init(FilterConfig fConfig) throws ServletException {

		System.out.println("2.��ʼ��������������");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("3.��ʼִ�й�����ҵ����");

			//����
		chain.doFilter(request, response);
		
		System.out.println("5.servletִ���꣬�ص�������");
	}

	public void destroy() {
		
		System.out.println("6.��������");
	}

}
