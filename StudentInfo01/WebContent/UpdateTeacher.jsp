<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	div{
		position:absolute;
		width:400px;
		left:50%;
		top:20%;
		margin-left:-200px;
	}
	table{
		margin:auto;
		width:400px;
		border:2px solid blue;
	}
	tr{
		border: 1px solid blue;
	}
</style>
<body>
	<c:if test="${empty sessionScope.stu}">
		<jsp:forward page="NoLogin.jsp"></jsp:forward>	
	</c:if>
	<c:if test="${not empty sessionScope.stu}">
		用户名:<c:out value="${sessionScope.stu.name }"></c:out>
	</c:if>
	
	<c:if test="${empty requestScope.stu }">
		<c:set var="msg" value="没有获取到参数" scope="request"></c:set>
		<jsp:forward page="Success.jsp"></jsp:forward>
	</c:if>
	
	<div>
		<form action="<%=request.getContextPath() %>/OracleOperationServlet?action=cg" method="post">
			<table>
				<!--TODO 做成下拉框  -->
				<tr>
					<td>班级</td>
					<td><input type="text" name="classId" value="${requestScope.user.classId}" /></td>
				</tr>
				<tr>
					<td>学号</td>
					<td><input type="text" name="id" value="${requestScope.user.id}" /></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="name" value="${requestScope.user.name}" /></td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
						<c:if test="${requestScope.user.sex eq '男'}">
							男<input type="radio" name="sex" checked="checked" value="男" />
							女<input type="radio" name="sex" value="女" />
						</c:if>
						<c:if test="${requestScope.user.sex eq '女'}">
							男<input type="radio" name="sex"  value="男" />
							女<input type="radio" name="sex" checked="checked" value="女" />
						</c:if>
					</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td><input type="text" name="age" value="${requestScope.user.age}" /></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input type="text" name="email" value="${requestScope.user.email}" /></td>
				</tr>
				<tr>
					<td>手机号</td>
					<td><input type="text" name="telephone" value="${requestScope.user.telephone}" /></td>
				</tr>
				<tr>
					<td>职务</td>
					<td><input type="text" name="roleName" value="${requestScope.user.roleName}" /></td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" value="更新数据"  >
						<input type="reset" value="重置数据"  >
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>