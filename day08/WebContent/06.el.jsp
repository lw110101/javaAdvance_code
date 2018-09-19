<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="entity.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL获取数据</title>
</head>
<body>
<%--EL表达式是用来替换jsp的表达式的 --%>
	<% //输出基本数据类型变量
		pageContext.setAttribute("name", "张三");
		pageContext.setAttribute("password", "123446",pageContext.REQUEST_SCOPE);
	%>
	${name}<br/>
	${age}  <%--找不到返回空字符串，而不是null --%><br/>
	${requestScope.password}
	
	<hr/>
	<%
		//输出对象的属性值
		pageContext.setAttribute("student", new Student("狗娃", 20));
	%>
	姓名:${student.name}   年龄:${student.age }
	<hr/>
	<%
		//输出集合对象 --单列集合List
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("张三", 15));
		list.add(new Student("李四", 25));
		list.add(new Student("王五", 20));
		pageContext.setAttribute("list", list);
		//双列集合Map
		Map<String,Student> map=new HashMap<String,Student>();
		map.put("101",new Student("狗剩",20));
		map.put("102",new Student("铁蛋",30));
		pageContext.setAttribute("map", map);	
	%><%--Array list.get(int index) --%>
		姓名:${list[0].name }   年龄:${list[0].age } <br/>
		姓名:${list[1].name }   年龄:${list[1].age } <br/>
		姓名:${list[2].name }    年龄:${list[2].age } <br/>
		<hr/><%--V map.get(Object key) --%>
		  姓名:${map['101'].name }    年龄:${map['101'].age }<br/> 
	 	 姓名:${map['102'].name }    年龄:${map['102'].age }<br/> 
</body>
</html>