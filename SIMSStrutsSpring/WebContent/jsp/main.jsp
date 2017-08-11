<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		String path = request.getContextPath();  //取到绝对根目录
		pageContext.setAttribute("path", path);
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css"/>
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/login.css" />
		<script src="js/main.js"></script>
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
					<li class="inner_navig"  id="li_5"><a>内部导航条1</a></li>
					<li class="inner_navig"  id="li_6"><a>内部导航条2</a></li>
					<li class="inner_navig"  id="li_7"><a>内部导航条3</a></li>
					<li class="inner_navig"  id="li_8"><a>内部导航条4</a></li>
				</ul>
			</div>
			<div id="show_info">
				这是信息展示区

			</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>

</html>