<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:select list="{'上海','北京','西安','海口'}" value="'西安'"></s:select>
	<s:select list="#{'a':'aaa','b':'bbb','c':'ccc','d':'ddd'}" value="'c'"></s:select>
	
	<s:iterator var="l" value="acList">
		<s:property value="#l"/><br>
		<%-- <s:property value="'l'"/><br> --%>
	</s:iterator>
	
</body>
</html>