<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<script type="text/javascript" src="Myjs/jquery-1.7.2.min.js"></script>
<body style="margin: 10px 30px">
		<form action="../LoginServlet" method="post">
			<table style="text-align: center;border: 3px solid green" >
				<caption>登录</caption>
				<tr>
					<td>用户编号:</td>
					<td><input type="text" id="userid"  name="userid" placeholder="请输入ID"></td>
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
			</table>
		</form>
</body>
</html>