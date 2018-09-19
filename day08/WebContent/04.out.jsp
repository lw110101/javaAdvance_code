<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
   		pageEncoding="UTF-8"
   		buffer="1kb"
   		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>out内置对象</title>
</head>
<body>
	<%
		for (int i = 0; i <= 1024; i++) {
			out.write("a");
		}
		System.out.println("当前的缓存容量是:" + out.getBufferSize());
		System.out.println("剩余缓存容量:" + out.getRemaining());
	%>

</body>
</html>