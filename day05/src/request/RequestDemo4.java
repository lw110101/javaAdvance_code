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
		//获取referer
		String refererValue=req.getHeader("Referer");
		resp.getWriter().write("referer是:"+refererValue);
		resp.getWriter().write("<br/>");
		/**
		 * 判断非法链接：
		 * 	1）直接访问的话referer=null
		 *  2）如果当前请求不是来自目标URL   
		 */
		if(refererValue==null||!refererValue.contains("/day05/adv.html")) {
			resp.getWriter().write("这是一个非法链接，请回到首页！<a href='/day05/adv.html'>首页<a>");
		}else {
			resp.getWriter().write("开始下载。。");
		}
	}
}
