<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:property value="model.uname" />
	&&
	<s:property value="pass" />
	<br /> ${uname} && ${model.pass}
	<hr />
	<s:property value="#session.username" />
	<br /> ${sessionScope.username}

	<hr />

	<s:property value="#session.username1" />
	&& ${username1}

	<s:property value="#request.username2" />
	&& ${username2}
	<s:debug />
</body>
</html>