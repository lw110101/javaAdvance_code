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
		//获取编号
		String id=request.getParameter("id");
		//到集合查询相对应的数据
		ProductDao dao=new ProductDao();
		Product product=dao.findById(id);
		//显示对应的数据到浏览器
		PrintWriter write=response.getWriter();
		String html="";
		
		html+="<html>";
		html+="<head>";
		html+="<title>商品详情</title>";
		html+="</head>";
		html+="<body>";
		html += "<table border='1' align='center' width='600px'>";
		if (product != null) {
			html += "<tr><th>编号:</th><td>" + product.getId() + "</td></tr>";
			html += "<tr><th>商品名称:</th><td>" + product.getProdName() + "</td></tr>";
			html += "<tr><th>商品型号:</th><td>" + product.getProdType() + "</td></tr>";
			html += "<tr><th>商品价格:</th><td>" + product.getPrice() + "</td></tr>";
		}
		html += "</table>";
		html+="<center><a href='"+request.getContextPath()+"/ListServlet'>[返回列表]</a></center>";
		html += "</body>";
		html += "</html>";
		write.write(html);
		
		/**
		 * 创建cookie并发送
	 */
		Cookie cookie=new Cookie("productHistory",createValue(request,id));
		cookie.setMaxAge(30*24*60*60);
		response.addCookie(cookie);
		
	}
/**生成cookie的值           当前的cookie值                                 传入的id     最终值                 算法
 *           cookie为null或prohist为null    1          1      直接放进去
 *                   1                    2          2,1    传入id放在首位
 *                   2,1                  1          1,2    删除重复，并放在首位
 *                   2,1                  3          3,2,1  放在首位
 *                   3,2,1                1          1,3,2  删除重复，放在首位
 *                   3,2,1                4          4,3,2  移除最后一个，传人的放在首位
 *        个数小于三：如果有重复，先删除重复，然后直接放在首位
 *        个数等于三：如果有重复，先删除重复，否则移除最后一位，再放在首位
  */
	private String createValue(HttpServletRequest request, String id) {
		Cookie[] cookies=request.getCookies();
		String productHistory=null;
		//cookie为null或prohist为null
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
		 * 个数小于三：如果有重复，先删除重复，然后直接放在首位
		 * 个数等于三：如果有重复，先删除重复，否则移除最后一位，再放在首位		
		 */
		//将cookie值转换成linkedList方便增删改
		String[] ids=productHistory.split("-");
		List<String> list=Arrays.asList(ids);
		LinkedList<String> link=new LinkedList<String>(list);
		
		if(link.size()<3) {
			if(link.contains(id)) {
				//去除重复id
				link.remove(id);	
			}else {}	
			//放在首位
			link.addFirst(id);
		}else {
			if(link.contains(id)) {
				////去除重复id
				link.remove(id);
				
			}else {
				//移除最后一位
				link.removeLast();
			}
			//添加到首位
			link.addFirst(id);
		}
		//将link转回字符串返回
		StringBuffer sb = new StringBuffer();
		for (String str : link) {
			sb.append(str+"-");
		}
		//去掉最后的逗号
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
