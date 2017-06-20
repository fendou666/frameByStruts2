<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("IOCrequest", "我是请求IOCAction的 request属性设置的值");
		session.setAttribute("IOCsession", "我是请求IOCAction的 session属性设置的值");
	%>
	<a href="IOCAction"></a>
</body>
</html>