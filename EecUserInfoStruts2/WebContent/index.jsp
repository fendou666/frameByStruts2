<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function loginAuthentication(){
		var errorInfo = "";
		$.post(
			"userInfoCheck",
			{
				id:$("#userName").val(),
				pwd:$("#pwd").val()
			},
			function(data){
				if(data=="false"){
					errorInfo = "参数不能为空";
				}
				if(data=="userOrPwdfalse"){
					errorInfo = "用户名或者密码错误";
				}
				/* 这里不应该有因为成功的时候action就帮你调用了, 总结页面跳转全部由action做了 */
				  
				if(data=="success"){
					//location.href="jsp/Z6Admin/Admin.jsp";
					console.log("请求成功返回");
				} 
				$("#errMsg").text=errorInfo;
			}
		)
	}
	/* $(function(){
		$("#subB").click(function(){
			loginAuthentication();
		})
	})
	 */
</script>

<style type="text/css">
#errMsg {
	color:red;
}
</style>
<body>
	<form action="userInfoCheck" method="post" id="loginFM">
		<table>
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" id="userName" name="userName"/>
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<input type="password" id="pwd" name="pwd">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="subB" value="登陆">
					<input type="button" value="重置" onclick="loginFM.reset()"> 
				</td>
			</tr>
		</table>
	</form>
	<div id="errMsg"></div>
</body>
</html>