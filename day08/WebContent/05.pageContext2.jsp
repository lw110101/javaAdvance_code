<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指定域获取数据</title>
</head>
<body>
<!-- 指定域获取数据 -->
	默认域：<%=pageContext.getAttribute("name")%><br/>
	page域：<%=pageContext.getAttribute("name", pageContext.PAGE_SCOPE) %><br/>
	request域：<%=pageContext.getAttribute("name", pageContext.REQUEST_SCOPE) %><br/>
	session域：<%=pageContext.getAttribute("name", pageContext.SESSION_SCOPE) %><br/>
	application域：<%=pageContext.getAttribute("name", pageContext.APPLICATION_SCOPE) %><br/>
	<%--自动依次搜索  顺序： page域 -> request域 -> session域- > context域（application域） --%>
	自动搜索域:<%=pageContext.findAttribute("name") %>
</body>
</html>