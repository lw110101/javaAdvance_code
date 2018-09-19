package request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//��������ȡ�����������
/**
 * Servlet implementation class RequestDemo3
 */
@WebServlet("/RequestDemo3")
public class RequestDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		//��ȡ����ͷ�� user-agent
		String agentValue=request.getHeader("user-agent");
		System.out.println("�������:"+agentValue);
		//�ж��û�ʹ�õ����������
		if(agentValue.contains("Firefox")) {
			response.getWriter().write("������ʹ�û���������");
		}else if(agentValue.contains("Chrome")) {
			response.getWriter().write("������ʹ�ùȸ��������");
		}else if(agentValue.contains("Trident")) {
			response.getWriter().write("������ʹ��IE�������");
		}else {
			response.getWriter().write("������û����������");
		}
	}

}
