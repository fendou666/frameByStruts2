<%@page import="com.chinasofti.eecuser.model.javabean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="../css/add.css" rel="stylesheet" type="text/css"/> 
</head>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>

<body>
	<%
		UserInfo stu = new UserInfo(20170207, 170000002, "管理员", "男",
				88,  "4437074544@qq.com", 11593239991L, 3006);
		session.setAttribute("userInfo", stu);
		UserInfo admin = (UserInfo)session.getAttribute("userInfo");
		if(admin==null){
			//request.getRequestDispatcher("NoLogin.jsp").forward(request, response);
			return;
		}
	%>
	<div id="d0">
		<h1>学生信息综合管理系统</h1>
	</div>
	<div id="d1">
		<div id="d2">&nbsp;&nbsp;&nbsp;当前用户:学生
		<div id="d21">
			<a href="#">首页</a>&nbsp;&nbsp;&nbsp;
			<a href="#">退出登录</a>
		</div>
		</div>
		<div id="d3">
			<div id="d31"><a href="#">个人信息</a></div>
			<div id="d32"><a href="TeacherManage.jsp">教师信息管理</a></div>
			<div id="d33"><a href="ClassManage.jsp">班级管理</a></div>
		</div>
		<div id="d4">
			<div id="d41">班级删除</div>
			<table id="t1" >
				<tr>
					<td>&nbsp;&nbsp;班级：<input type="text"  name="classId" value=""></td>
					<td>&nbsp;&nbsp;班主任：<input type="text" name="monitorName" value=""></td>
					<td>&nbsp;&nbsp;<input type="button" id="query" name="query" value="查 询"></td>
				</tr>
			</table>
			<table id="t2" border="1">
				<tr bgcolor="#8FBC8F">
					<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>
					<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td><td>操作</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>