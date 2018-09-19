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
		System.out.println("1.�����ڶ���������ʵ��");

	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("2.�ڶ����������ĳ�ʼ��");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// ����POST�ύ������ı���
		request.setCharacterEncoding("UTF-8");
		/*// ������Ӧ��ı���
		response.setCharacterEncoding("UTF-8");
		// ����ҳ���ʱ��ʱ��ı����ʽ�� ������Ӧ��ı���
		response.setContentType("text/html;charset=UTF-8");*/
		
		//get�ύ��ʽ     tomcat8�Ժ����޸�

		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
