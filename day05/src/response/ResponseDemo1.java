package response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//������Ӧ��Ϣ
/**
 * Servlet implementation class ResponseDemo1
 */
@WebServlet("/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		//������Ӧ��
		//resp.setStatus(404);
		//resp.sendError(404);
		
		//������Ӧͷ
		//resp.setHeader("server", "JBoss");
		//����ʵ������
		//resp.getWriter().write("�����������ַ�����д����");
		resp.getOutputStream().write("�����ֽڵ�д����".getBytes("utf-8"));
	}
}
