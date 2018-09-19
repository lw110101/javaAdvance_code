package servletContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextDemo6
 */
@WebServlet("/ContextDemo6")
public class ContextDemo6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
//��ȡwebӦ�õ���Դ�ļ�
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context=this.getServletContext();
	//��ȡwebӦ�õľ���·��
		/*String path=context.getRealPath("/WEB-INF/classes/db.properties");
		System.out.println(path);
		
		FileInputStream in=new FileInputStream(new File(path));
		*/
		InputStream in=context.getResourceAsStream("/WEB-INF/classes/db.properties");
		//���������ļ�
		Properties prop=new Properties();
		prop.load(in);
		
		String name=prop.getProperty("name");
		String password=prop.getProperty("password");
		System.out.println("name="+name+"  password="+password);
		
		
	}
}
