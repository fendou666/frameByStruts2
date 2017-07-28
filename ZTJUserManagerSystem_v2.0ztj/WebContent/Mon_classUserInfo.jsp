<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>班长友好界面</title>
<meta name="description"
	content="Examples for creative website header animations using Canvas and JavaScript" />
<meta name="keywords"
	content="header, canvas, animated, creative, inspiration, javascript" />
<meta name="author" content="Codrops" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="/css/normalize.css" />
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
function getPagenum(){
	return $("#pagenum").val();
}
</script>
<script type="text/javascript">
 function getPage(forword){
	 $.post(
				"MonitorInfoServlet?action=classInf&page="+forword+"&classId=${sessionScope.user.classId}",
				{  },
				function(data){
					var students=eval(data);
					var htmStr="";
					htmStr+="<table border=\"2\" align=\"center\" class=\"con_table\">";
					htmStr+="<caption>";
					htmStr+="	<font size=\"6\">${sessionScope.user.classId}班学员信息一览表</font><br>";
					htmStr+="	<a style='margin-left: 70%;'>班主任：${requestScope.headmaster.name}</a>||<a>任课老师：${requestScope.userTeacher.name}</a>";										
					htmStr+="</caption>";
					htmStr+="<tr><th>编号</th><th>学号</th><th>姓名</th><th>性别</th><th>年龄</th><th>移动电话</th><th>住址</th><th>邮箱</th><th>在校情况</th></tr>";
					$.each(students,function(i){
						if (students[i].id=="") {
							htmStr+="<tr>";
							htmStr+="<td colspan=\"9\">暂无学员信息！！！</td>";
							htmStr+="</tr>";
							return;
						}
						htmStr+="<tr>";
						htmStr+="<td>"+(i+1)+"</td>";
						htmStr+="<td>"+students[i].id+"</td>";
						htmStr+="<td>"+students[i].name+"</td>";
						htmStr+="<td>"+students[i].sex+"</td>";
						htmStr+="<td>"+students[i].age+"</td>";
						htmStr+="<td>"+students[i].phone+"</td>";
						htmStr+="<td>"+students[i].address+"</td>";
						htmStr+="<td>"+students[i].mail+"</td>";
						htmStr+="<td>"+students[i].status+"</td>";
						htmStr+="</tr>";	
					})
					htmStr+="<tr>";
					htmStr+="<td colspan='2'><input type='button' value='首页' onclick='getPage(\"first\")'/></td>";
					htmStr+="<td ><input type='button' value='前页' onclick='getPage(\"pre\")'/></td>";
					htmStr+="<td ><input type='button' value='次页' onclick='getPage(\"nxt\")'/></td>";
					htmStr+="<td colspan='2'><input type='button' value='尾页' onclick='getPage(\"last\")'/></td>";
					htmStr+="<td colspan='3'><input type='number' name='pagenum' id='pagenum'><input type='button' value='指定页' onclick='getPage(getPagenum())'/></td>";
					htmStr+="</tr>";
					htmStr+="</table>";
					$("#tbl").html(htmStr);
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
							<div class="con_rmenu" id="nav">用户管理>>班级成员</div>
							<hr>
							<div class="con_rcon" style="overflow: hidden;">
								<div id="tbl">
									 <table border="2" align="center" class="con_table">
										<caption>
											<font size="6">${sessionScope.user.classId}班学员信息一览表</font><br>
											<a style="margin-left: 70%;">班主任：${requestScope.headmaster.name}</a>||<a>任课老师：${requestScope.userTeacher.name}</a>										
										</caption>
										<tr>
											<th>编号</th><th>学号</th><th>姓名</th><th>性别</th><th>年龄</th><th>移动电话</th><th>住址</th><th>邮箱</th><th>在校情况</th>
										</tr>
										<c:choose>
											<c:when test="${not empty requestScope.list}">
												<c:forEach items="${requestScope.list}" var="stu"
													varStatus="iid">
													<tr>
														<td><c:out value="${iid.index+1}" /></td>
														<td><c:out value="${stu.id}" /></td>
														<td><c:out value="${stu.name}" /></td>
														<td><c:out value="${stu.sex}" /></td>
														<td><c:out value="${stu.age}" /></td>
														<td><c:out value="${stu.phone}" /></td>
														<td><c:out value="${stu.address}" /></td>
														<td><c:out value="${stu.mail}" /></td>
														<td><c:out value="${stu.delete}" /></td>
													</tr>
												</c:forEach>
												<tr>
													<td colspan="2"><input type="button" value="首页" id="first" /></td>
													<td ><input type="button" value="前页" onclick="getPage('pre')"/></td>
													<td ><input type="button" value="次页" onclick="getPage('nxt')"/></td>
													<td colspan="2"><input type="button" value="尾页" onclick="getPage('last')"/></td>
													<td colspan="3"><input type="number" name="pagenum" id="pagenum"><input type="button" value="指定页" onclick="getPage(getPagenum())"/></td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="9">暂无学员信息！！！</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
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