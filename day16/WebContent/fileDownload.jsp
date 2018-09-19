<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载页面</title>
</head>
<body>
	<table align="center">
		<tr>
			<th>序号</th>
			<th>文件名</th>
			<th>操作</th>
		</tr>

		<c:if test="${not empty requestScope.map }">
			<c:forEach var="map" items="${requestScope.map }" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${map.value }</td>
					<!-- 构建一个url-->
					<td><c:url var="url" value="FileUploadServlet">
							<c:param name="method" value="down"></c:param>
							<c:param name="fileName" value="${map.key }"></c:param>
						</c:url> 
						<!-- 使用上面的url地址 --> 
						<a href="${url }">下载</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>

	</table>

</body>
</html>