<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载列表</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<td>编号</td>
			<td>文件名</td>
			<td>操作</td>
		</tr>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<c:forEach var="fileName" items="${requestScope.fileNames }" varStatus="vs" >
			<tr>
				<td>${vs.count}</td>
				<td>${fileName}</td>
				<td>
					<!-- 构建一个url   -->
					 <c:url var="url" value="down_down">
						<c:param name="fileName" value="${fileName}"></c:param>
					</c:url> 
					<a href="${url}">下载</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>