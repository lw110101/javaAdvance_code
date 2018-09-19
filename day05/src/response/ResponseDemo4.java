package response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**����- content-Type����
 * Servlet implementation class ResponseDemo4
 */
@WebServlet("/ResponseDemo4")
public class ResponseDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml");
		response.getWriter().write("<html><head></head><body>�ϲ���ѧ������װѧԺ</body></html>");
	*/
	/*	
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("15�Ʊ���ΰ");
		*/
		//���������ط�ʽ���ļ�
		File file=new File("E:\\apache-tomcat-9.0.6\\webapps\\FirstWeb\\imgs\\Jellyfish.jpg");
		response.setHeader("Content-Disposition", "attachment;fileName="+file.getName());
		FileInputStream in = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = 0;
		
		//��ͼƬ����д���������
		while( (len=in.read(buf))!=-1 ){
			response.getOutputStream().write(buf, 0, len);
		}
		in.close();
	}

}
