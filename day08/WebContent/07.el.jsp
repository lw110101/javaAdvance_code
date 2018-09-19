<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL执行计算</title>
</head>
<body>
	<%
		String str = "";
		String str1 = null;
		int a=20;
		int b=10;
	%>
	a是否大于b:${a}<br/>
	${str }<br/>     <%--取不到对象 --%>
	${empty str1 }<br/>
	<hr/>
	${str==str1 }<br/>
	${5*10}<br/>
	${10>8}<br/>
	
	<hr/>
	
	${true&&false }<br/>
	${true || false }<br/>
	${!false }<br/>
	<%--获取web的对象引用 --%> 
	${pageContext.request }
</body>
</html>