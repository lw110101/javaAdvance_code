<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://gz.itcast.cn" prefix="itcast" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
 2、开发一个含有[用户名]和[密码]的登录表格标签
   	 <form action="#" method="post"> 
  		<simple:login username="username(表单项名字)" 
						password="password(表单项名字)" />
  	 </form>
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<form action="" method="post">
	<itcast:loginTag password="pwd" userName="user"></itcast:loginTag>
	</form>

</body>
</html>