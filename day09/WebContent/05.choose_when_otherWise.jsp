<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://gz.itcast.cn" prefix="itcast" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>判断：多条件判断</title>
</head>
<body>
	<itcast:choose>
		<itcast:when test="${10>5 }">
			条件成立
		</itcast:when>
		<itcast:otherWise>
			条件不成立
		</itcast:otherWise>
	</itcast:choose>

</body>
</html>