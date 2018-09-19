<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500错误处理页面</title>
<body>
  500错误!!亲爱的用户，不好意思，服务器暂时出现故障，给你带来不便请谅解！我们正马不停蹄的加紧抢修中.。。。。<br/>
	错误原因:<%=exception.getMessage() %>
</body>
</html>