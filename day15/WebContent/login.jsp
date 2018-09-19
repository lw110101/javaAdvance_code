
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
	<%
		ResourceBundle rb = ResourceBundle.getBundle("myproperties", request.getLocale());
	%>

	<form
		action="${pageContext.request.contextPath }/MyRequestListenerServlet">
		<table align="center" border="1">
			<tr>
				<td><%=rb.getString("userName") %></td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td><%=rb.getString("password") %></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value=<%=rb.getString("submit") %>></td>
			</tr>
		</table>
	</form>

</body>
</html>