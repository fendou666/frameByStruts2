<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>单个请求</p>
	<a href="aaa.action">aaa</a>
	<a href="Test.action">test</a>
	<p>多个请求</p>
	<a href="ATest.action">ATest.action</a>
	<a href="BTest.action">BTest.action</a>
	<a href="CTest.action">CTest.action</a>
	<a href="DTest.action">DTest.action</a>
	<p>多个请求通过参数方式</p>
	<a href="Param.action?action=ATest">param ATest.action</a>
	<a href="Param.action?action=BTest">param BTest.action</a>
	<a href="Param.action?action=CTest">param CTest.action</a>
	<a href="Param.action?action=DTest">param DTest.action</a>
</body>
</html>