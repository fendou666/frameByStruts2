<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<style type="text/css">
	body{
		font-size: 26px;
	}
</style>
<script type="text/javascript" src="Myjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var login = {};
	login.$1 = function(id){
		return document.getElementById(id);
	}
	login.checkNull = function(ele,errorMsg){
		if(ele.value==null||ele.value==""){
			login.$1("msg").innerText = errorMsg;
			return false;
		}
		return true;
	}
	login.checkAll = function(){
		if(!login.checkNull(login.$1("userid"),"用户名不能为空")){
			return false;
		}
		if(!login.checkNull(login.$1("pwd"),"密码不能为空")){
			return false;
		}
		return true;
	}
</script>
</head>
<body style="margin: 10px 30px">
	<s:actionerror/>
	<form action="login" name="myfm" method="post">
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
					<input type="submit" value="登录" onclick="return login.checkAll()">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置">
				</td>
			</tr>
			<tr>
			  <td colspan="2"><div id="msg" style="color:red"></div></td>
			</tr>
		</table>
	</form>
</body>
</html>