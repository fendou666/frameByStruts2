<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		var a;
		console.log(a);
		$.post(
				"<%=request.getContextPath()%>/TestReq",
				{action:undefined},
				function(data){
					console.log("请求完毕")
				}
		)
	}
	)

</script>

<body>
	<%-- <a href="<%=request.getContextPath()%>/TestReq?action=''"> 提交请求</a> --%>
	aaa
</body>
</html>