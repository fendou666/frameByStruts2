<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<%
			String path = request.getContextPath();
			pageContext.setAttribute("path", path);
		%>
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<script src="${pageScope.path}/js/main.js"></script>
		<script src="${pageScope.path}/js/succeed.js"></script>
	</head>
	
	<body>
		<div id="layer1" >
    		<img src="${pageScope.path}/img/bg.jpg" width="100%" height="100%"/>
    	</div>
		<div id="outer">
			<div id="logo" class="div_b show">学生信息管理系统</div>
			<!--主导航页面-->
			<div id="main_navig" class="div_b">
				<ul class="main_navig">
					<jsp:include page="user_right.jsp"></jsp:include>
				</ul>
			</div>
			<div id="inner_navig" class="div_b">
				<ul class="inner_navig">
					
				</ul>
			</div>
			<div id="show_info">
				<h1>恭喜你！${linkTitle}成功，3秒后调转到${linkTitle}页面</h1>
				<a href="${pageScope.path}${link}" id="link">点击跳转</a><span id="time">3</span>...
			</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>
</html>