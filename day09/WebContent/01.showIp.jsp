<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://gz.itcast.cn"  prefix="itcast"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的IP地址</title>
</head>
<body>

	当前的IP地址是:${pageContext.request.remoteHost}
	<hr/>
	<itcast:showIp></itcast:showIp>

</body>
</html>