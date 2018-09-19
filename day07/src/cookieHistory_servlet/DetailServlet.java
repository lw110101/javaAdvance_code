package cookieHistory_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cookieHistory_dao.ProductDao;
import cookieHistory_entity.Product;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//��ȡ���
		String id=request.getParameter("id");
		//�����ϲ�ѯ���Ӧ������
		ProductDao dao=new ProductDao();
		Product product=dao.findById(id);
		//��ʾ��Ӧ�����ݵ������
		PrintWriter write=response.getWriter();
		String html="";
		
		html+="<html>";
		html+="<head>";
		html+="<title>��Ʒ����</title>";
		html+="</head>";
		html+="<body>";
		html += "<table border='1' align='center' width='600px'>";
		if (product != null) {
			html += "<tr><th>���:</th><td>" + product.getId() + "</td></tr>";
			html += "<tr><th>��Ʒ����:</th><td>" + product.getProdName() + "</td></tr>";
			html += "<tr><th>��Ʒ�ͺ�:</th><td>" + product.getProdType() + "</td></tr>";
			html += "<tr><th>��Ʒ�۸�:</th><td>" + product.getPrice() + "</td></tr>";
		}
		html += "</table>";
		html+="<center><a href='"+request.getContextPath()+"/ListServlet'>[�����б�]</a></center>";
		html += "</body>";
		html += "</html>";
		write.write(html);
		
		/**
		 * ����cookie������
	 */
		Cookie cookie=new Cookie("productHistory",createValue(request,id));
		cookie.setMaxAge(30*24*60*60);
		response.addCookie(cookie);
		
	}
/**����cookie��ֵ           ��ǰ��cookieֵ                                 �����id     ����ֵ                 �㷨
 *           cookieΪnull��prohistΪnull    1          1      ֱ�ӷŽ�ȥ
 *                   1                    2          2,1    ����id������λ
 *                   2,1                  1          1,2    ɾ���ظ�����������λ
 *                   2,1                  3          3,2,1  ������λ
 *                   3,2,1                1          1,3,2  ɾ���ظ���������λ
 *                   3,2,1                4          4,3,2  �Ƴ����һ�������˵ķ�����λ
 *        ����С������������ظ�����ɾ���ظ���Ȼ��ֱ�ӷ�����λ
 *        ������������������ظ�����ɾ���ظ��������Ƴ����һλ���ٷ�����λ
  */
	private String createValue(HttpServletRequest request, String id) {
		Cookie[] cookies=request.getCookies();
		String productHistory=null;
		//cookieΪnull��prohistΪnull
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("productHistory")) {
					productHistory=cookie.getValue();
					break;
				}			
			}
		}
		
		if(cookies==null||productHistory==null) {
			return id;
		}		
		/**
		 * ����С������������ظ�����ɾ���ظ���Ȼ��ֱ�ӷ�����λ
		 * ������������������ظ�����ɾ���ظ��������Ƴ����һλ���ٷ�����λ		
		 */
		//��cookieֵת����linkedList������ɾ��
		String[] ids=productHistory.split("-");
		List<String> list=Arrays.asList(ids);
		LinkedList<String> link=new LinkedList<String>(list);
		
		if(link.size()<3) {
			if(link.contains(id)) {
				//ȥ���ظ�id
				link.remove(id);	
			}else {}	
			//������λ
			link.addFirst(id);
		}else {
			if(link.contains(id)) {
				////ȥ���ظ�id
				link.remove(id);
				
			}else {
				//�Ƴ����һλ
				link.removeLast();
			}
			//��ӵ���λ
			link.addFirst(id);
		}
		//��linkת���ַ�������
		StringBuffer sb = new StringBuffer();
		for (String str : link) {
			sb.append(str+"-");
		}
		//ȥ�����Ķ���
		String result=sb.toString();
		
		result=result.substring(0, result.length()-1);
		
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
