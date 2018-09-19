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
 * ����id���Ҷ�Ӧ����ϵ��
 * @author hasee
 */
@WebServlet("/QueryContactServlet")
public class QueryContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// �õ����ݹ�����idֵ
		String id = request.getParameter("id");
		// ͨ��id���Ҷ�Ӧ����ϵ��
		ContactDao dao = new ContactDaoImpl();
		Contact contact = dao.findById(id);
		// ����ϵ��д���޸���ϵ�˽���
		String html = "";
		PrintWriter writer = response.getWriter();
		
		html += "<!doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='utf-8'>";
		html += "<title>�޸���ϵ��</title>";
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
		html += "	<h3>�޸���ϵ��</h3>";
		html += "	<form action='"+request.getContextPath()+"/UpdateContactServlet' method='post'>";
		//���һ�������򣬴���һ��ID
		html+="<input type='hidden' name='id' value='"+contact.getId()+"'/>";
		html += "		<table border='1'>";
		html += "			<tr>";
		html += "				<th>����</th>";
		html += "				<td><input type='text' name='name' value='"+contact.getName()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>�Ա�</th>";
		if (contact.getGender().equals("Ů")) {
			html += "			<td><input type='radio' name='gender' value='��'/>�� ";
			html += "				<input type='radio' name='gender' value='Ů' checked='checked'/>Ů</td>";
		} else {
			html += "				<td><input type='radio' name='gender' value='��' checked='checked'/>��";
			html += "					<input type='radio' name='gender' value='Ů'>Ů</td>";
		}
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>����</th>";
		html += "				<td><input type='text' name='age' value='"+contact.getAge()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>�绰</th>";
		html += "				<td><input type='text' name='phone' value='"+contact.getPhone()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>QQ</th>";
		html += "				<td><input type='text' name='qq' value='"+contact.getQq()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>����</th>";
		html += "				<td><input type='text' name='email' value='"+contact.getEmail()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<th>��ַ</th>";
		html += "				<td><input type='text' name='ad' value='"+contact.getAd()+"'/></td>";
		html += "			</tr>";
		html += "			<tr>";
		html += "				<td colspan='2' align='center'><input type='submit' value='����' />";
		html += "				<input	type='reset' value='����' /></td>";
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
