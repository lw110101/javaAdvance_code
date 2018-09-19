<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误提示</title>
</head>
<body>
	this is a error page!
	<br />
	<hr />
	<!-- 查看struts框架在运行时的错误信息 -->
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<s:fielderror></s:fielderror>
</body>
</html>