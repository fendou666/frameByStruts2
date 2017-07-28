<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <title>班长界面</title>
        <meta name="description" content="Examples for creative website header animations using Canvas and JavaScript" />
        <meta name="keywords" content="header, canvas, animated, creative, inspiration, javascript" />
        <meta name="author" content="Codrops" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <link rel="stylesheet" type="text/css" href="css/monitor.css"> 
        <link rel="stylesheet" href="http://www.yyyweb.com/demo/common/init.css">
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
										<td><a><span class="menu_con"> 考勤管理</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 请假管理</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 考试管理</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 活动管理</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 奖罚管理</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 资源共享</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 留言板</span></a></td>
									</tr>
									<tr>
										<td><a><span class="menu_con"> 注销/退出</span></a></td>
									</tr>
								</table>
							</div>

							<div class="con_right" style="margin-left: 205px;">
								<div style="float: right;font-size: 20px;">当前用户：${sessionScope.user.name}</div>
								<div class="con_rmenu" id="nav">用户管理>>班长界面</div>
								<hr>
								<ul class="mcon_ul" style="overflow: hidden;text-align: center;">
									<li><a><img src="img/renwu.png"><br>个人信息</a></li>
									<li><a href="action=classInf&classId=${sessionScope.user.classId}&headmasterId=${sessionScope.user.headmasterId}&teacherId=${sessionScope.user.teacherId}"><img src="img/renwu.png"><br>班级成员</a></li>
									<li><a href="action=classDiv&classId=${sessionScope.user.classId}&headmasterId=${sessionScope.user.headmasterId}&teacherId=${sessionScope.user.teacherId}"><img src="img/renwu.png"><br>指定组长</a></li>
									<li><a href="action=classDivPeo&classId=${sessionScope.user.classId}&headmasterId=${sessionScope.user.headmasterId}&teacherId=${sessionScope.user.teacherId}"><img src="img/renwu.png"><br>分配组员</a></li>
									<li style="margin-left: 25%;margin-top: -270px;"><a><img src="img/renwu.png"><br>分组情况</a></li>
								</ul>
							</div>
						</div>
						<div class="footer">&copy; 2017 海阔天空 . All rights reserved.</div>
					</div>
                </div>
            </div>
		</div>
        <script src="js/rAF.js"></script>
        <script src="js/demo-2.js"></script>
	</body>
</html>