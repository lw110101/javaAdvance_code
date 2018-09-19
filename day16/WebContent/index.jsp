<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
	<fieldset>
		<legend align="top">文件上传与下载</legend>
		<center>
			<a href="${pageContext.request.contextPath }/fileUpload.jsp">文件上传</a><br /> 
			<a href="${pageContext.request.contextPath }/FileUploadServlet?method=downList">文件下载</a>
		</center>
	</fieldset>
</body>
</html>