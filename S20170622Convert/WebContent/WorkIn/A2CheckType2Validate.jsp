<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:actionerror/>
	<form action="DataCheckValidateAction" method="post">
		用户名：<input type="text" name="userName"><br>
		年龄：<input type="text" name="age"><br>
		爱好: <input type="checkbox" name="likes" value="1" > 足球
			 <input type="checkbox" name="likes" value="2" > 篮球
			 <input type="checkbox" name="likes" value="3" > 羽毛球<br>
		电话号码: <input type="text" name="tel"><br>
		<input type="submit" value="提交">
		<input type="reset" value="重置">
	</form>
</body>
</html>