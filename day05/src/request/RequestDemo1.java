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
			//GET����ʽû�� ʵ������
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//����ʵ������
		InputStream in=req.getInputStream();
		int length=0;
		byte[] buf=new byte[1024];
		while((length=in.read(buf))!=-1) {
			String content=new String(buf,0,length);
			System.out.println("ʵ��������:"+content);
		}
	}
	
	protected void getReqHeader(HttpServletRequest req) {
		//����ͷ
		String hostValue=req.getHeader("Host");
		System.out.println(hostValue);
		
		Enumeration<String> acceptValues=req.getHeaders("Accept");
		while(acceptValues.hasMoreElements()) {
			String value=acceptValues.nextElement();
			System.out.println("ֵ�ǣ�"+value);
		}
		
		Enumeration<String> names=req.getHeaderNames();
		while(names.hasMoreElements()) {
			String name=names.nextElement();
			System.out.println(name+":"+req.getHeader(name));
		}
	}
	
	protected void getReqLine(HttpServletRequest req) {
		//������
		System.out.println("Э��汾��"+req.getProtocol());
		System.out.println("����ʽ��"+req.getMethod());
		System.out.println("url:"+req.getRequestURL());
		System.out.println("URI:"+req.getRequestURI());
	}
}
