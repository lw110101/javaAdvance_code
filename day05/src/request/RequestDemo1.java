package request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemo1
 */
@WebServlet("/RequestDemo1")
public class RequestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		getReqLine(req);
		getReqHeader(req);
			//GET请求方式没有 实体内容
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//请求实体内容
		InputStream in=req.getInputStream();
		int length=0;
		byte[] buf=new byte[1024];
		while((length=in.read(buf))!=-1) {
			String content=new String(buf,0,length);
			System.out.println("实体内容是:"+content);
		}
	}
	
	protected void getReqHeader(HttpServletRequest req) {
		//请求头
		String hostValue=req.getHeader("Host");
		System.out.println(hostValue);
		
		Enumeration<String> acceptValues=req.getHeaders("Accept");
		while(acceptValues.hasMoreElements()) {
			String value=acceptValues.nextElement();
			System.out.println("值是："+value);
		}
		
		Enumeration<String> names=req.getHeaderNames();
		while(names.hasMoreElements()) {
			String name=names.nextElement();
			System.out.println(name+":"+req.getHeader(name));
		}
	}
	
	protected void getReqLine(HttpServletRequest req) {
		//请求行
		System.out.println("协议版本："+req.getProtocol());
		System.out.println("请求方式："+req.getMethod());
		System.out.println("url:"+req.getRequestURL());
		System.out.println("URI:"+req.getRequestURI());
	}
}
