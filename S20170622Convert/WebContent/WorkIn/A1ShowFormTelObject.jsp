<%@page import="com.opensymphony.xwork2.ActionContext"%>
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
	<%
		request.setAttribute("aaa", "aaa");
		String likes = request.getParameter("likes");
		System.out.println("爱好是 " + likes);
		String[] strAry = (String[])ActionContext.getContext().getParameters().get("likes");
		request.setAttribute("likes", strAry);
	%>
	测试：<s:property value="#request.aaa == 'aaa'"/> <br>
	用户名：<s:property value="userName"/> <br>
	<%-- 出生日期: <s:property value="birthday"/> <br> --%>
	
	爱好: <s:iterator var="like"  value="#request['likes']">
			<s:if test="#like == 1">
				<input type="checkbox" name="likes" checked="checked" value="1" > 足球
			</s:if>
			<s:if test="#like == 2">
			 	<input type="checkbox" name="likes" checked="checked" value="2" > 篮球
			</s:if>
			<s:if test="#like == 3">
			 	<input type="checkbox" name="likes" checked="checked" value="3" > 羽毛球
			</s:if>
		</s:iterator>
		
	爱好简单: <s:iterator var="like"  value="likes">
			<s:if test="#like == 1">
				<input type="checkbox"  checked="checked" value="1" > 足球
			</s:if>
			<s:if test="#like == 2">
			 	<input type="checkbox" checked="checked" value="2" > 篮球
			</s:if>
			<s:if test="#like == 3">
			 	<input type="checkbox"  checked="checked" value="3" > 羽毛球
			</s:if>
		</s:iterator>
		
	<br>
	<%-- 爱好: <s:property value="likes"/> <br> --%>
	电话号码: <s:property value="tel"/> <br>
	<s:debug/>
</body>
</html>