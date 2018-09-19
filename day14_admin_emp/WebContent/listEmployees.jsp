<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示员工信息</title>
</head>
<body>
	<jsp:include page="/public/head.jsp"></jsp:include>
	<!-- 列表展示数据 -->
	<table align="center" border="1" width="80%" cellpadding="3"
		cellspacing="0">
		<tr>
			<th>序号</th>
			<th>员工编号</th>
			<th>员工姓名</th>
		</tr>

		<c:if test="${not empty requestScope.list }">
			<c:forEach var="emp" items="${requestScope.list }" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${emp.empId }</td>
					<td>${emp.empName }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>

</body>
</html>