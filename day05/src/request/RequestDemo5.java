package request;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//���ݲ����Ļ�ȡ
/**
 * Servlet implementation class RequestDemo5
 */
@WebServlet("/RequestDemo5")
public class RequestDemo5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		System.out.println(req.getMethod()+"��ʽ:");
		//�������ݵĲ�����ʽһ��
		Map<String, String[]> map=req.getParameterMap();
		Set<Entry<String,String[]>> set=map.entrySet();
		Iterator<Entry<String,String[]>> it=set.iterator();
		while(it.hasNext()) {
			Entry<String,String[]> entry=it.next();
			String key=entry.getKey();
			System.out.print(key+":");
			String[] values=entry.getValue();
			for (String value : values) {
				System.out.print(value+" ");
			}	
			System.out.println();
		}
		//��ʽ����
		Enumeration<String> enums=req.getParameterNames();
		while (enums.hasMoreElements()) {
			String name = enums.nextElement();
			if (name.equals("����")) {
				String[] nameValues = req.getParameterValues(name);
				System.out.println("��Ȥ����:");
				for (String value : nameValues) {
					System.out.print(value + "  ");
				}
				System.out.println();
			} else {
				String nameValue = req.getParameter(name);
				System.out.println(name + ":" + nameValue);
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
