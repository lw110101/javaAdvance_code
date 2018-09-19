<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
<form name="frmregister" action="${pageContext.request.contextPath }/data/register_register" method="post">
	姓名：<input name="user.userName" type="text" /><br/>
	密码：<input name="user.password" type="password" /><br/>
	年龄：<input name="user.age" type="text" /><br/>
	出生日期：<input name="user.birthday" type="date" /><br/>
	当前时间：<input name="user.time" type="text"><br/>
	<input type="submit" value="注册"><br/>
</form>

</body>
</html>