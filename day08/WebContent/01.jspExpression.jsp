<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jsp的语法</title>
</head>
<body>
	<%--jsp的表达式 --%>
	<%
		int a = 8;
		int b = 10;
	%>
	<%=a + b%>
	<hr />
	<%--jsp的脚本 --%>
	<%
		for (int i = 0; i < 5; i++) {
			out.write("南昌大学人民武装学院" + i);
	%>
	<br />
	<%
		}
	%>
	<%--jsp的声明 :成员变量与方法--%>
	<%!
	String str="15计本刘伟"; 
	private int getNum(int num){
		return num;
	}
	%>
	<%!
	static {
		String a=null;
		int b=100;
	}
	%>
	<%--jsp注释 --%>
	<!-- 这是html的注释-->
	<%--这是jsp的注释 --%>

</body>
</html>