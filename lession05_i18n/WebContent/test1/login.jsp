<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:text name="loginPage"/></title>.
   <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/> 
</head>
<body>
<s:form action="login.action">
	<s:textfield name="username" key="user"/>
	<s:textfield name="password" key="pass"/>
	<s:submit key="login"/>
</s:form>
</body>
</html>
