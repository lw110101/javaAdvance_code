<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://gz.itcast.cn"  prefix="itcast"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签的作用</title>
</head>
<body>
	<itcast:tagUse num="5">AAAAA${10+5 }<br/></itcast:tagUse><br/>
		这是标签余下内容!
</body>
</html>