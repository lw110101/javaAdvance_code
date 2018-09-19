<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="java.util.*,entity.*"
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>page指令</title>
</head>
<body>
	<%
		Scanner sc = new Scanner(System.in);
		Student s = new Student();
		sc.close();
	%>

	<%--异常错误相关的 --%>
	<%=100/0%>
	
	<%-- <%@ page
	errorPage="/common/error_500.jsp"
	%> --%>
	<br/>
	<%--缓存 --%>
	<%@ page buffer="0kb" %>
	<%
	String str="abc";
	out.write(str);
	response.getWriter().write("123");
	%>
	<hr/>
	<%@ page session="true" %>
	<% session.setAttribute("name", "张三"); %>
	<% String name=(String)session.getAttribute("name"); %>
	姓名:<%=name %>
</body>
</html>