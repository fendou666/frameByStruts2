<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript" src="Myjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			$.get(
				   "LoginAjaxAction",
				   {
						userid:$("#userid").val(),
						pwd:$("#pwd").val()
					},
				   function(data){
					   	var errMsg=<%=application.getAttribute("errorMsg")%>;
					   	console.log("action msg 信息" +errMsg);
					   	if(!(errMsg==null || errMsg=="")){
							$("#msg").text(errMsg);
						}
						console.log(<%=session.getAttribute("errorMsg")%>);
						$("#msg").text(<%=session.getAttribute("errorMsg")%>);
				   }
				);
		});
	});
</script>
</head>
<body style="margin: 10px 30px">
	<form action="" name="myfm" method="post">
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
					<input type="button" id="login" value="登录">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="重置" onclick="myfm.reset()">
				</td>
			</tr>
			<tr>
			  <td colspan="2"><div id="msg" style="color:red"></div></td>
			</tr>
		</table>
	</form>
</body>
</html>