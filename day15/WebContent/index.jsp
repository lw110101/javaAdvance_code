<%@page import="c_sessionRelated.Test"%>
<%@page import="c_sessionRelated.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		 //request监听器
		request.setAttribute("cn", "CHINA");
		//session的生命监听
		//session.invalidate();
		
		//属性变化监听
		request.setAttribute("cn", "中国");
		request.removeAttribute("cn");
		
		session.setAttribute("name", "张三");	
		session.setAttribute("name", "李四");
		session.removeAttribute("name");
		
		getServletContext().setAttribute("password", "123");
		getServletContext().setAttribute("password", "456");
		getServletContext().removeAttribute("password");
		
		
		session.setAttribute("admin", new Admin("lw","111"));
		session.removeAttribute("admin");
		
		session.setAttribute("test", new Test());
		session.removeAttribute("test"); 
		
	%>
</body>
</html>