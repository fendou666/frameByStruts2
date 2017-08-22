<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="../css/add.css" rel="stylesheet" type="text/css"/> 
<script type="text/javascript">
$(function(){
	$("#t3").click(function(){
		$.post(
				"StudentQueryServlet?action=conditions",
				{
					eecId:$("#eecId").val(),
					eecName:$("#eecName").val(),
					roleName:$("#roleName").val()
				},	
				function(data){
					var info="";
					var user=eval(data);
					info+="<table border=\"2\" align="center">"
					info+="<tr><td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>"
					info+="<td>出生日期</td><td>邮箱</td><td>手机号</td><td>职务</td></tr>"
						$.each(user,function(i){
							if(user[i].id != ""){ 
								info+="<tr>";
								info+="<td>"+(i+1)+"</td>";
								info+="<td>"+user[i].name+"</td>";
								info+="<td>"+user[i].id+"</td>";
								info+="<td>"+user[i].sex+"</td>";
								info+="<td>"+user[i].birthday+"</td>";
								info+="<td>"+user[i].telephone+"</td>";
								info+="<td>"+user[i].email+"</td>";
								info+="<td>"+user[i].roleName+"</td>";
								info+="</tr>";	
								
							}else{
								info="<tr><td colspan="8">未找到符合条件的数据</td></tr>";
							}
						});
					info+="</table>"; 
					$("#d42").html(info);
				},
				"JSON"
		)
	});
})
</script>
</head>
<body bgcolor="#F5FFFA">
	<div id="d0">
		<h1>学生信息综合管理系统</h1>
	</div>
	<div id="d1">
		<div id="d2">&nbsp;&nbsp;&nbsp;当前用户:班主任
			<div id="d21">
				<a href="#">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="#">退出登录</a>
			</div>
		</div>
		<div id="d3">
			<div id="d31"><a href="PersonalInfo.jsp">个人基本信息管理</a></div>
			<div id="d32"><a href="StudentQueryServlet">班级信息查询</a></div>
		</div>
		<div id="d4">
			<div id="d41"></div>
			<table id="t1" >
				<tr>
					<td>&nbsp;&nbsp;学号：<input type="text" id="eecId" name="eecId" value=""></td>
					<td>&nbsp;&nbsp;姓名：<input type="text" id="eecName" name="eecName" value=""></td>
					<td>&nbsp;&nbsp;职务：
						<select id="roleName">
							<option value="学生">学生</option>
							<option value="组长">组长</option>
							<option value="班长">班长</option>
							<option value="班主任">班主任</option>
						</select>
					</td>
					<td>&nbsp;&nbsp;<input type="button" id="query" name="query" value="查询" ></td>
				</tr>
				<tr>
				</tr>
			</table>
			<hr>
			<div id="t3">
				<table id="t2" border="1">
				<tr bgcolor="#8FBC8F">
					<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>
					<td>出生日期</td><td>邮箱</td><td>手机号</td><td>职务</td>
				</tr>
				<c:if test="${not empty requestScope.users}">
					<c:forEach var="stu" items="${requestScope.users}" varStatus="it">
					<tr>
						<td>${it.count}</td>
						<td>${stu.id}</td>
						<td>${stu.name}</td>
						<td>${stu.sex}</td>
						<td>${stu.age}</td>
						<td>${stu.birthday}</td>
						<td>${stu.email}</td>
						<td>${stu.telephone}</td>
						<td>${stu.roleName}</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.users}">
					<tr><td colspan="9">没有找到任何数据</td></tr>
				</c:if>
			</table>
			</div>
				
		</div>
	</div>
</body>
</html>