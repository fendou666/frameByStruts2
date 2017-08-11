<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE>
<html>
	<%
		String path = request.getContextPath();  //取到绝对根目录
		pageContext.setAttribute("path", path);
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>密码找回页面</title>
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css"/>
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/login.css" />
		<script src="js/main.js"></script>
	</head>
	
	<c:if test="${not empty requestScope.chpwd}">
		<div style="color: red;" align="center">${requestScope.chpwd}</div>
	</c:if>
	
<body>
		<div id="layer1" >
    			<img src="${pageScope.path}/img/bg.jpg" width="100%" height="100%"/>
    	</div>
	<form action="changePwd" method="post">
		<table style="margin: 30px auto;text-align: center;top: 100px;">
			<caption>
				<h1>密码找回</h1>
			</caption>
			<tr>
				<td >账号：</td>
				<td ><input type="text" id="userId" name="userId" placeholder="请输入账号"></td>
			</tr>
			<tr>
				<td>
					请输入你的新密码：
				</td>
				<td>
					<input type="password" id="pwd" name="pwd" placeholder="请输入您的新密码">
				</td>
			</tr>
			<tr>
				<td>
					请再次输入你的新密码：
				</td>
				<td>
					<input type="password" id="apwd" name="apwd" placeholder="请再次输入您的新密码">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit"  value="提交" >
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
			<s:actionerror/>
			<s:actionmessage/>
	</form>
	
	</body>
</html>