<%@page import="com.chinasofti.eecuser.model.javabean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="<%=request.getContextPath() %>/css/add.css" rel="stylesheet" type="text/css"/> 
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.min.js"></script>

<body>
<%-- <%
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
	%> --%>
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
			<div id="d32"><a href="<%=request.getContextPath() %>/AdminServlet?action=gotoTeacherManage">教师信息管理</a></div>
			<div id="d33"><a href="ClassManage.jsp">班级管理</a></div>
		</div>
		<di v id="d4">
			<div id="d41">个人信息展示页面</div>
		</div>
	</div>
	
	
</body>
</html>