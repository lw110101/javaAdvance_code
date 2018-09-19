package day04;
/*
1）编写一个servlet程序，继承HttpServlet

2）找到HelloServlet类的class字节码，然后把拷贝到tomcat的一个web应用中WEB-INF/classes目录下。

3）在当前web应用下的web.xml文件配置Servlet。

4）启动tomcat服务器，运行访问
*/
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解决中文乱码
		resp.setCharacterEncoding("utf-8");
		//输出数据
		resp.getWriter().write("这是第一个servlet程序。当前时间为:"+new Date());
	}
}
