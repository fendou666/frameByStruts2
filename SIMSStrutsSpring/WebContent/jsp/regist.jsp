<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE ">
<html>
<head>
	<%
		String path = request.getContextPath();  //取到绝对根目录
		pageContext.setAttribute("path", path);
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>注册页面</title>
      	<link rel="stylesheet" href="${pageScope.path}/css/bg.css"/>
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/login.css" />
		<script src="js/login.js"></script>
</head>
<body style="text-align: center;">
		
		<div id="layer1" >
    		<img src="${pageScope.path}/img/bg.jpg" width="100%" height="100%"/>
    	</div>
	<form action="regist" method="post">
		<table style="margin: 30px auto;text-align: center;">
	         <caption style="font-size:300%;padding: 30px;">注册新学员</caption>
			<tr>
				<td  class="left_td">用户名:</td><td class="right_td"><input type="text" id="userId" name="userId" placeholder="请输入用户名"></td>
			</tr>
			<tr>
				<td  class="left_td">密码:</td><td class="right_td"><input type="password" id="pwd" name="pwd" placeholder="请输入密码"></td>
			</tr>	
			<tr>
				<td  class="left_td">姓名:</td><td class="right_td"><input type="text" id="name" name="name" placeholder="请输入姓名"></td>
			</tr>
			<tr>
				<td  class="left_td">性别:</td>
				<td class="right_td">
					男<input type="radio" id="sex" name="sex" value="男">
					&nbsp;&nbsp;&nbsp;&nbsp;
				 	女<input type="radio" id="sex" name="sex" value="女">
				</td>
			</tr>	
			<tr>
				<td  class="left_td">年龄:</td><td class="right_td"><input type="number" id="age" name="age" placeholder="请输入年龄"></td>
			</tr>
			<tr>
				<td  class="left_td">毕业院校:</td><td class="right_td"><input type="text" id="gradFrom" name="gradFrom" placeholder="请输入毕业院校"></td>
			</tr>	
			<tr>
				<td  class="left_td">电话号码:</td><td class="right_td"><input type="number" id="tel" name="tel" placeholder="请输入电话"></td>
			</tr>	
			<tr>
				<td  class="left_td">地址:</td><td class="right_td"><input type="text" id="addr" name="addr" placeholder="请输入地址"></td>
			</tr>	
			<tr>
				<td  class="left_td">身份证:</td><td class="right_td"><input type="text" id="idCard" name="idCard" placeholder="请输入身份证号码"></td>
			</tr>	
			<tr>
				<td  class="left_td">邮箱:</td><td class="right_td"><input type="text" id="email" name="email" placeholder="请输入邮箱"></td>
			</tr>	
			<tr>
				<td colspan="2">
					<input type="submit" value="提交" style="width: 80px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置"  style="width: 80px;">
					<a href="Login.jsp">返回登录页面</a>
				</td>
			</tr>
			<c:if test="${not empty requestScope.registErr}">
					<tr>
						<td colspan="2"><div id="Err" style="color: red;">${requestScope.registErr}</div></td>
						
					</tr>
				</c:if>
		</table>
		
	</form>
</body>
</html>