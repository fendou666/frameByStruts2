<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改学员信息</title>
<style type="text/css">
	body{
		font-size: 26px;
	}
</style>
</head>
<body>
<!-- 保存没搞定 -->
	<form action="modStu" method="post">
		学号:${requestScope.stu.id}<s:property value="#parameters.stu.id"/><input type="hidden" name="id" value="${requestScope.stu.id}"><br>
		姓名：<input type="text" name="name" value="${requestScope.stu.name}"><br>
		<c:if test="${requestScope.stu.sex eq '男'}">
				性别：<input type="radio" name="sex" value="男" checked="checked">男
				      <input type="radio" name="sex" value="女">女<br>
		</c:if>
		<c:if test="${requestScope.stu.sex eq '女'}">
				性别：<input type="radio" name="sex" value="男">男
				      <input type="radio" name="sex" value="女" checked="checked">女<br>
		</c:if>
		年龄：<input type="number" name="age" value="${requestScope.stu.age}"><br>
		毕业院校：<input type="text" name="gradeFrom" value="${requestScope.stu.gradeFrom}"><br>
		<input type="submit" value="保存">
	</form>
</body>
</html>