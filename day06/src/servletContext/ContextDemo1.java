package servletContext;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextDemo1
 */
@WebServlet(urlPatterns="/ContextDemo1",description="name=aaa")

public class ContextDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ServletContext context=this.getServletConfig().getServletContext();
		ServletContext context=this.getServletContext();
		//��ǰweb��·��
		String path=context.getContextPath();
		System.out.println("��ǰweb��Ӧ��·����"+path);
		//������Ӧ�õ������ض���
		resp.sendRedirect(path+"/index.html");
		
		
	}
}
