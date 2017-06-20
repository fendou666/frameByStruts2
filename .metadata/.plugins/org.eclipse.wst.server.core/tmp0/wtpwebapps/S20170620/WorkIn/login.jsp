<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body>
	<div>
		<s:set name="xx" value="6666"/><br>
 		xx:<s:property value="#xx"/><br>
 		<hr>
 		para-uname:<s:property value="#parameters.uname"/><br>
 		2+3:<s:property value="'2+3'"/><br>
 		2+3:<s:property value="2+3"/><br>
 		非成员action:<s:property value="action"/><br>
 		gvstack:<s:property value="gvs"/><br>
 		#req:<s:property value="#request.req"/><br>
 		#sess:<s:property value="#session.sess"/><br>
 		#app:<s:property value="#application.app"/><br>
 		
  		<s:debug></s:debug>
  	</div>
  <p>用户登录</p> 
  <form action="login.action" method="post">
	  <table>
	  	<tr>
	  		<td>用户名:</td>
	  		<td><input type="text" id="id" name="id"/></td>
	  	</tr>
	  	<tr>
	  		<td>密&nbsp;&nbsp;&nbsp;码:</td>
	  		<td><input type="password" id="pwd" name="pwd"/></td>
	  	</tr>
	  	<tr>
	  		<td><input type="submit" value="登录"/></td>
	  	</tr>
	  	<tr>
	  		<td><a href="#">注册</a></td>
	  		<td><a href="#">忘记密码</a></td>
	  	</tr>
	  	</table>
  </form>
 