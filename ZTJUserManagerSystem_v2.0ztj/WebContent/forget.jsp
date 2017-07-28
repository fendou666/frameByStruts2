<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>SeSky用户密码找回</title>
		<meta name="description" content="Examples for creative website header animations using Canvas and JavaScript" />
		<meta name="keywords" content="header, canvas, animated, creative, inspiration, javascript" />
		<meta name="author" content="Codrops" />
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<link rel="stylesheet" href="http://www.yyyweb.com/demo/common/init.css">
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
						<form action="ForgetServlet" method="post">
							<table align="center" style="border:3px solid green;font-size:30px">
									<%
										String errMsg=(String)request.getAttribute("err");
										System.out.print("---------------------");
										System.out.print("忘记密码页面收到错误信息："+errMsg+"\n");
										System.out.print("---------------------");
										if(errMsg!=null){
											if(!("").equals(errMsg)){
												out.write("<span style='color: red;'>警告："+errMsg+"</span>");
												session.removeAttribute("err");
											}
										}
									%>
									<%	
										String text=(String)request.getAttribute("text");
										System.out.print("---------------------");
										System.out.print("忘记密码页面收到提示信息："+errMsg+"\n");
										System.out.print("---------------------");
										if(text!=null){
											if(!("").equals(text)){
												out.write("<span style='color: green;'>"+text+"</span>");
												session.removeAttribute("text");
											}
										}
									%>	
									<tr><th colspan="2" style="font-size:50px">密码找回</th></tr>
									<tr>
										<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:</td>
										<td><input type="text" name="name" id="name"  style="background: transparent;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:</td>
										<td><input type="text" name="userId" id="userId"  style="background: transparent;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td>手&nbsp;机&nbsp;号&nbsp;:</td>
										<td><input type="text" name="phone" id="phone"  style="background: transparent;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td>新&nbsp;密&nbsp;码&nbsp;:</td>
										<td><input type="password" name="pwd" id="pwd"  style="background: transparent;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td>确认密码:</td>
										<td><input type="password" name="pwdgain" id="pwdgain"  style="background: transparent;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td>验&nbsp;证&nbsp;码&nbsp;:</td>
										<td><input type="text" name="ident" id="ident" placeholder="请输入图中验证码" style="background: transparent;z-index: 1" />
										<img alt="验证码图" src="ImgServlet" id="img" name="img" onclick="imgController()"></td>
									</tr>
									<tr>
										<td colspan="2" style="text-align: center;">
											<input type="submit" name="submit" id="submit" value="提交" />
											<a href="index.jsp">已有账号</a>
										</td>
									</tr>
								</table>
						</form>	
					</h6>
				</div>				
			</div>
		</div><!-- /container -->
		<div style="position:fixed;height:90px;width:100%;left:0;bottom:5px;"><div class="footer-banner" style="width:728px; margin:0px auto"></div></div>		
		<script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>		
	</body>
</html>