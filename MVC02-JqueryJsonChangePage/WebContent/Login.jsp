<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<script type="text/javascript">
	/* var login = {};
	var errorInfo = "";
	login.$=function(id){
		return document.getElementById(id);
	}
	login.checkData = function(elem, keyInfo){
		if(elem.value==null || elem.value==""){
			errorInfo += keyInfo + "不能为空";
			elem.focuse();
			return false;
		}
		return true;
	}
	login.checkDataAll = function(){
		login.$("msg").innerText = "";
		if(!login.checkData(login.$("userid"), "用户名") |
				!login.checkData(login.$("pwd"), "密码")
			){
			return false;
		}
		return true;
	}  */
</script>

<body style="margin: 10px 30px">
	<s:actionerror/>
	<form action="LoginAction" name="myfm" method="post">
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
					<!-- <input type="button" id="login" value="登录" onclick="return login.checkDataAll()"> -->
					<input type="submit" id="login" value="登录" >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="重置" onclick="myfm.reset()">
				</td>
			</tr>
			<tr>
			  <td colspan="2"><div id="msg" style="color:red"></div></td>
			</tr>
		</table>
	</form>
	<s:debug/>
</body>
</html>