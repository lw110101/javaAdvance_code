<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,contactSys_mysql_entity.*"
	%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看所有联系人</title>
</head>
<style type="text/css">
table {
	border-collapse:collapse;
}

table td {
	text-align: center;
}
</style>
<body>
	<center><h3>所有联系人列表</h3></center>
	<form>
		<table align="center" border="1" width="1000px">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>电话</th>
				<th>QQ</th>
				<th>邮箱</th>
				<th>地址</th>
				<th colspan="3">操作</th>
			</tr>
			<c:if test="${!empty list }">
			<c:forEach items="${list }" var="contact">
			<tr>
				<td>${contact.id }</td>
				<td>${contact.name }</td>
				<td>${contact.gender }</td>
				<td>${contact.age }</td>
				<td>${contact.phone }</td>
				<td>${contact.qq}</td>
				<td>${contact.email }</td>
				<td>${contact.ad }</td>
				<td><a href="${pageContext.request.contextPath }/addContact.jsp">添加</a></td>
				<td><a href="${pageContext.request.contextPath }/QueryContactServlet?id=${contact.id }">修改</a></td>
				<td><a href="${pageContext.request.contextPath }/DeleteContactServlet?id=${contact.id }">删除</a></td>
			</tr>
			</c:forEach>
			</c:if>

		</table>
	</form>

</body>
</html>