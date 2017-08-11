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
		<script src="${pageScope.path}/js/main.js"></script>
		<script src="${pageScope.path}/js/jquery-1.7.2.min.js"></script>
		<script src="${pageScope.path}/js/createTeam.js"></script>
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
		<div id="show_info" align="center">
			<table>
			<caption>创建组</caption>
				<tr>
					<td>组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;D:</td>
					<td><input type="text" id="teamId" name="teamId" placeholder="请输入组ID"></td>
				</tr><br>
				
				<tr>
					<td>组&nbsp;&nbsp;名&nbsp;&nbsp;称:</td>
					<td><input type="text" id="teamName" name="teamName" placeholder="请输入组名称"></td>
				</tr><br>
				
				<tr>
					<td>组&nbsp;&nbsp;长&nbsp;&nbsp;I&nbsp;D:</td>
					<td><input type="text" id="leaderId" name="leaderId" placeholder="请输入组长ID"></td>
				</tr><br>
				
				<tr>
					<td colspan="2"><button id="add" onclick="createTeam()">添加</button></td>
				</tr>
				
				<tr><td colspan="2"><span id="mesg"></span></td></tr>
			</table>

		</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	
</body>
</html>