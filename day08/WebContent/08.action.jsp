<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动作标签</title>
</head>
<body>
<%--jsp:forword 转发--%>
	<jsp:forward page="09.action.jsp">
		<jsp:param value="rose" name="name" />
		<jsp:param value="123456" name="password" />
	</jsp:forward>
	
	<hr/>
	<%--jsp：include 包含  动态引入可以传参 --%>
	<jsp:include page="/common/header.jsp">
		<jsp:param value="male" name="gender" />
	</jsp:include>
	主页的内容
</body>
</html>