<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
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
		<script src="${pageScope.path}/js/mod_pwd.js" ></script>
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
				<form action="modpwd" method="post">
					<table class=" table-striped">
					<caption>密码修改</caption>
						<tr>
							<td>原密码：</td>
							<td><input type="password" name="old_pwd" id = "old_pwd" onfocus="hideMesg()"/></td>
						</tr>
						<tr>
							<td>新密码：</td>
							<td><input type="password" name="new_pwd" id = "new_pwd" onfocus="hideMesg()"/></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><input type="password" name="con_pwd" id = "con_pwd" onfocus="hideMesg()"/></td>
						</tr>
						<tr>
							<td colspan="2" id="submit"><input type="submit" value="提交" onclick="return checkEmpty()"/>
							&nbsp;&nbsp;<span style="color:red" id="mesg"><s:actionmessage/></span></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>

</html>