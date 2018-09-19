package contactSys_web_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contactSys_web_dao.ContactDao;
import contactSys_web_dao_Impl.ContactDaoImpl;
import contactSys_web_entity.Contact;

/**显示所有人的逻辑
 * Servlet implementation class ListContactServlet
 */
@WebServlet("/ListContactServlet")
public class ListContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//shift+alt+A   ^(.*)$  \1";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//从xml中取出数据
		ContactDao dao=new ContactDaoImpl();
		List<Contact> list=dao.checkContacts();
		
		//把数据写到浏览器中
		String html="";
		PrintWriter writer=response.getWriter();
		
		html+=" <!doctype html>";
		html+=" <html>";
		html+=" <head>";
		html+=" <meta charset='utf-8'>";
		html+=" <title>查看所有联系人</title>";
		html+=" </head>";
		html+=" <link href='"+request.getContextPath()+"/contact.css' rel='stylesheet'/>";
		html+=" <style type='text/css'>";
		html+=" 	table{";
		html+=" 		width:1000px;";
		html+=" 		position:absolute;";
		html+=" 		left:300px;";
		html+=" 		}";
		html+=" 	table td{";
		html+=" 		text-align:center;";
		html+=" 		}";
		html+=" </style>";
		html+=" <body>";
		html+=" 	<h3>所有联系人列表</h3>";
		html+=" <form>";
		html+=" 	<table border='1'>";
		html+=" 		<tr>";
		html+=" 			<th>编号</th><th>姓名</th><th>性别</th><th>年龄</th><th>电话</th>";
		html+=" 			<th>QQ</th><th>邮箱</th><th>地址</th><th colspan='3'>操作</th>";
		html+=" 		</tr>";
		if(list!=null) {
			for (Contact contact : list) {
		html+=" <tr>";
		html+=" <td>"+contact.getId()+"</td>";
		html+= "<td>"+contact.getName()+"</td>";
		html+="<td>"+contact.getGender()+"</td>";
		html+= "<td>"+contact.getAge()+"</td>";
		html+="<td>"+contact.getPhone()+"</td>";
		html+=" <td>"+contact.getQq()+"</td>";
		html+="<td>"+contact.getEmail()+"</td>";
		html+="<td>"+contact.getAd()+"</td>";
		html+=" <td><a href='"+request.getContextPath()+"/addContact.html'>添加</a></td>";
		html+=" <td><a href='"+request.getContextPath()+"/QueryContactServlet?id="+contact.getId()+"'>修改</a></td>";
		html+=" <td><a href='"+request.getContextPath()+"/DeleteContactServlet?id="+contact.getId()+"'>删除</a></td>";
		html+=" </tr>";
			}
		}
		html+=" 	</table>";
		html+=" </form>";
		html+=" </body>";
		html+=" </html>";
		
		writer.write(html);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
