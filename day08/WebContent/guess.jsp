<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--1、【使用JSP技术实现百万富翁猜数字游戏】
		设计一个web app，每次产生一个30以内的数字，给5次机会让客户猜测这个数字：
		1）如果客户猜的数字比产生的数字值大，则提示“可惜，大了点”。
		2）如果客户猜的数字比产生的数字值小，则提示“可惜，小了点”
		猜对了奖励100百万，猜错Game Over，给玩家重玩的机会。

		guess.jsp(显示数据)  GuessServlet（java逻辑代码）
 --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百万富翁猜数字游戏</title>
</head>
<body>
	<%
		String mesg = (String) request.getAttribute("mesg");
		Integer times=(Integer)request.getAttribute("times");
		if(mesg!=null){
			out.write("<font color='red'>"+mesg+"</font>");
		}
		if(times!=null){
			out.write("你还有"+(5-times)+"次机会竞猜!");
		}
	%>
	<form action="/day08/GuessServlet" method="post">
	请输入个30以下的数字:<input type="text" name="number"/>
		<%
			if (times != null) {
		%>
		<input type="hidden" name="times" value="<%=times%>" />
		<%
			}
		%>
		<input type="submit" value="开始竞猜">
	</form>
</body>
</html>