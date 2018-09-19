<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改联系人</title>
</head>
<style type="text/css">
table {
	border-collapse: collapse;
}

table td {
	text-align: center;
}
</style>
<body>
	<center><h3>修改列表</h3></center>
	<form action="${pageContext.request.contextPath }/UpdateContactServlet" method="post">
		<input type="hidden" name="id" value="${contact.id }"/>
		<table align="center" border="1" width="190px">
			<tr>
				<th>姓名</th>
				<td><input type="text" name="name" value="${contact.name }"/></td>
			</tr>
			<tr>
				<th>性别</th>
				<c:choose>
					<c:when test="${contact.gender=='男' }">
						<td><input type="radio" name="gender" value="男"
							checked="checked" />男 <input type="radio" name="gender"
							value="女" />女</td>
					</c:when>
					<c:when test="${contact.gender=='女' }">
						<td><input type="radio" name="gender" value="男" />男<input
							type="radio" name="gender" value="女" checked="checked">女</td>
					</c:when>
				</c:choose>
			</tr>
			<tr>
				<th>年龄</th>
				<td><input type="text" name="age" value="${contact.age }"/></td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input type="text" name="phone" value="${contact.phone }"/></td>
			</tr>
			<tr>
				<th>QQ</th>
				<td><input type="text" name="qq" value="${contact.qq }"/></td>
			</tr>
			<tr>
				<th>邮箱</th>
				<td><input type="text" name="email" value="${contact.email }"/></td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input type="text" name="ad" value="${contact.ad }"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="保存" /><input
					type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>

</body>
</html>