package servletLife;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class InitDemo
 */
@WebServlet(urlPatterns= "/InitDemo")
public class InitDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*@Override//�вε�init��������Ҫ���ǣ��������ڷ�����һ����tomcat����
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("�вε�init������");
	}*/
	@Override//�޲ε�init��������ʼ������д������
	public void init() throws ServletException {
		System.out.println("�޲ε�init������");
	}
	
}
