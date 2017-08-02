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
	<s:property value="name"/> <br>
	<s:property value="sex"/> <br>
	<s:property value="age"/> <br>
	<hr>
	<s:property value="u.name"/> <br>
	<s:property value="u.sex"/> <br>
	<s:property value="u.age"/> <br>
</body>
</html>