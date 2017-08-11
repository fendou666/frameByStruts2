<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<%
		String path = request.getContextPath();
		pageContext.setAttribute("path", path);
	%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="${pageScope.path}/dist/css/bootstrap.min.css" />
		<link rel="stylesheet"href="${pageScope.path}/css/bg.css" />
		<link rel="stylesheet"href="${pageScope.path}/css/main.css" />
		<script src="${pageScope.path}/js/jquery-1.7.2.min.js"></script>
		<script src="${pageScope.path}/js/main.js"></script>
		<script src="${pageScope.path}/js/classStudent.js"></script>
	    <script type="text/javascript">
	    var path = "<%=path%>"
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
				<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/classMonit/classMoniter.jsp">班信息查询</a></li>
				<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/classMonit/classStuInfo.jsp">班级成员查询</a></li>
				<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/classMonit/createTeams.jsp">创建组</a></li>
				<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/classMonit/showTeam.jsp">所有组信息展示</a></li>
				<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/classMonit/checkTeamStudent.jsp">组员信息查询</a></li>
				<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/classMonit/appTeamLeader.jsp">替换组长</a></li>
			</ul>
		</div>
		
		<div id="show_info">
			<div id="info" align="center">
			</div>	
		</div>
		
		<div id="footer" class="div_b">链接信息区</div>
	</div>

</body>
</html>

