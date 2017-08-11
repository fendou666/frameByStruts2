<%@page import="com.sims.mvc.model.service.StudentServiceImp"%>
<%@page import="com.sims.mvc.model.service.IStudentService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
		
		<script>
			var path = "<%=path%>";
			$(function(){
				getData("getAllClassInfo","first");
			}); 
			
			function getClassInfoById(){
				getData("getClassInfoById");
			}
		</script>
		<script src="${pageScope.path}/js/Admin.js"></script>
		
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
				<ul>
					<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/admin/Admin.jsp">查询所有班级</a></li>
					<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/admin/CreateClass.jsp">创建新班级</a></li>
					<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/admin/appClassLeader.jsp">任命班主任</a></li>
				</ul>
			</div>
			<div id="show_info">
				<div id="info">
					<table >
						<tr>
							<td colspan="2" >请输入你想要查询的班级号：</td>
							<td colspan="2" ><input type="text" id="classID" name="classID" placeholder="请输入班级号"></td>
							<td><button id="submit" onclick="getClassInfoById()">查询</button></td>
						</tr>
						<tr>
							<td>班级ID</td><td>班级名</td><td>班长</td><td>班主任</td><td>代课老师</td>
						</tr>
					</table>
				</div>
				
			</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>
</html>