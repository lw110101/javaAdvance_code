package filter;

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
import javax.servlet.http.HttpSession;

/**
 * 登陆验证过滤器
 * 
 * http://localhost:8080/emp_sys/login.jsp 可以直接访问
 * http://localhost:8080/emp_sys/loginServlet 可以直接访问
 * http://localhost:8080/emp_sys/ListEmpLoyeesServlet 不能直接访问
 * http://localhost:8080/emp_sys/listEmployees.jsp 不能直接访问 Servlet Filter
 * implementation class LoginFilter
 */
@WebFilter(filterName = "/LoginFilter", urlPatterns = { "/ListEmpLoyeeServlet", "/listEmployees.jsp"})
public class LoginFilter implements Filter {
	private String uri;

	public LoginFilter() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	/**
	 * 分析：
	 * 
	 * 1. 先指定放行的资源，哪些资源不需要拦截： login.jsp + /login (request对象可以获取)
	 * 2. 获取session，从session中获取登陆用户 
	 * 3. 判断是否为空： 为空， 说明没有登陆， 跳转到登陆 不为空， 已经登陆，放行！
	 */

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// 0. 转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//编码
		request.setCharacterEncoding("UTF-8");
		/*// 获取请求资源
		String requestURI = request.getRequestURI();      //     /day14_admin_emp/login.jsp
		//截取获取资源
		String requestPath = requestURI.substring(requestURI.lastIndexOf("/")+1, requestURI.length());
		//2. 判断： 先放行一些资源：/login.jsp、/login
		if("login.jsp".equals(requestPath) || "LoginServlet".equals(requestPath)) {
			// 放行
				chain.doFilter(request, response);
		}else {*/
			//3.1 先获取Session、获取session中的登陆用户(loginInfo)
			HttpSession session = request.getSession(false);
			//如果对象存在，获取info对象
			if(session!=null) {
				Object logInfo = request.getAttribute("logInfo");
				if(logInfo==null) {
					//没有登录
					uri="/login.jsp";
				}else {
					//放行
					chain.doFilter(request, response);
				}
			}else {
				uri="/login.jsp";
			}
			//response.sendRedirect(request.getContextPath() + uri);
			request.getRequestDispatcher(uri).forward(request, response);
		}

	public void destroy() {
	}

}
