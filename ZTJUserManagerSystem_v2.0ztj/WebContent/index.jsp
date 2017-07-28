<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>SeSky学员系统</title>
		<meta name="description" content="Examples for creative website header animations using Canvas and JavaScript" />
		<meta name="keywords" content="header, canvas, animated, creative, inspiration, javascript" />
		<meta name="author" content="Codrops" />
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<link rel="stylesheet" href="http://www.yyyweb.com/demo/common/init.css">
		<script type="text/javascript">
			<%
				String text=(String)request.getSession().getAttribute("text");
				System.out.print("---------------------");
				System.out.print("登录界面收到修改信息："+text);
				System.out.print("---------------------");
				if(text!=null){
					if(!("").equals(text)){
						%>
						alert("<%=text%>");
						<%
						session.removeAttribute("text");
					}
				}
			%>
		</script>
		<script type="text/javascript">
			function imgController(){
				document.getElementById("img").src = "ImgServlet?rand="+Math.random();
			}
		</script>
	</head>
	<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<h6 class="main-title">
						<form action="com.seasky.managersys.controller.LogonServlet" method="post">
							<table align="center" style="border:3px solid green;font-size:30px">
									<%
										String errMsg=(String)request.getAttribute("err");
										System.out.print("登录界面收到错误信息："+errMsg);
										if(errMsg!=null){
											if(!("").equals(errMsg)){
												out.write("<span style='color: red;'>警告："+errMsg+"</span>");
												session.removeAttribute("err");
											}
										}
									%>
									<%-- <%request.getSession().removeAttribute("user");%> --%>
									<tr><th colspan="2" style="font-size:50px">欢迎登陆SeSky学员系统</th></tr>
									<tr>
										<td>用户名:</td>
										<td><input type="text" placeholder="学号/手机号" name="user" id="user"  style="background: transparent;" /><a href="regist.jsp">用户注册</a></td>
									</tr>
									<tr>
										<td>密&nbsp;&nbsp;&nbsp;码:</td>
										<td><input type="password" name="pwd" id="pwd"  style="background: transparent;" /><a href="forget.jsp">忘记密码</a></td>
									</tr>
									<tr>
										<td>验证码:&nbsp;&nbsp;</td>
										<td><input type="text" name="ident" id="ident" placeholder="请输入图中验证码" style="background: transparent;z-index: 1" />
										&nbsp;&nbsp;
										<img alt="验证码图" src="ImgServlet" id="img" name="img" onclick="imgController()">&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" style="text-align: center;">
											<input type="submit" name="submit" id="submit" value="提交" />
											<input type="reset" name="reset" id="reset" value="重置" />
										</td>
									</tr>
								</table>
						</form>	
					</h6>
				</div>				
			</div>
		</div><!-- /container -->
		<script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>		
	</body>
</html>