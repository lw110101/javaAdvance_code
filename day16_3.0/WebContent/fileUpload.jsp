<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<fieldset>
		<legend> 上传单个文件 </legend>
		<!-- 文件上传时必须要设置表单的enctype="multipart/form-data"-->
		<form action="${pageContext.request.contextPath}/FileUploadServlet"
			method="post" enctype="multipart/form-data">
			上传文件： <input type="file" name="file"> <br> 
			<input type="submit" value="上传">
		</form>
	</fieldset>
	<hr />
	<fieldset>
		<legend> 上传多个文件 </legend>
		<!-- 文件上传时必须要设置表单的enctype="multipart/form-data"-->
		<form action="${pageContext.request.contextPath}/FileUploadServlet"
			method="post" enctype="multipart/form-data">
			上传文件： <input type="file" name="file1"> <br> 
			上传文件： <input type="file" name="file2"> <br> 
			<input type="submit" value="上传">
		</form>
	</fieldset>
</body>
</html>