<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"
    	import="entity.*,java.util.*"
 %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核心库标签  </title>
</head>
<%--替换jsp脚本 --%>
<body>
	<!--保存数据  默认保存到page域-->
	<c:set var="name" value="张三" scope="request"></c:set>
	${requestScope.name }<br/>
	<!-- 从域中获取数据 -->
	<%
	pageContext.setAttribute("password",null);
	%>
	<c:out value="${password }" escapeXml="false" default="<h1>标题</h1>"></c:out>
	<hr/>
	<!--单条件判断 -->
	<c:if test="${empty password }">
	${password=123456}
	</c:if>
	<hr/>
	<!-- 4）多条件判断 -->
	<c:set var="score" value="86"></c:set>
	<c:choose>
		<c:when test="${score>=90 && score<=100 }">
			优秀
		</c:when>
		<c:when test="${score>=80 && score<90 }">
			良好
		</c:when>
		<c:when test="${score>=70 && score<80}">
			一般
		</c:when>
		<c:when test="${score>=60 && score<=70}">
			及格
		</c:when>
		<c:otherwise>
			不及格
		</c:otherwise>
	</c:choose>
	<hr/>
	<!-- 循环标签 -->
	<%
		//单列集合
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("张三", 15));
		list.add(new Student("李四", 25));
		list.add(new Student("王五", 20));
		pageContext.setAttribute("list", list);
		//双列集合Map
		Map<String, Student> map = new HashMap<String, Student>();
		map.put("101", new Student("狗剩", 20));
		map.put("102", new Student("铁蛋", 30));
		pageContext.setAttribute("map", map);
	%>
	<%--
      begin="" : 从哪个元素开始遍历，从0开始.默认从0开始
      end="":     到哪个元素结束。默认到最后一个元素
      step="" ： 步长    (每次加几)  ，默认1
      items=""： 需要遍历的数据（集合） 
      var=""： 每个元素的名称 
      varStatus=""： 当前正在遍历元素的状态对象。（count属性：当前位置，从1开始）
      
     --%>
	<c:forEach begin="0" step="1" items="${list }" end="2" var="student" varStatus="varSta">
	  序号:${varSta.count }   姓名:${student.name }   年龄:${student.age }<br/>
	<!--姓名:<c:out value="${student.name}"></c:out>  年龄:<c:out value="${student.age}"></c:out><br/>-->
	</c:forEach>
	
	<hr/>
	<c:forEach items="${map }" var="entry" varStatus="varSta">
	编号:${entry.key } 姓名:${entry.value.name }  年龄:${entry.value.age }<br/>
	</c:forEach>
	<c:forTokens items="name-age-gender-email" delims="-" var="str">
		${str }<br />
	</c:forTokens>
	
	<!-- url重写 -->
	<c:url value="/guess.jsp">
		<c:param name="name" value="张三"></c:param>
	</c:url>
	<hr/>
	<!-- catch异常 -->
	<c:catch var="myException">
		<%=100/0%>
	</c:catch>
	异常:<c:out value="${myException }"></c:out><br/>
	异常信息:<c:out value="${myException.message }"></c:out><br/>
	异常原因:<c:out value="${myException.cause }"></c:out><br/>
	异常stackTrace:<c:out value="${myException.stackTrace }"></c:out><br/>
	
</body>
</html>