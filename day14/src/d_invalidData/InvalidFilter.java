package d_invalidData;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class InvalidFilter
 */
@WebFilter(filterName = "/InvalidFilter", value = "/InvalidDataServlet")
public class InvalidFilter implements Filter {
	private List<String> invalidData;

	public void init(FilterConfig fConfig) throws ServletException {
		invalidData = new ArrayList<String>();
		invalidData.add("������");
		invalidData.add("NND");
		invalidData.add("���");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
	
		ServletRequest proxy = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(),
				request.getClass().getInterfaces(), new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// ��ȡ������
						String methodName = method.getName();
						// �ж�
						if ("getParameter".equals(methodName)) {
							// ��ȡ��������
							String value = request.getParameter(args[0].toString());

							for (String data : invalidData) {
								if (value.contains(data)) {
									value = value.replace(data, "��������������");
								}
							}
							return value;
						} else {
							return method.invoke(request, args);
						}	
						
					}
				});
		chain.doFilter(proxy, response);
	}

	public void destroy() {

	}

}
