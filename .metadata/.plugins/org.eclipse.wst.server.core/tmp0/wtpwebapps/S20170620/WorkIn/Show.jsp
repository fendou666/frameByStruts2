<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	pageContext.setAttribute("page", "request值");
	request.setAttribute("req", "request值");
	session.setAttribute("ses", "request值");	
	application.setAttribute("app", "request值");
%>


<body>
	form.userName <s:property value="#userName"/> <br>
	form.userName param方式 :   <s:property value="#parameters.userName"/> <br>
	form.userName request方式:   <s:property value="#request.userName"/> <br>
	form.userName pageContext方式:  <s:property value="#pageContext.page"/> <br>
	form.userName request方式: <s:property value="#request.req"/> <br>
	form.userName session方式: <s:property value="#session.ses"/> <br>
	form.userName application方式: <s:property value="#application.app"/> <br>
	<s:debug />
</body>
</html>