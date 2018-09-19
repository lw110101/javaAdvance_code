package servletConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigDemo1
 */
@WebServlet(urlPatterns="/ConfigDemo1",initParams= {@WebInitParam(name="userName",value="张三")
,@WebInitParam(name="password",value="123456"),@WebInitParam(name="path",value="C:\\Users\\hasee\\Desktop\\contact.txt")})
public class ConfigDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html;charset=utf-8");
	//读取配置文件
		
		ServletConfig config=this.getServletConfig();
		String path=config.getInitParameter("path");
		
		File file=new File(path);
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String content=null;
		while((content=br.readLine())!=null) {
			System.out.println(content);
		}
		br.close();
		
		Enumeration<String> enums=config.getInitParameterNames();
		while(enums.hasMoreElements()) {
			String param=enums.nextElement();
			String value=config.getInitParameter(param);
			System.out.println(param+"="+value);
		}
		
		ServletContext context=config.getServletContext();
		System.out.println(context);
	
		String servletName=config.getServletName();
		System.out.println(servletName);
	}
}
