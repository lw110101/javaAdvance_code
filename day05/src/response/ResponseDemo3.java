package response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//��ʱˢ��
/**
 * Servlet implementation class ResponseDemo3
 */
@WebServlet("/ResponseDemo3")
public class ResponseDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
		 * ��ʱˢ��
		 * ԭ���������ʶrefreshͷ���õ�refreshͷ֮����������ǰ��Դ
		
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("refresh","1");  //ÿ��1��ˢ�´�ҳ��
		
		//��n��֮����ת�������Դ
		response.setHeader("refresh", "3;url=/day05/adv.html");  //��3��֮����ת��adv.html
	}

}
