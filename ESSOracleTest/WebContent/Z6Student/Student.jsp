<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="../css/add.css" rel="stylesheet" type="text/css"/>
<link id="mycss" href="css/table.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var user={}
	user.$=function(id){
		return document.getElementById(id);
	}
	function updateData(){
		user.$("ptel").style.display="none";
		user.$("intel").style.display="inline";
		user.$("pemail").style.display="none";
		user.$("inemail").style.display="inline";
		user.$("upd").style.display="none";
		user.$("save").style.display="inline";
		
	}
	function queryData(){
		user.$("t421").style.display="inline";
	}
</script>

<script>
	function personalInfo(forword){
		var path =  "${pageContext.request.contextPath}/StudentUpdateServlet?oper="+forword;
		var reqData	= {}
		if (forword === "updData"){
			console.log("执行更新 发送的数据");
			reqData = {
						telephone:$("#intel").val(),
						email:$("#inemail").val()
					  };
		}
		console.log("执行 personalInfo " + forword);
		$.post(
					path,
					reqData,
					function(data){
						var info="";
						var user=eval(data);
						info = "<form name=\"myfm\">";
						info += "<table id=\"t1\">";
						$.each(user,function(i){
							if(user[i].id != ""){ 
								info += "<tr><td class=\"td1\">姓名：</td><td>"+user[i].name+"</td></tr>";
								info += "<tr><td>学号：</td><td>"+user[i].id+"</td></tr>";
								info += "<tr><td>性别：</td><td>"+user[i].sex+"</td></tr>";
								info += "<tr><td>出生日期：</td><td>"+user[i].birthday+"</td></tr>";
								info += "<tr><td>手机号码：</td><td><p id=\"ptel\" name=\"tel\">"+user[i].telephone+"</p>";
								info += "<input type=\"text\" id=\"intel\" name=\"tel\" value=\""+user[i].telephone+"\" style=\"display:none\"></td></tr>";
								info += "<tr><td>邮箱：</td><td><p id=\"pemail\" name=\"email\">"+user[i].email+"</p>";
								info += "<input type=\"text\" id=\"inemail\" name=\"email\" value=\""+user[i].email+"\" style=\"display:none\"></td></tr>";
								info += "<tr><td>所在班级：</td><td>"+user[i].classId+"</td></tr>";
								if(forword == "show"){
									info += "<tr><td><input type=\"button\" id=\"upd\" value=\"修改\" onclick=\"return updateData()\">";
									info += "<input type=\"button\" id=\"save\"  value=\"保存\" style=\"display:none\" onclick=\"personalInfo('updData')\" ></td>";
									info += "<td><input type=\"button\" value=\"重置\" onclick=\"myfm.reset()\"></td></tr>";
								}
							}else{
								info="操作有误";
							}
						});
						info+="</table>"; 
						info+="</form>"; 
						$("#d41").html("个人信息修改")
						$("#d42").html(info);
						console.log("保存按钮value" + document.getElementById("save").value);
					},
					"JSON"
			)
			
	}
	
	function getQueryData(forword){
		<%-- var studentQuerys = "<%=request.getContextPath() %>/StudentQueryServlet?action="+forword;  --%>
		var urlPath = "${pageContext.request.contextPath}/StudentQueryServlet?action="+forword;
		var requestObj = "";
		document.getElementById("t421").style.display="inline";
		if(forword == "conditions"){
			requestObj = {
							eecId:$("#eecId").val(),
							eecName:$("#eecName").val(),
							roleName:$("#roleName").val()
						 };
		}
		$.post(
				urlPath,
				requestObj,	
				function(data){
					var info="";
					var user=eval(data);
					info="<table id=\"t2\" border=\"1\">";
					info+="<tr bgcolor=\"#8FBC8F\">";
					info+="<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>";
					info+="<td>出生日期</td><td>手机号</td><td>邮箱</td><td>职务</td>";
					info+="</tr>";
					$.each(user,function(i){
						if(user[i].id != ""){ 
							info+="<tr><td>"+(i+1)+"</td>";
							info+="<td>"+user[i].classId+"</td>";
							info+="<td>"+user[i].id+"</td>";
							info+="<td>"+user[i].name+"</td>";
							info+="<td>"+user[i].sex+"</td>";
							info+="<td>"+user[i].birthday+"</td>";
							info+="<td>"+user[i].telephone+"</td>";
							info+="<td>"+user[i].email+"</td>";
							info+="<td>"+user[i].roleName+"</td></tr>";
						}else{
							info+="<tr><td colspan=\"9\">未找到符合条件的数据</td></tr>";
						}
					});
					info+="</table>"; 
					$("#d41").html("班级信息查询")
					$("#d422").html(info);
					
				},
				"JSON"
			)
			
	}
	
	$(function(){
		$("#d31").click(function(){
			personalInfo("show");
		});
		/* $("#save").click(function(){
			console.log("save  111111111");
			personalInfo("updData");
		}); */
		$("#d32").click(function(){
			getQueryData("classId");
		});
		$("#cond").click(function(){
			getQueryData("conditions");
		});
	})
</script>
</head>
<body bgcolor="#F5FFFA">
	<div id="d0">
		<h1>学生信息综合管理系统</h1>
	</div>
	<div id="d1">
		<div id="d2">&nbsp;&nbsp;&nbsp;当前用户:学生
		<div id="d21">
			<a href="#">首页</a>&nbsp;&nbsp;&nbsp;
			<a href="#">退出登录</a>
		</div>
		</div>
		<div id="d3">
			<div id="d31">个人基本信息管理</div>
			<div id="d32">班级信息查询</div>
		</div>
		<div id="d4">
			<div id="d41">个人资料展示</div>
			<div id="d42">
				<table id="t421" style="display:none;">
					<tr>
						<td>&nbsp;&nbsp;学号：<input type="text" id="eecId" name="eecId" value=""></td>
						<td>&nbsp;&nbsp;姓名：<input type="text" id="eecName" name="eecName" value=""></td>
						<td>&nbsp;&nbsp;职务：
							<select id="roleName">
								<option></option>
								<option>学生</option>
								<option>组长</option>
								<option>班长</option>
								<option>任课老师</option>
								<option>班主任</option>
							</select>
						</td>
						<td>&nbsp;&nbsp;<input type="button" id="cond" value="查 询"></td>
					</tr>
				</table>
				<div id="d422"> 
					<form name="myfm">
						<c:if test="${not empty sessionScope.user}">
							<table id="t1">
								<tr>
									<td class="td1">姓名：</td>
									<td>${sessionScope.user.name}</td>
								</tr>
								<tr>
									<td>学号：</td>
									<td>${sessionScope.user.id}</td>
								</tr>
								<tr>
									<td>性别：</td>
									<td>
										<c:if test="${sessionScope.user.sex eq '男'}">
										<input type="radio" name="sex" value="男" checked="checked">男
										<input type="radio" name="sex" value="女">女
										</c:if>
										<c:if test="${sessionScope.user.sex eq '女'}">
										<input type="radio" name="sex" value="男">男
										<input type="radio" name="sex" value="女" checked="checked">女
										</c:if>
									</td>
								</tr>
								<tr>
									<td>出生日期：</td>
									<td>${sessionScope.user.birthday}</td>
								</tr>
								<tr>
									<td>手机号码：</td>
									<td>
										<p id="ptel" name="tel">${sessionScope.user.telephone}</p>
										<input type="text" id="intel" name="tel" value="${sessionScope.user.telephone}" style="display:none">
									</td>
								</tr>
								<tr>
									<td>email：</td>
									<td>
										<p id="pemail" name="email">${sessionScope.user.email}</p>
										<input type="text" id="inemail" name="email" value="${sessionScope.user.email}" style="display:none">
									</td>
								</tr>
								<tr>
									<td>所在班级：</td>
									<td>${sessionScope.user.classId}</td>
								</tr>
								<tr>
									<td>
										<input type="button" id="upd" value="修改" onclick="return updateData()">
										<input style="display:none" type="button" id="save" value="保存">
									</td>
									<td><input type="button" value="重置" onclick="myfm.reset()"></td>
								</tr>
							</table>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>