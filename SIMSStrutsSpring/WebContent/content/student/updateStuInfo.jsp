<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		String path = request.getContextPath();
		pageContext.setAttribute("path", path);
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="${pageScope.path}/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<script src="${pageScope.path}/js/main.js"></script>
		<style type="text/css">
		.left{
			text-align: left;
		}
		#left{
			text-align: left;
		}
	</style>
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
				<ul class="inner_navig">
					<li class="inner_navig"  id="li_5"><a class="btn btn-primary" href="${pageScope.path}/content/student/showStuInfo.jsp">个人信息展示</a></li>
					<li class="inner_navig"  id="li_6"><a class="btn btn-primary" href="${pageScope.path}/content/student/updateStuInfo.jsp">个人信息修改</a></li>
					<li class="inner_navig"  id="li_7"><a class="btn btn-primary" href="${pageScope.path}/content/student/mod_pwd.jsp">密码修改</a></li>
				</ul>
			</div>
			<div id="show_info"  align="center">
			<form method="post" action="update">
				<table style="text-align: center;" class=" table-striped">
				<caption>个人信息修改</caption>
					<tr>
						<td class="right">学号：</td>
						<td id="left">
							<input type="text" id="stuid" name="stuid"placeholder="请输入学号" value="${sessionScope.stuInfo.id}" />
						</td>
					</tr>
					<tr>
						<td class="right">姓名：</td>
						<td id="left">
							<input type="text" id="stuname" name="stuname"placeholder="请输入姓名" value="${sessionScope.stuInfo.name}"/>
						</td>
					</tr>
					<tr>
						<td class="right">性别：</td>
						<td id="left">
							<input type="radio" id="stusex" name="stusex" value="0" value="${sessionScope.stuInfo.sex}"checked="checked"/>男
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" id="stusex" name="stusex" value="1" value="${sessionScope.stuInfo.sex}"/>女
						</td>
					</tr>
					<tr>
						<td class="right">年龄：</td>
						<td id="left">
							<input type="number" id="stuage" name="stuage"placeholder="请输入年龄"value="${sessionScope.stuInfo.age}" />
						</td>
					</tr>
					<tr>
						<td class="right">毕业院校：</td>
						<td id="left">
							<input type="text" id="stugradFrom" name="stugradFrom"placeholder="请输入毕业院校" value="${sessionScope.stuInfo.gradFrom}" />
						</td>
					</tr>
					<tr>
						<td class="right">电话号码：</td>
						<td id="left">
							<input type="text" id="stutel" name="stutel"placeholder="请输入电话号码" value="${sessionScope.stuInfo.tel}" />
						</td>
					</tr>
					<tr>
						<td class="right">地址：</td>
						<td id="left">
							<input type="text" id="stuaddr" name="stuaddr"placeholder="请输入地址" value="${sessionScope.stuInfo.addr}" />
						</td>
					</tr>
					<tr>
						<td class="right">身份证号码：</td>
						<td id="left">
							<input type="text" id="stuidCard" name="stuidCard"placeholder="请输入身份证号码" value="${sessionScope.stuInfo.idCard}"/>
						</td>
					</tr>
					<tr>
						<td class="right">邮箱：</td>
						<td id="left">
							<input type="text" id="stuemail" name="stuemail"placeholder="请输入邮箱" value="${sessionScope.stuInfo.email}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" id="save" name="save"value="保存"/>
						</td>
					</tr>
				</table>
			</form>
	</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>

</html>