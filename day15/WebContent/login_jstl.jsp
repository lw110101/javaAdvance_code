<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>

	<!--设置本地化环境 -->
	<fmt:setLocale value="${pageContext.request.locale }"/>
	<%--设置工具类 --%>
	<fmt:setBundle basename="myproperties" var="bundle"/>
	
	<form
		action="${pageContext.request.contextPath }/MyRequestListenerServlet">
		<table align="center" border="1">
			<tr>
				<td><fmt:message key="userName" bundle="${bundle }"></fmt:message></td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td><fmt:message key="password" bundle="${bundle }"></fmt:message></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="<fmt:message key="submit" bundle="${bundle }"></fmt:message>"></td>
			</tr>
		</table>
	</form>

</body>
</html>