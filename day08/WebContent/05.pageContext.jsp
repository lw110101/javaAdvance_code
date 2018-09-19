<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pageContext对象</title>
</head>
<body>
<%--可以获取其他八个内置对象。 --%>
	<%
		pageContext.getOut();
		Object obj = pageContext.getPage();
		pageContext.getSession();
		pageContext.getServletConfig();
		pageContext.getServletContext();
		pageContext.getRequest();
		pageContext.getResponse();
		pageContext.getException();
	%>
	<%--作为域对象使用 --%>
	<%  //保存数据
		pageContext.setAttribute("name", "张三");
		pageContext.setAttribute("name", "李四", pageContext.REQUEST_SCOPE);
		pageContext.setAttribute("name", "赵六", pageContext.PAGE_SCOPE);
		pageContext.setAttribute("name", "王五", pageContext.SESSION_SCOPE);
		pageContext.setAttribute("name", "陈七", pageContext.APPLICATION_SCOPE);
	%>
	<%--转发与重定向 --%>
	<%
		//转发
		//request.getRequestDispatcher("/05.pageContext2.jsp").forward(request, response);
		//重定向
		response.sendRedirect(request.getContextPath()+"/05.pageContext2.jsp");
	%>

</body>
</html>