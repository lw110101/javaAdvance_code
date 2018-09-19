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
 * ��½��֤������
 * 
 * http://localhost:8080/emp_sys/login.jsp ����ֱ�ӷ���
 * http://localhost:8080/emp_sys/loginServlet ����ֱ�ӷ���
 * http://localhost:8080/emp_sys/ListEmpLoyeesServlet ����ֱ�ӷ���
 * http://localhost:8080/emp_sys/listEmployees.jsp ����ֱ�ӷ��� Servlet Filter
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
	 * ������
	 * 
	 * 1. ��ָ�����е���Դ����Щ��Դ����Ҫ���أ� login.jsp + /login (request������Ի�ȡ)
	 * 2. ��ȡsession����session�л�ȡ��½�û� 
	 * 3. �ж��Ƿ�Ϊ�գ� Ϊ�գ� ˵��û�е�½�� ��ת����½ ��Ϊ�գ� �Ѿ���½�����У�
	 */

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// 0. ת��
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//����
		request.setCharacterEncoding("UTF-8");
		/*// ��ȡ������Դ
		String requestURI = request.getRequestURI();      //     /day14_admin_emp/login.jsp
		//��ȡ��ȡ��Դ
		String requestPath = requestURI.substring(requestURI.lastIndexOf("/")+1, requestURI.length());
		//2. �жϣ� �ȷ���һЩ��Դ��/login.jsp��/login
		if("login.jsp".equals(requestPath) || "LoginServlet".equals(requestPath)) {
			// ����
				chain.doFilter(request, response);
		}else {*/
			//3.1 �Ȼ�ȡSession����ȡsession�еĵ�½�û�(loginInfo)
			HttpSession session = request.getSession(false);
			//���������ڣ���ȡinfo����
			if(session!=null) {
				Object logInfo = request.getAttribute("logInfo");
				if(logInfo==null) {
					//û�е�¼
					uri="/login.jsp";
				}else {
					//����
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
