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
<script type="text/javascript" src="js/adminOperation.js"></script>
<script type="text/javascript">
	var servlet = "<%=request.getContextPath()%>/AdminServlet";
	$(function(){
		$("#query").click(function(){
			getEecUserInfo(servlet, "first");
		})
	})
</script>
<body>
	<%
		final int ADMIN_ROLEID = 3006;
		
		// 登录信息暂时没有的情况，自己先做一个数据保证程序运行
		UserInfo stu = new UserInfo(20170207, 170000002, "管理员", "男",
				88,  "4437074544@qq.com", 11593239991L, 3006);
		session.setAttribute("userInfo", stu);
		UserInfo admin = (UserInfo)session.getAttribute("userInfo");
		if(admin==null){
			//request.getRequestDispatcher("NoLogin.jsp").forward(request, response);
			return;
		}
		// 这一步判断可以去掉，肖梦娜使用过滤器做， 对应的权限只能访问对应的目录
		if(admin.getRoleId()!=ADMIN_ROLEID){
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
			<div id="d32"><a href="#">班级管理</a></div>
			<div id="d33"><a href="#">教师信息查看</a></div>
			<div id="d34"><a href="#">添加教师</a></div>
			<div id="d35"><a href="#">罢免教师</a></div>
			<div id="d36"><a href="#">教师信息更改</a></div>
		</div>
		<div id="d4">
			<div id="d41">教师信息查询</div>
			<table id="t1" >
				<tr>
					<td>职位：
						<select name="roleId" id="roleId">
							<option value="postAll">全部</option>
							<option value="3004">任课老师</option>
							<option value="3005">班主任</option>
						</select>
					</td>
					<td>班级：
						<select name="classId" id="classId">
							<option value="classAll">全部</option>
							<option value="20170207">java0207</option>
							<option value="20170208">java0208</option>
						</select>
					</td>
					<td>&nbsp;&nbsp;学号：<input type="text"  name="id" value=""></td>
					<td>&nbsp;&nbsp;姓名：<input type="text" name="name" value=""></td>
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