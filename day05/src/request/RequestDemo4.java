package request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemo4
 */
@WebServlet("/RequestDemo4")
public class RequestDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		//��ȡreferer
		String refererValue=req.getHeader("Referer");
		resp.getWriter().write("referer��:"+refererValue);
		resp.getWriter().write("<br/>");
		/**
		 * �жϷǷ����ӣ�
		 * 	1��ֱ�ӷ��ʵĻ�referer=null
		 *  2�������ǰ����������Ŀ��URL   
		 */
		if(refererValue==null||!refererValue.contains("/day05/adv.html")) {
			resp.getWriter().write("����һ���Ƿ����ӣ���ص���ҳ��<a href='/day05/adv.html'>��ҳ<a>");
		}else {
			resp.getWriter().write("��ʼ���ء���");
		}
	}
}
