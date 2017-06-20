<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <form action="PojoAct" method="post"> -->
	<!-- 第二种要求数据格式必须匹配  -->
	<form action="PojoAct">
		用户名：<input type="text" name="u.name"> <br>
		密码：<input type="text" name="u.sex"> <br>
		年龄：<input type="text" name="u.age"> <br>
		<input type="submit" value="提交">
	</form>
</body>
</html>