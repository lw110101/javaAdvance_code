<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3 align="center">
 	欢迎你，${sessionScope.info.userName }&nbsp;&nbsp;&nbsp;
 	<a href="${pageContext.request.contextPath }/LoginServlet?method=out">退出</a>
 		
 	<a href="${pageContext.request.contextPath }/online.jsp">在线列表展示</a>
</h3>