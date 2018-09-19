<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入ckeditor组件(给用户输入提供方便) --> 
	<script src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/ckeditor/samples/sample.css">
	<!--  格式
		<script src="../ckeditor.js"></script>
		<link rel="stylesheet" href="sample.css">
	-->
<title>评论</title>
</head>
<body>
	评论：${ requestScope.content }
	<form action="${pageContext.request.contextPath }/InvalidDataServlet" method="post">
		发表评论：
		<textarea class="ckeditor" rows="5" cols="10" name="content"></textarea>
		<br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>