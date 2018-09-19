<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/FileUploadServlet?method=upload"
		enctype="multipart/form-data" method="post">
		<fieldset>
			
			<legend> 上传单个文件 </legend>
			用户名<input type="text" name=userName><br/>
			上传文件： <input type="file" name="file"> <br> <input
				type="submit" value="上传">
				
				<hr/>
				<!-- <legend>上传多个文件</legend>
				上传文件1<input type="file" name="file1"><br/>
				上传文件2<input type="file" name="file2"><br/>
				上传文件3<input type="file" name="file3"><br/>
				<input type="submit" value="上传"> -->
			
		</fieldset>

	</form>
</body>
</html>