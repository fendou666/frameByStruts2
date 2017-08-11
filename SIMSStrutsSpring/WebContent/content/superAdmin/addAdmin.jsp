<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<% %>
<!DOCTYPE html>
<html>
	<%
		String path = request.getContextPath();
		pageContext.setAttribute("path", path);
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="${pageScope.path}/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<script src="${pageScope.path}/js/jquery-1.7.2.min.js"></script>
		<script src="${pageScope.path}/js/main.js"></script>
		<script src="${pageScope.path}/js/superAdmin.js"></script>
		<script>
			var path = "<%=path%>"
		</script>
		
		<style type="text/css">
			.left{
				text-align: left;
			}
			td, table{
				border: 0px;
				padding:3px;
			}
		</style>
		
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
					<jsp:include page="../user_right.jsp"></jsp:include>
				</ul>
			</div>
			<div id="inner_navig" class="div_b">
				<ul class="inner_navig">
					<li class="inner_navig"  id="li_5"><a class="btn btn-primary" href="${pageScope.path}/content/superAdmin/showAdmin.jsp">查看管理员</a></li>
					<li class="inner_navig"  id="li_6"><a class="btn btn-primary" href="${pageScope.path}/content/superAdmin/addAdmin.jsp">添加管理员</a></li>
				</ul>
			</div>
			<div id="show_info">
				<div id="info" align="center">
					<table>
						<tr><td>请输入要添加的管理员ID</td></tr>
						<tr><td><input type="text" id="id"></td></tr>
						<tr><td><button id="add" onclick="addAdmin()">添加</button></td></tr>
						<tr><td><span id="mesg"></span></td></tr>
					</table>
				</div>
	 		</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>

</html>