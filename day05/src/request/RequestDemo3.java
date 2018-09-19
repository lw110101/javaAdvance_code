package request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//案例：获取浏览器的类型
/**
 * Servlet implementation class RequestDemo3
 */
@WebServlet("/RequestDemo3")
public class RequestDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		//获取请求头： user-agent
		String agentValue=request.getHeader("user-agent");
		System.out.println("浏览器是:"+agentValue);
		//判断用户使用的浏览器类型
		if(agentValue.contains("Firefox")) {
			response.getWriter().write("你正在使用火狐浏览器！");
		}else if(agentValue.contains("Chrome")) {
			response.getWriter().write("你正在使用谷歌浏览器！");
		}else if(agentValue.contains("Trident")) {
			response.getWriter().write("你正在使用IE浏览器！");
		}else {
			response.getWriter().write("地球上没有这个浏览器");
		}
	}

}
