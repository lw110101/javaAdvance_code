package cookieHistory_servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//获取集合的数据
		ProductDao dao=new ProductDao();
		List<Product>list=dao.findAll();
		//写到浏览器
		String html="";
		PrintWriter write=response.getWriter();
		html+="<html>";
		html+="<head>";
		html+="<title>显示商品列表</title>";
		html+="</head>";
		html+="<body>";
		html+="<table border='1' align='center' width='600px'>";
		html+="<tr>";
		html+="<th>编号</th><th>商品名称</th><th>商品型号</th><th>价格</th>";
		html += "</tr>";
		/**
		 * 显示全部商品列表
		 */
		if (list != null) {
			for (Product product : list) {
				html += "<tr>";
				html += "<td>" + product.getId() + "</td><td><a href='"+request.getContextPath()+"/DetailServlet?id="+product.getId()+"'>" + product.getProdName() + "</a></td>";
				html += "<td>" +product.getProdType()+ "</td><td>" + product.getPrice() + "</td>";
				html += "</tr>";
			}
		}
			html += "</table>";
			/**
			 * 根据id显示浏览过的商品
			 */
			html+="最近浏览过的商品:<br/>";
			//获取cookie信息
			Cookie[] cookies=request.getCookies();
			if(cookies!=null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("productHistory")) {
						String productHistory=cookie.getValue();//有可能是1，2，3故切割
						String[] ids=productHistory.split("-");
						for (String id : ids) {
							Product product=dao.findById(id);
							html+=""+product.getId()+"&nbsp;"+product.getProdName()+"&nbsp;"+product.getProdType()+"&nbsp;"+product.getPrice()+"<br/>";
						}
					}
				}
			}
			html += "</body>";
			html += "</html>";
			write.write(html);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
