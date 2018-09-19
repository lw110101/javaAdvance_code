<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单提交</title>
</head>
<body>
	<form action="request.jsp" method="post">
		姓名: <input type="text" name="name"> <br> 
		
		密码: <input type="password" name="pass"><br>
		
		 <input type="submit" value="post提交">
	</form>
	<hr>
	<form action="request.jsp" method="get">
		姓名: <input type="text" name="name"> <br> 
		
		密码: <input type="password" name="pass"><br>
		
		 <input type="submit" value="get提交">
	</form>

</body>
</html>