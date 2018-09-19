package response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//设置响应信息
/**
 * Servlet implementation class ResponseDemo1
 */
@WebServlet("/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		//设置响应行
		//resp.setStatus(404);
		//resp.sendError(404);
		
		//设置响应头
		//resp.setHeader("server", "JBoss");
		//设置实体内容
		//resp.getWriter().write("哈哈，这是字符串的写出！");
		resp.getOutputStream().write("这是字节的写出！".getBytes("utf-8"));
	}
}
