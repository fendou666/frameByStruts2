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
		<script src="${pageScope.path}/js/Admin.js"></script>
		<script >
			var path = "<%=path%>";
		</script>
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
				<div id="info" align="center">
					<table class=" table-striped">
					<caption>任命班主任</caption>
						<tr>
							<td>
								请输入待任命的班级ID：
							</td>
							<td>
								<input type="text" id="clID" name="clID" placeholder="请输入班级ID">
							</td>
						</tr>
						<tr>
							<td>
								请输入你需要任命的班主任ID：
							</td>
							<td>
								<input type="text" id="clManId" name="clManId" placeholder="请输入班主任ID">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button id="submit" onclick="appointManTeacher()">提交</button>
							</td>
						</tr>
					</table>
					<div id="mesg"></div>
				</div>
			</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>

</html>