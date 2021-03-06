<%@page import="com.chinasofti.eecuser.model.javabean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="../css/add.css" rel="stylesheet" type="text/css"/> 
</head>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/adminOperation.js"></script>
<script type="text/javascript">
	var servlet = "<%=request.getContextPath()%>/AdminServlet";
	$(function(){
		$("#query").click(function(){
			getEecUserInfo(servlet, "first", "query");
		})
	})
</script>
<body>
	<%
		request.setAttribute("servlet", request.getContextPath() + "/AdminServlet");
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
			<div id="d32"><a href="ClassManage.jsp">班级管理</a></div>
			<div id="d33"><a href="<%=request.getContextPath() %>/AdminServlet?action=gotoTeacherManage">教师信息查看</a></div>
			<div id="d34"><a href="TeacherAdd.jsp">添加教师</a></div>
			<div id="d35"><a href="TeacherDEL.jsp">罢免教师</a></div>
			<div id="d36"><a href="TeacherUPD.jsp">教师信息更改</a></div>
		</div>
		<div id="d4">
			<div id="d41">教师信息查询</div>
			<table id="t1" >
				<tr>
					<td>职位：
						<select name="roleId" id="roleId">
							<option value="">全部</option>
							<option value="3004">任课老师</option>
							<option value="3005">班主任</option>
						</select>
					</td>
					<td>班级：
						<select name="classId" id="classId">
							<option value="">全部</option>
							<option value="20170207">java0207</option>
							<option value="20170208">java0208</option>
						</select>
					</td>
					<td>&nbsp;&nbsp;学号：<input type="text" id="id"  name="id" value=""></td>
					<td>&nbsp;&nbsp;姓名：<input type="text" id="name" name="name" value=""></td>
					<td>&nbsp;&nbsp;<input type="button" id="query" name="query" value="查 询"></td>
				</tr>
			</table>
			<table id="t2" border="1">
				<tr bgcolor="#8FBC8F">
					<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>
					<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td>
				</tr>
				<c:forEach var="teacher" items="${sessionScope.teacherAllData}">
					<tr>
						<td>${teacher.id}</td>
						<td>${teacher.classId}</td>
						<td>${teacher.id}</td>
						<td>${teacher.name}</td>
						<td>${teacher.sex}</td>
						<td>${teacher.age}</td>
						<td>${teacher.email}</td>
						<td>${teacher.telephone}</td>
						<td>${teacher.roleName}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage('${requestScope.servlet}', 'first', 'query' )" value="首页" ></td>
					<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage('${requestScope.servlet}', 'pre', 'query' )" value="前页" ></td>
					<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage('${requestScope.servlet}', 'next', 'query' )" value="次页" ></td>
					<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage('${requestScope.servlet}', 'last', 'query')" value="尾页" ></td>
					<td colspan="2"><input type="number" id="pageIndex">
					<input type="button" onclick="getQueryTeacherByPage('${requestScope.servlet}', getCustomPageIndex(),'query')" value="指定页" ></td>
				</tr>
			</table>
			<!-- 这里不可以采用a标签 因为会跳转网页， 这里我需要总结以下，可以直接跳转网页的情况  -->
			<!--   -->
			<div id="t3">
				<c:if test="${not empty sessionScope.teacherPageMax  }">
					<c:forEach var="max" begin="1" end="${sessionScope.teacherPageMax }" >
						<input type="button"  onclick="getQueryTeacherByPage('${requestScope.servlet}', ${max} ,'query')" value="${max}页">
						<c:if test="${max%10 == 0} ">
							<br>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	
</body>
</html>