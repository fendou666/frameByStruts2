<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="css/add.css" rel="stylesheet" type="text/css"/> 
</head>
<body bgcolor="#F5FFFA">
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
			<div id="d31"><a href="#">个人基本信息管理</a></div>
			<div id="d32"><a href="#">权限管理</a></div>
		</div>
		<div id="d4">
			<div id="d41">学生信息查询</div>
			<table id="t1" >
				<tr>
					<td>班级：
						<select>
							<option>全部</option>
							<option>java0201</option>
							<option>java0207</option>
						</select>
					</td>
					<td>&nbsp;&nbsp;学号：<input type="text" name="stuNum" value=""></td>
					<td>&nbsp;&nbsp;姓名：<input type="text" name="stuName" value=""></td>
					<td>&nbsp;&nbsp;<input type="submit" name="query" value="查 询"></td>
				</tr>
			</table>
			<table id="t2" border="1">
				<tr bgcolor="#8FBC8F">
					<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>
					<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td><td>操作</td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td><a href="#">添加</a></td>
				</tr>
			</table>
		</div>
	</div>
	<!--<div id="d5"></div>-->
</body>
</html>