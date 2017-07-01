<%@page import="com.chinasofti.eecuser.model.javabean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="css/add.css" rel="stylesheet" type="text/css"/> 
</head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function getCustomPageIndex(){
		// TODO 
		//return $('pageIndex').val(); // 为什么这种不可以
		return document.getElementById("pageIndex").value;
	}
	
	function showInfo(){
		console.log("roleId :" + $("#roleId").val());
		console.log("classId :" + $("#classId").val());
		console.log("eecId :" + $("#eecId").val());
		console.log("eecName :" + $("#eecName").val());
	}
	function showHtml(id){
		console.log(document.getElementById(id).innerHTML);
	}
	
	function getEecUserInfo(forward){
		showInfo();
		$.post(
				"<%=request.getContextPath()%>/AdminServlet",				
				{
					action:forward,
					roleId:$("#roleId").val(),
					classId:$("#classId").val(),
					eecId:$("#eecId").val(),
					eecName:$("#eecName").val()
				},
				function(data){
					//console.log("数据解析支持成功");
					var eecUsers = eval(data);
					var htmlData = "";
					htmlData +=	'<tr bgcolor="#8FBC8F">';
					htmlData += '<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>';
					htmlData += '<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td><td colspan="3">操作</td>';
					htmlData += '</tr>';
					if(eecUsers == null || eecUsers.length == 0){
						htmlData += "<tr >";
						htmlData += '<td colspan="12">没有获取到数据</td>';
						htmlData += "</tr>";
					}else{
						
						$.each(eecUsers ,function(i){
							htmlData += "<tr id=\"usd" + (i+1) + "\">";
							htmlData += "<td>" + (i+1)  +"</td>";
							htmlData += "<td>" + eecUsers[i].classId  +"</td>";
							htmlData += "<td>" + eecUsers[i].id  +"</td>";
							htmlData += "<td>" + eecUsers[i].name  +"</td>";
							htmlData += "<td>" + eecUsers[i].sex  +"</td>";
							htmlData += "<td>" + eecUsers[i].age  +"</td>";
							htmlData += "<td>" + eecUsers[i].email  +"</td>";
							htmlData += "<td>" + eecUsers[i].telephone  +"</td>";
							htmlData += "<td>" + eecUsers[i].roleName  +"</td>";
							//htmlData += '<td><a href="#" onclick="showHtml(\'usd' +(i+1)+ '\')">增加</a></td>';
							htmlData += '<td><a href="#" onclick="showHtml(\'t2\')">增加</a></td>';
							//htmlData += "<td><a href=\"<%=request.getContextPath() %>/OracleOperationServlet?action=seachById&id="+ eecUsers[i].id +"\">增加</a></td>";
							htmlData += "<td><a href=\"<%=request.getContextPath() %>/OracleOperationServlet?action=seachById&id="+ eecUsers[i].id +"\">修改</a></td>";
							htmlData += "<td><a href=\"<%=request.getContextPath() %>/OracleOperationServlet?action=del&delNum="+ eecUsers[i].id +"\">删除</a></td>";
							htmlData += "</tr>";
						})
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getStuInfo(\'first\')" value="首页" ></td>';
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getStuInfo(\'pre\')" value="前页" ></td>';
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getStuInfo(\'next\')" value="次页" ></td>';
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getStuInfo(\'last\')" value="尾页" ></td>';
						htmlData +=  '<td colspan="2"><input type="number" id="pageIndex">';
						htmlData +=  '<input type="button" onclick="getEecUserInfo(getCustomPageIndex())" value="指定页" ></td>';
					}
					$("#t2").html(htmlData);
				},
				"JSON"
			);
	}

	$(function(){
		$("#query").click(function(){
			getEecUserInfo("getQuery");
		})
	})
</script>
<body>
	<%-- <%
		UserInfo stu = (UserInfo)session.getAttribute("userInfo");
		if(stu==null){
			request.getRequestDispatcher("NoLogin.jsp").forward(request, response);
			return;
		}
	%> --%>
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
			<div id="d31"><a href="#">个人信息</a></div>
			<div id="d32"><a href="#">增加管理1</a></div>
			<div id="d33"><a href="#">增加管理2</a></div>
			<div id="d34"><a href="#">增加管理3</a></div>
			<div id="d35"><a href="#">增加管理4</a></div>
			<div id="d36"><a href="#">增加管理5</a></div>
		</div>
		<div id="d4">
			<div id="d41">学生信息查询</div>
			<table id="t1" >
				<tr>
					<td>职位：
						<select name="roleId" id="roleId">
							<option value="postAll">全部</option>
							<option value="3004">任课老师</option>
							<option value="3005">班主任</option>
						</select>
					</td>
					<td>班级：
						<select name="classId" id="classId">
							<option value="classAll">全部</option>
							<option value="20170207">java0207</option>
							<option value="20170208">java0208</option>
						</select>
					</td>
					<td>&nbsp;&nbsp;学号：<input type="text"  name="eecId" value=""></td>
					<td>&nbsp;&nbsp;姓名：<input type="text" name="eecName" value=""></td>
					<td>&nbsp;&nbsp;<input type="button" id="query" name="query" value="查 询"></td>
				</tr>
			</table>
			<table id="t2" border="1">
				<tr bgcolor="#8FBC8F">
					<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>
					<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td><td colspan="3">操作</td>
				</tr>
				<c:forEach items="">
				</c:forEach>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr>
				<!-- <tr id="usd3">
					<td>3</td>
					<td><input type="text" name="usd3classId" id="usd3classId"></td>
					<td><input type="text" name="usd3id" id="usd3id"></td>
					<td><input type="text" name="usd3name" id="usd3name"></td>
					<td><input type="text" name="usd3sex" id="usd3sex"></td>
					<td><input type="text" name="usd3age" id="usd3age"></td>
					<td><input type="text" name="usd3email" id="usd3email"></td>
					<td><input type="text" name="usd3telephone" id="usd3telephone"></td>
					<td><input type="text" name="usd3roleName" id="usd3roleName"></td>
					<td><a href="#">添加</a></td>
					<td><a href="#">删除</a></td>
					<td><a href="#">修改</a></td>
				</tr> -->
			</table>
		</div>
	</div>
	
	
</body>
</html>