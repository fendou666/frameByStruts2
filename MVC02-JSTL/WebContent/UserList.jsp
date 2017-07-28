<%@page import="com.study.mvc.model.bean.StudentInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.study.mvc.model.bean.UserInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- uri:标签库描述文件所在位置 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>具体功能页面</title>
<script type="text/javascript">
	function delArm(){
		if(confirm("确认要删除吗？")){
			return true;
		}	
		else{
			return false;
		}
	}
</script>
</head>
<body>
	<c:if test="${empty sessionScope.userInfo}">
		<jsp:forward page="LoginServlet"></jsp:forward>
	</c:if>
	<c:if test="${not empty requestScope.msg}">
		<div style="color: red">${requestScope.msg}</div>
	</c:if>
	欢迎您，${sessionScope.userInfo.name}
	<hr>
	<table border="3">
	    <caption>学员信息一览</caption>
		<tr>
			<td>序号</td>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>年龄</td>
			<td>毕业院校</td>
			<td colspan="2">操作</td>
		</tr>
		<c:if test="${not empty requestScope.stus}">
			<c:forEach var="stu" items="${requestScope.stus}" varStatus="iid">
				<tr>
					<td>${iid.index+1}</td>
					<td>${stu.id}</td>
					<td>${stu.name}</td>
					<td>${stu.sex}</td>
					<td>${stu.age}</td>
					<td>${stu.gradeFrom}</td>
					<td><a href="QueryServlet?action=getStuByID&id=${stu.id}">修改</a></td>
					<td><a href="UpdateServlet?action=stu_del&id=${stu.id}" onclick="return delArm()">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.stus}">
			<tr>
				<td colspan="8">没有找到数据</td>
			</tr>
		</c:if>
	</table>
</body>
</html>