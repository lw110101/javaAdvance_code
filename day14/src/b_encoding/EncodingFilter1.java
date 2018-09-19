package b_encoding;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(filterName = "/EncodingFilter1", initParams = { @WebInitParam(name = "pass", value = "123456") },value="/Login")
public class EncodingFilter1 implements Filter {

	public EncodingFilter1() {

	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ʹ�ö�̬����ʵ�ֱ�����
		HttpServletRequest req = (HttpServletRequest) request;
		
		req.setCharacterEncoding("utf-8");
		System.out.println(1);
		HttpServletRequest proxy = (HttpServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(),
				req.getClass().getInterfaces(), new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = null;
						// ��ȡ������
						String methodName = method.getName();
						// �ж�
						System.out.println("a:"+methodName);
						if ("getParameter".equals(methodName)) {
							// ��ȡ��������ֵ�� <input type="text" name="userName">��
							String value = req.getParameter(args[0].toString()); // ����Ŀ�����ķ���
								
							// ��ȡ�ύ��ʽ
							 
							String methodSubmit = req.getMethod(); // ֱ�ӵ���Ŀ�����ķ���
							System.out.println("�ύ��ʽ��"+methodSubmit);
							// �ж������GET�ύ����Ҫ�����ݽ��д��� (POST�ύ�Ѿ��������)
							if ("GET".equals(methodSubmit)) {
								if (value != null && !"".equals(value.trim())) {
									// ����GET����
									value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
									
								}
							}
							return value;
						} else {
							// ִ��request�������������
							obj = method.invoke(req, args);
						}
						return obj;
					}
				});

		chain.doFilter(proxy, response);
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String filterName = fConfig.getFilterName();
		System.out.println("���������֣�" + filterName);
		String pass = fConfig.getInitParameter("pass");
		System.out.println("��ʼ������" + pass);
	}

}
