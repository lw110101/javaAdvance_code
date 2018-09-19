<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="tag.*,java.util.*"%>
  <%@ taglib uri="http://gz.itcast.cn"  prefix="itcast"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>循环：forEach</title>
</head>
<body>
	<%
		//输出集合对象 --单列集合List
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
	<itcast:forEach items="${list }" var="student">
	姓名:${student.name }  年龄:${student.age }<br/>
	</itcast:forEach>
	<hr/>
	<itcast:forEach items="${map }" var="entry">
	编号:${entry.key}   姓名:${entry.value.name }   年龄:${entry.value.age } <br/>
	</itcast:forEach>
</body>
</html>