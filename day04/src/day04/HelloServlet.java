package day04;
/*
1����дһ��servlet���򣬼̳�HttpServlet

2���ҵ�HelloServlet���class�ֽ��룬Ȼ��ѿ�����tomcat��һ��webӦ����WEB-INF/classesĿ¼�¡�

3���ڵ�ǰwebӦ���µ�web.xml�ļ�����Servlet��

4������tomcat�����������з���
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
		//�����������
		resp.setCharacterEncoding("utf-8");
		//�������
		resp.getWriter().write("���ǵ�һ��servlet���򡣵�ǰʱ��Ϊ:"+new Date());
	}
}
