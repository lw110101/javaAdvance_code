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
		// 使用动态代理实现编码解决
		HttpServletRequest req = (HttpServletRequest) request;
		
		req.setCharacterEncoding("utf-8");
		System.out.println(1);
		HttpServletRequest proxy = (HttpServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(),
				req.getClass().getInterfaces(), new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = null;
						// 获取方法名
						String methodName = method.getName();
						// 判断
						System.out.println("a:"+methodName);
						if ("getParameter".equals(methodName)) {
							// 获取请求数据值【 <input type="text" name="userName">】
							String value = req.getParameter(args[0].toString()); // 调用目标对象的方法
								
							// 获取提交方式
							 
							String methodSubmit = req.getMethod(); // 直接调用目标对象的方法
							System.out.println("提交方式："+methodSubmit);
							// 判断如果是GET提交，需要对数据进行处理 (POST提交已经处理过了)
							if ("GET".equals(methodSubmit)) {
								if (value != null && !"".equals(value.trim())) {
									// 处理GET中文
									value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
									
								}
							}
							return value;
						} else {
							// 执行request对象的其他方法
							obj = method.invoke(req, args);
						}
						return obj;
					}
				});

		chain.doFilter(proxy, response);
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String filterName = fConfig.getFilterName();
		System.out.println("过滤器名字：" + filterName);
		String pass = fConfig.getInitParameter("pass");
		System.out.println("初始参数：" + pass);
	}

}
