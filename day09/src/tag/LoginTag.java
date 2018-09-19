package tag;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoginTag extends SimpleTagSupport {

	private String userName;
	private String password;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void doTag() throws JspException, IOException {
	
		HttpServletResponse response=(HttpServletResponse)((PageContext)this.getJspContext()).getResponse();
		response.setContentType("text/html;charset=utf-8");
		String html="";
		html+="<center><h3>�û���¼����</h3></center>";
		html+="	<table border='1' align='center' width='300px' >";
		html+="		<tr>";
		html+="			<th>�û���:</th>";
		html+="			<td><input type='text' name='"+userName+"'/></td>";
		html+="		</tr>";
		html+="		<tr>";
		html+="			<th>����</th>";
		html+="			<td><input type='password' name='"+password+"'/></td>";
		html+="		</tr>";
		html+="		<tr>";
		html+="			<td colspan='2' align='center'>";
		html+="				<input type='submit' value='��¼'/>";
		html+="				<input type='reset' value='����'/>";
		html+="			</td>";
		html+="		</tr>";
		html+="	</table>";
		response.getWriter().write(html);
	}
}
