<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <title>SeSky用户管理信息系统</title>
        <meta name="description" content="Examples for creative website header animations using Canvas and JavaScript" />
        <meta name="keywords" content="header, canvas, animated, creative, inspiration, javascript" />
        <meta name="author" content="Codrops" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <!-- <link href='http://fonts.googleapis.com/css?family=Raleway:200,400,800|Clicker+Script' rel='stylesheet' type='text/css'> -->
        <link rel="stylesheet" href="http://www.yyyweb.com/demo/common/init.css">
        <!--[if IE]>
        	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script type="text/javascript" src="js/jquery-1.6.min.js" charset="utf-8"></script>
		<script type="text/javascript" src="js/jquery.backgroundPosition.fixed.js" charset="utf-8"></script>
		<script type="text/javascript" src="js/menuAnimation2.js" charset="utf-8"></script>
	</head>
	<body>
		<div class="container demo-2">
			<div class="content">
                <div id="large-header" class="large-header">
                    <canvas id="demo-canvas"></canvas>
                    <div class="main-title">
                    	<div class="main">
							<h1>SeSky用户管理信息系统</h1>
	                    </div>
						<div class="context">
							<div class="con_left">
								<table id="menu" style="position: absolute;">
									<tr>
										<td><a href="Mon_monitor.jsp"><span class="menu_con">用户管理</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 考勤管理</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 请假管理</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 考试管理</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 活动管理</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 奖罚管理</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 资源共享</span></a></td>
									</tr>
									<tr>
										<td><a href="success.jsp"><span class="menu_con"> 留言板</span></a></td>
									</tr>
									<tr>
										<td><a href="loginout.jsp"><span class="menu_con"> 注销/退出</span></a></td>
									</tr>
								</table>
							</div>
							<div class="con_right">
								<div class="con_rmenu">
									欢迎${sessionScope.user.name}登陆SeSky系统，您的id为${sessionScope.user.id}<br>								
								</div><hr>
								<div class="con_rcon">自己的内容画面</div>
							</div>
						</div>
						<div class="footer">&copy; 2017 海阔天空 . All rights reserved.</div>
					</div>
                </div>
            </div>
		</div><!-- /container -->
        <div style="position:fixed;height:90px;width:100%;left:0;bottom:5px;z-index:999"><div class="footer-banner" style="width:728px; margin:0px auto"></div></div>           
        <script src="js/rAF.js"></script>
        <script src="js/demo-2.js"></script>
	</body>
</html>