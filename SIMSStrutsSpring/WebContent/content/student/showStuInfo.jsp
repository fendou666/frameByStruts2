<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
				<table class=" table-striped">
				<caption>个人信息一览</caption>
					<tr>
						<td class="left">学号：</td>	
						<td id="left">${sessionScope.stuInfo.id}</td>
					</tr>
					<tr>
						<td class="left">姓名：</td>	
						<td id="left">${sessionScope.stuInfo.name}</td>
					</tr>
					<tr>
						<td class="left">性别：</td>	
						<td id="left">${sessionScope.stuInfo.sex}
						</td>
					</tr>
					<tr>
						<td class="left">年龄：</td>	
						<td id="left">${sessionScope.stuInfo.age}</td>
					</tr>
					<tr>
						<td class="left">毕业院校：</td>	
						<td id="left">${sessionScope.stuInfo.gradFrom}</td>
					</tr>
					<tr>
						<td class="left">电话号码:</td>
						<td id="left">${sessionScope.stuInfo.tel}</td>
					</tr>
					<tr>
						<td class="left">地址：</td>	
						<td id="left">${sessionScope.stuInfo.addr}</td>
					</tr>
					<tr>
						<td class="right">身份证号码：</td>	
						<td id="left">${sessionScope.stuInfo.idCard}</td>
					</tr>
					<tr>
						<td class="left">邮箱：</td>
						<td id="left">${sessionScope.stuInfo.email}</td>
					</tr>
					<tr>
						<td class="left">组ID：</td>	
						
						<c:choose>
							<c:when test="${empty sessionScope.stuInfo.teamID}">
								<td id="left">无</td>
							</c:when>
							<c:otherwise>
								<td id="left">${sessionScope.stuInfo.teamID}</td>
							</c:otherwise>

						</c:choose>
						
					</tr>
					<tr>
						<td class="left">班ID：</td>	
						<c:choose>
							<c:when test="${empty sessionScope.stuInfo.classID}">
								<td id="left">无</td>
							</c:when>
							<c:otherwise>
								<td id="left">${sessionScope.stuInfo.classID}</td>
							</c:otherwise>
						</c:choose>
						
					</tr>
					<tr>
						<td colspan="2">
							<a href="${pageScope.path}/content/student/updateStuInfo.jsp">
							<input type="button" id="update" name="update"value="修改" />
							</a>	
						</td>
					</tr>
				</table>
	 			</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>

</html>