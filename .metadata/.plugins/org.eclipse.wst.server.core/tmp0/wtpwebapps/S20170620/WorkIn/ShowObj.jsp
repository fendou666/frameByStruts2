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
	
	action 属性值 <s:property value="actionMeb"/> <br>
	ActionContext 属性值 <s:property value="acvv"/><br>
	session 属性值 <s:property value="#session.sesvv"/><br>
	Parameters 属性值 <s:property value="#parameters.paramvv"/><br>
	Application 属性值 <s:property value="#application.appvv"/><br>
	ValueStack 属性值 <s:property value="vsckvv"/><br>
	ValueStack加# 属性值 <s:property value="#vsckvv"/><br>
	ContextMap 属性值 <s:property value="conmpvv"/><br>
	ContextMap加# 属性值 <s:property value="#conmpvv"/><br>
	<s:debug/>
</body>
</html>