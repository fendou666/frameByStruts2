<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	我是WorkIn/Test/的内容
	<form action="<%=request.getContextPath() %>/abc/HelloForm1">
	<!-- <form action="/HelloForm1">
	<form action="ABC/HelloForm1">
	<form action="/ABC/HelloForm1"> -->
	<%-- <form action="<%=request.getContextPath() %>/abc/helloForm"> --%>
		<p>
			<span>用户名</span>
			<input type="text" name="userName" id="userName" >
		</p>
		<p>
			<span>密码</span>
			<input type="text" name="pwd" id="pwd" >
		</p>
		<p>
			<input type="submit" value="提交">
		</p>
	</form>
</body>
</html>