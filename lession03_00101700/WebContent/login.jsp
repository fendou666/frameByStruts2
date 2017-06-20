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
	<s:property value="exception.message"/>
	${exception.message}
	<s:actionmessage />
	<%-- ${actionMessages} --%>
	<s:form action="Login_login" method="get">
		<%-- 		<s:textfield name="uname" label="name"  />
		<s:password name="pass" label="password" />
		<s:submit value="LOGIN" />
		<s:reset value="RESET" /> --%>

		<s:textfield name="model.uname" label="name" />
		<s:password name="pass" label="password" />
		<s:submit value="LOGIN" />
		<s:reset value="RESET" />
	</s:form>
	
	<s:debug/>
</body>
</html>