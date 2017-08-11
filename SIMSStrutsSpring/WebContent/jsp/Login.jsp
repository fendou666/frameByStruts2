<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE>
<html>
	<%
		String path = request.getContextPath();  //取到绝对根目录
		pageContext.setAttribute("path", path);
	%>
	<head>
		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录页面</title>
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css"/>
		<link rel="stylesheet" href="${pageScope.path}/css/login.css" />
		<script src="${pageScope.path}/js/jquery-1.7.2.min.js"></script>
		<script src="${pageScope.path}/js/login.js"></script>
		<script src="${pageScope.path}/js/main.js"></script>
		
		<script >
			var path = "<%=path%>"
		</script>
	</head>
	
	<c:if test="${not empty requestScope.chpwd}">
		<div style="color: red;">${requestScope.chpwd}</div>
	</c:if>
	
	<body onload="getNewRand()" >
		<div id="layer1" >
    		<img id="main_bg" src="${pageScope.path}/img/bg.jpg" width="100%" height="100%"/>
    	</div>
    	<div id="main">
				<div id="outer">
					<div id="head">登录</div>
					<div class="block">
						<input type="text" id="userId" name="userId" placeholder="用户名" >
						<a href="${pageScope.path}/jsp/regist.jsp">注册</a>
					</div>
					<div class="block">
						<input type="password" id="pwd" name="pwd" placeholder="密码" >
						<a href="${pageScope.path}/jsp/PasswordBack.jsp">密码找回</font></a>
					</div>
					<div class="block">
						<select name="permissions" id="permissions" >
							<option value="stu">学员</option>
							<option value="isleader">组长</option>
							<option value="ismonitor">班长</option>
							<option value="ismanteach">班主任</option>
							<option value="isnormadmin">一般管理员</option>
						</select>
					</div>
					<div class="block">
						<input type="text" id="randNum" name="randNum" onblur="checkImgNum()" placeholder="请输入验证码">
						<img id="image" align="top" alt="1" src="${pageScope.path}/identifyNum" onclick="getNewRand()">
					</div>
					<div class="block">
						<button  id="submit" onclick="return login()" >登录</button>
					</div>
				</div>
		</div>
		<s:actionerror/>
		<s:actionmessage/>
	</body>
</html>