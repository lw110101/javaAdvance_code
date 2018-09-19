<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日期时间格式化</title>
</head>
<body>
	<%
		request.setAttribute("date", new Date());
		request.setAttribute("time","09-11-28 上午10时25分39秒 CST");
	%>
	<!-- 格式化日期 -->
	<fmt:formatDate value="${requestScope.date}" pattern="yyyy-MM-dd hh:mm:ss"/>
	<!-- 
  		格式化金额 
  			格式: 0.00   保留2为小数，会自动补0
  			     #.##  保留2为小数，不自动补0
  	-->
  	<hr/>
  	<fmt:formatNumber pattern="#.##" value="12"></fmt:formatNumber><br/>
  	<fmt:formatNumber pattern="0.00" value="14"></fmt:formatNumber>
  	<%-- 
  	<fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${ time}"></fmt:parseDate>

	<fmt:parseNumber pattern="#-#" value="123"></fmt:parseNumber> --%>
  	
</body>
</html>