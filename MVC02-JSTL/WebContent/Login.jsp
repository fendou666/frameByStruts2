<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body style="margin: 10px 30px">
    xie wb values::${param.xiewb}
	<form action="LoginServlet" method="post">
		<table style="text-align: center;border: 3px solid green" >
			<caption>登录</caption>
			<tr>
				<td>用户编号:</td>
				<td><input type="text" id="userid" name="userid" placeholder="请输入ID"></td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
				<td><input type="password" id="pwd" name="pwd"  placeholder="请输入密码"></td>
			</tr>
			<tr>
				<td colspan="2" id="td_btn">
					<input type="submit" value="登录">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置">
				</td>
			</tr>
			<c:if test="${not empty requestScope.err}">
				<tr>
				  <td colspan="2" id="td_btn"><div id="msg" style="color:red">${requestScope.err}</div></td>
				</tr>
			</c:if>
		</table>
	</form>
</body>
</html>