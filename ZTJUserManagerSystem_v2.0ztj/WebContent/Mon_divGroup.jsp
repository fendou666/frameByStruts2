<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>进行分组</title>
<meta name="description"
	content="Examples for creative website header animations using Canvas and JavaScript" />
<meta name="keywords"
	content="header, canvas, animated, creative, inspiration, javascript" />
<meta name="author" content="Codrops" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<link rel="stylesheet" type="text/css" href="css/monitor.css">
<link rel="stylesheet" href="http://www.yyyweb.com/demo/common/init.css">
<script type="text/javascript" src="js/jquery-1.6.min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="js/jquery.backgroundPosition.fixed.js" charset="utf-8"></script>
<script type="text/javascript" src="js/menuAnimation2.js"
	charset="utf-8"></script>
<script type="text/javascript">
	var selValue;
	function getValue(value){
		selValue = value;
	}
	function clickRequest(id,classId){
		location.href="MonChooseServlet?action=divGroup&selValue="+selValue+"&id="+id+"&classId="+classId+"&headmasterId=${sessionScope.user.headmasterId}&teacherId=${sessionScope.user.teacherId}"; 
	} 
</script>
<script type="text/javascript">
function getPagenum(){
	return $("#pagenum").val();
}
</script>
<script type="text/javascript">
function getPage(forword){
	$.post(
		"MonitorInfoServlet?action=divGroup&page="+forword+"&classId=${sessionScope.user.classId}",
		{  },
		function(data){
			var students=eval(data);
			var htmStr="";
			htmStr+="<form action=\"MonChooseServlet\" method=\"post\">";
			htmStr+="<table border=\"2\" align=\"center\" class=\"con_table\">";
			htmStr+="<caption>";
			htmStr+="	<font size=\"6\">${sessionScope.user.classId}班学员信息一览表</font><br>";
			htmStr+="	<a style='margin-left: 70%;'>班主任：${requestScope.headmaster.name}</a>||<a>任课老师：${requestScope.userTeacher.name}</a>";										
			htmStr+="</caption>";
			htmStr+="<tr><th>编号</th><th>学号</th><th>姓名</th><th>组号</th><th>身份</th><th>进行分组</th></tr>";
			$.each(students,function(i){
				if (students[i].id=="") {
					htmStr+="<tr>";
					htmStr+="<td colspan=\"6\">暂无学员信息！！！</td>";
					htmStr+="</tr>";
					return;
				}
				htmStr+="<tr>";
				htmStr+="<td>"+(i+1)+"</td>";
				htmStr+="<td>"+students[i].id+"</td>";
				htmStr+="<td>"+students[i].name+"</td>";
				htmStr+="<td>"+students[i].groupId+"</td>";
				if(students[i].power==1){
					htmStr+="<td>普通学员</td>";
				}else if(students[i].power==2){
					htmStr+="<td>组长</td>";
				}else if(students[i].power==3){
					htmStr+="<td>班长</td>";
				}else if(students[i].power==4){
					htmStr+="<td>任课老师</td>";
				}else if(students[i].power==5){
					htmStr+="<td>班主任</td>";
				}else if(students[i].power==6){
					htmStr+="<td>一般管理员</td>";
				}else{
					htmStr+="<td>超级管理员</td>";
				}
				htmStr+="<td>"
				htmStr+="<select style='width: 150px;' name='selValue' id='selValue' onchange='getValue(this.value)' >"
				htmStr+="<option selected='selected'>请选择组长</option>"
				htmStr+="<c:forEach items='${requestScope.listLeader}' var='leader' >"
				htmStr+="<option value='${leader.id}'><c:out value='${leader.name}' /></option>"
				htmStr+="</c:forEach>"
				htmStr+="</select>"
				htmStr+="<input type=\"button\" value=\"提交\" id=\"btn\" onclick=\"clickRequest("+students[i].id+","+students[i].classId+")\">"
				htmStr+="</td>";
				htmStr+="</tr>";
			})
			htmStr+="<tr>";
			htmStr+="<td><input type='button' value='首页' onclick='getPage(\"first\")'/></td>";
			htmStr+="<td ><input type='button' value='前页' onclick='getPage(\"pre\")'/></td>";
			htmStr+="<td ><input type='button' value='次页' onclick='getPage(\"nxt\")'/></td>";
			htmStr+="<td><input type='button' value='尾页' onclick='getPage(\"last\")'/></td>";
			htmStr+="<td colspan='2'><input type='number' name='pagenum' id='pagenum'><input type='button' value='指定页' onclick='getPage(getPagenum())'/></td>";
			htmStr+="</tr>";
			htmStr+="</table>";
			htmStr+="</form>";
			$(".con_rcon").html(htmStr);
		},
		"JSON"
		)
	}
	$(function(){
		$("#first").click(function(){
			getPage('first');
		})
	})
</script>
</head>
<body>
	<div class="container demo-2">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="main-title">
					<div class="main">
						<h1>SeSky用户管理信息系统</h1>
					</div>
					<div class="context">
						<div class="con_left">
							<table id="menu" style="position: absolute;">
								<tr>
									<td><a href="Mon_monitor.jsp"><span class="menu_con">用户管理</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 考勤管理</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 请假管理</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 考试管理</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 活动管理</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 奖罚管理</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 资源共享</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 留言板</span></a></td>
								</tr>
								<tr>
									<td><a><span class="menu_con"> 注销/退出</span></a></td>
								</tr>
							</table>
						</div>

						<div class="con_right" style="margin-left: 205px;">
							<div style="float: right;font-size: 20px;">当前用户：${sessionScope.user.name}</div>
							<div class="con_rmenu" id="nav">用户管理>>分配组员</div>
							<hr>
							<div class="con_rcon">
								<table border="2" align="center" class="con_table">
									<caption>
										<font size="6">${sessionScope.user.classId}班学员信息一览表</font><br>
										<a style="margin-left: 70%;">班主任：${requestScope.headmaster.name}</a>||<a>任课老师：${requestScope.userTeacher.name}</a>										
									</caption>
									<tr>
										<th>编号</th>
										<th>学号</th>
										<th>姓名</th>
										<th>组号</th>
										<th>身份</th>
										<th>进行分组</th>
									</tr>
									<c:choose>
										<c:when test="${not empty requestScope.listStu}">
											<c:forEach items="${requestScope.listStu}" var="stu" varStatus="iid">
												<tr>
													<!-- <form action="MonChooseServlet?action=divGroup" method="post"> -->
														<td><c:out value="${iid.index+1}" /></td>
														<td id="id"><c:out value="${stu.id}" /></td>
														<td><c:out value="${stu.name}" /></td>
														<td><c:out value="${stu.groupId}" /></td>
														<td>
														<c:choose>
															<c:when test="${stu.power eq 1}">普通学员</c:when>
															<c:when test="${stu.power eq 2}">组长</c:when>
															<c:when test="${stu.power eq 3}">班长</c:when>
															<c:when test="${stu.power eq 4}">任课老师</c:when>
															<c:when test="${stu.power eq 5}">班主任</c:when>
															<c:when test="${stu.power eq 6}">一般管理员</c:when>
															<c:otherwise>超级管理员</c:otherwise>
														</c:choose>
														</td>
														<td>
															<select style="width: 150px;" name="selValue" id="selValue" onchange="getValue(this.value)">
																<option value="" selected="selected">请选择组长</option>
																<c:forEach items="${requestScope.listLeader}" var="leader" >
																	<option value="${leader.id}"><c:out value="${leader.name}" /></option>
																</c:forEach>
															</select><!-- MonChooseServlet?action=divGroup&selValue="+selValue+"&id="+id+"&classId="+classId -->
															<input type="submit" value="提交" id="btn"  onclick="clickRequest(${stu.id},${stu.classId})">
														</td>
													<!-- </form> -->
												</tr>
											</c:forEach>
											<tr>
												<td><input type="button" value="首页" id="first" /></td>
												<td ><input type="button" value="前页" onclick="getPage('pre')"/></td>
												<td ><input type="button" value="次页" onclick="getPage('nxt')"/></td>
												<td ><input type="button" value="尾页" onclick="getPage('last')"/></td>
												<td colspan="2"><input type="number" name="pagenum" id="pagenum"><input type="button" value="指定页" onclick="getPage(getPagenum())"/></td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="6">暂无学员信息！！！</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</table>
							</div>
						</div>
					</div>
					<div class="footer">&copy; 2017 海阔天空 . All rights reserved.</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/rAF.js"></script>
	<script src="js/demo-2.js"></script>
</body>
</html>