<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加联系人</title>
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
	<center>
		<h3>添加联系人</h3>
	</center>
	<form action="${pageContext.request.contextPath }/AddContactServlet" method="post">
		<table align="center" border="1" width="380px">
			<tr>
				<th>姓名</th>
				<td><input type="text" name="name"/><font color="red">${message }</font></td>
			</tr>
			<tr>
				<th>性别</th>
				<td><input type="radio" name="gender" value="男"
					checked="checked" />男 <input type="radio" name="gender" value="女" />女
				</td>
			</tr>
			<tr>
				<th>年龄</th>
				<td><input type="text" name="age" /></td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<th>QQ</th>
				<td><input type="text" name="qq" /></td>
			</tr>
			<tr>
				<th>邮箱</th>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input type="text" name="ad" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="保存" />
					<input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>

</body>
</html>