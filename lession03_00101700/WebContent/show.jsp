<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:property value="username"/><br/>
	<s:iterator value="data"  var="item">
		<s:property value="item"/>
	</s:iterator>
	<hr/>
	<s:debug/>
	${username}<br/>
	<c:forEach items="${data}" var="item1">
		<c:out value="${item1}"/>
	</c:forEach>
	
	
</body>
</html>