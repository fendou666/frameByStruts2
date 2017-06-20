<%@page import="com.chinasofti.eecuser.model.javabean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link id="mycss" href="../css/add.css" rel="stylesheet" type="text/css"/> 
</head>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var THE_THEACHER_ROLEID = 3004;
	var HEAD_THEACHER_ROLEID = 3005;
	
	function getCustomPageIndex(){
		return document.getElementById("pageIndex").value;
	}
	
	function showInfo(){
		console.log("id :" + $("#id").val());
		console.log("name :" + $("#name").val());
	}
	function appointTheacher(eecId, classIdELM, 
			theTheacherELM,headTheacherELM,  trIdELM){
		console.log(classIdELM);
		console.log($(classIdELM).val());
		console.log("theTheacher check  ");
		console.log($(theTheacherELM).prop("checked"));
		console.log("headTheacher check  ");
		console.log($(headTheacherELM).prop("checked"));
		var theacherType;
		if($(theTheacherELM).prop("checked")){
			theacherType = $(theTheacherELM).val()
		}else{
			theacherType = $(headTheacherELM).val()
		}
		
		$.post(
				"<%=request.getContextPath()%>/AdminServlet",				
				{
					action:"addTheacherTrue",
					classId:$(classIdELM).val(),
					theacherType:theacherType,
					id:eecId
				},
				function(data){
					if(data=="success"){
						var classSelect = trIdELM + " td:eq(7)";
						var teacherTypeSelect = trIdELM + " td:eq(8)";
						$(classSelect).html($(classIdELM).val()); // 设定班级
						if(theacherType == THE_THEACHER_ROLEID){
							$(teacherTypeSelect).html("任课老师");
						}else{
							$(teacherTypeSelect).html("班主任");
						}
					}else{
						console.log("更新失败");
					}
				}
		);
	}
	
	function getEecUserInfo(forward){
		showInfo();
		$.post(
				"<%=request.getContextPath()%>/AdminServlet",				
				{
					action:"addTheacher",
					// 分页对象存储在session中的key， 本来考虑通过传参，锁定不同的分页对象，
					// 不同人需求不同，暂时先各自按自己key分页
					sqlPageHashMap:"addTeacherPage",  
					pageIndex: forward, // 页面跳转参数
					id:$("#id").val(),
					name:$("#name").val()
				},
				function(data){
					//console.log("数据解析支持成功");
					var eecUsers = eval(data);
					var htmlData = "";
					var classNamesHtml = "";
					htmlData +=	'<tr bgcolor="#8FBC8F">';
					htmlData += '<td>序号</td><td>学号</td><td>姓名</td><td>性别</td><td>年龄</td>';
					htmlData += '<td>邮箱</td><td>手机号</td><td>班级选择</td><td>操作</td>';
					htmlData += '</tr>';
					if(eecUsers == null || eecUsers.length == 0){
						htmlData += "<tr >";
						htmlData += '<td colspan="12">没有获取到数据</td>';
						htmlData += "</tr>";
					}else{
						$.each(eecUsers ,function(i){
							htmlData += "<tr id=\"usd" + (i+1) + "\">";
							htmlData += "<td>" + (i+1)  +"</td>";
							htmlData += "<td>" + eecUsers[i].id  +"</td>";
							htmlData += "<td>" + eecUsers[i].name  +"</td>";
							htmlData += "<td>" + eecUsers[i].sex  +"</td>";
							htmlData += "<td>" + eecUsers[i].age  +"</td>";
							htmlData += "<td>" + eecUsers[i].email  +"</td>";
							htmlData += "<td>" + eecUsers[i].telephone  +"</td>";
							if(i==0){
								for(var j=0; j<eecUsers[i].classNames.length;j++){
									classNamesHtml +='<option value="' + eecUsers[i].classNames[j] 
														+'">'+ eecUsers[i].classNames[j]  +'</option>';
								}
							}
							htmlData += '<td><select name="className" id="className' + (i+1) + '" >' + classNamesHtml + '</select></td>';
							htmlData += '<td><span>任课老师</span><input type="radio" id="theTheacher' + (i+1) + '" name="theacherType"  value="'
										+ THE_THEACHER_ROLEID +'"  /><span>班主任</span>'
										+ '<input type="radio" id="headTheacher' + (i+1) + '" name="theacherType"  value="'
										+ HEAD_THEACHER_ROLEID +'"  />'
										+ '<input type="button" value="确认选择" onclick="appointTheacher(' + eecUsers[i].id  
													+ ',\'#className' + (i+1)
													+ '\',\'#theTheacher' + (i+1)
													+ '\',\'#headTheacher' + (i+1)
													+ '\',\'#usd' + (i+1) + '\')" ></td>';
							htmlData += "</tr>";
						})
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\'first\')" value="首页" ></td>';
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\'pre\')" value="前页" ></td>';
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\'next\')" value="次页" ></td>';
						htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\'last\')" value="尾页" ></td>';
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
			getEecUserInfo("first");
		})
	})
</script>

<body>
	<%
		final int ADMIN_ROLEID = 3006;	
		final int THE_THEACHER_ROLEID = 3004;
		final int HEAD_THEACHER_ROLEID = 3005;
		int [] classNames = {20170207, 20170208, 20170209,20170210};
		// 登录信息暂时没有的情况，自己先做一个数据保证程序运行
		UserInfo stu = new UserInfo(20170207, 170000002, "管理员", "男",
				88,  "4437074544@qq.com", 11593239991L, 3006);
		session.setAttribute("userInfo", stu);
		UserInfo admin = (UserInfo)session.getAttribute("userInfo");
		if(admin==null){
			//request.getRequestDispatcher("NoLogin.jsp").forward(request, response);
			return;
		}
		// 这一步判断可以去掉，肖梦娜使用过滤器做， 对应的权限只能访问对应的目录
		if(admin.getRoleId()!=ADMIN_ROLEID){
			//request.getRequestDispatcher("NoLogin.jsp").forward(request, response);
			return;	
		}
		request.setAttribute("theTheacher", THE_THEACHER_ROLEID);
		request.setAttribute("headTheacher", HEAD_THEACHER_ROLEID);
		request.setAttribute("classNames", classNames);
	%>
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
			<div id="d32"><a href="#">班级管理</a></div>
			<div id="d33"><a href="#">教师信息查看</a></div>
			<div id="d34"><a href="#">添加教师</a></div>
			<div id="d35"><a href="#">罢免教师</a></div>
			<div id="d36"><a href="#">教师信息更改</a></div>
		</div>
		<div id="d4">
			<div id="d41">添加教师</div>
			<table id="t1" >
				<tr>
					<td>&nbsp;&nbsp;学号：<input type="text"  name="id" value=""></td>
					<td>&nbsp;&nbsp;姓名：<input type="text" name="name" value=""></td>
					<!-- 默认全部查询 -->
					<td>&nbsp;&nbsp;<input type="button" id="query" name="query" value="查 询"></td>
				</tr>
			</table>
			<table id="t2" border="1">
				<tr bgcolor="#8FBC8F">
					<td>序号</td><td>学号</td><td>姓名</td><td>性别</td><td>年龄</td>
					<td>邮箱</td><td>手机号</td><td>班级选择</td><td colspan="3">操作</td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td>
					<td>
						<select name="className" >
							<c:forEach var="className" items="${requestScope.classNames }">
								<option value="${className }">${className }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<span>任课老师</span>
						<input type="radio" name="theacherType"  value="${requestScope.theTheacher }"  />
						<span>班主任</span>
						<input type="radio" name="theacherType"  value="${requestScope.headTheacher }"  />
						<input type="button" value="确认选择" onclick="" >
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	
</body>
</html>