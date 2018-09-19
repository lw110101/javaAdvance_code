<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页查询数据</title>
</head>
<body>
	<table border="1" width="80%" align="center" cellpadding="5"
		cellspacing="0">
		<tr>
			<th>序号</th>
			<th>员工编号</th>
			<th>员工姓名</th>
		</tr>
		<!-- 迭代数据 -->
		<c:choose>
			<c:when test="${not empty requestScope.PageBean.pageData }">
				<c:forEach var="empl" items="${requestScope.PageBean.pageData }"
					varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${empl.empId }</td>
						<td>${empl.empName }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3">对不起，没有你要找的数据</td>
				</tr>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan="3" align="center">当前${requestScope.PageBean.current_page}/${requestScope.PageBean.totalPages }页
				<a
				href="${pageContext.request.contextPath }/IndexServlet?currentPage=1">首页</a>&nbsp;&nbsp;
				<a
				href="${pageContext.request.contextPath }/IndexServlet?currentPage=${requestScope.PageBean.current_page-1}">上一页
			</a> <a
				href="${pageContext.request.contextPath }/IndexServlet?currentPage=${requestScope.PageBean.current_page+1}">下一页
			</a> <a
				href="${pageContext.request.contextPath }/IndexServlet?currentPage=${requestScope.PageBean.totalPages }">末页</a>
			</td>
		</tr>
	</table>
</body>
</html>