package contactSys_web_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contactSys_web_dao.ContactDao;
import contactSys_web_dao_Impl.ContactDaoImpl;
import contactSys_web_entity.Contact;
/**
 * 根据id查找对应的联系人
 * @author hasee
 */
@WebServlet("/QueryContactServlet")
public class QueryContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// 拿到传递过来的id值
		String id = request.getParameter("id");
		// 通过id查找对应的联系人
		ContactDao dao = new ContactDaoImpl();
		Contact contact = dao.findById(id);
		// 将联系人写到修改联系人界面
		String html = "";
		PrintWriter writer = response.getWriter();
		
		html += "<!doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='utf-8'>";
		html += "<title>修改联系人</title>";
		html += "</head>";
		html += "<link href='" + request.getContextPath() + "/contact.css' rel='stylesheet' />";
		html += "<style type='text/css'>";
		html += "table {";
		html += "	width: 270px;";
		html += "	position: absolute;";
		html += "	left: 650px;";
		html += "}";
		html += "</style>";
		html += "<body>";
		html += "	<h3>修改联系人</h3>";
		html += "	<form action='"+request.getContextPath()+"/UpdateContactServlet' method='post'>";
		//添加一个隐藏域，传入一个ID
		html+="<input type='hidden' name='id' value='"+contact.getId()+"'/>";
		html += "		<table border='1'>";
		html += "			<tr>";
		html += "				<th>姓名</th>";
		html += "				<td><input type='text' name='name' value='"+contact.getName()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>性别</th>";
		if (contact.getGender().equals("女")) {
			html += "			<td><input type='radio' name='gender' value='男'/>男 ";
			html += "				<input type='radio' name='gender' value='女' checked='checked'/>女</td>";
		} else {
			html += "				<td><input type='radio' name='gender' value='男' checked='checked'/>男";
			html += "					<input type='radio' name='gender' value='女'>女</td>";
		}
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>年龄</th>";
		html += "				<td><input type='text' name='age' value='"+contact.getAge()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>电话</th>";
		html += "				<td><input type='text' name='phone' value='"+contact.getPhone()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>QQ</th>";
		html += "				<td><input type='text' name='qq' value='"+contact.getQq()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>邮箱</th>";
		html += "				<td><input type='text' name='email' value='"+contact.getEmail()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>地址</th>";
		html += "				<td><input type='text' name='ad' value='"+contact.getAd()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<td colspan='2' align='center'><input type='submit' value='保存' />";
		html += "				<input	type='reset' value='重置' /></td>";
		html += "			</tr>";
		html += "		</table>";
		html += "	</form>";
		html += "</body>";
		html += "</html>";

		writer.write(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
