<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var i=0;
	$(function(){
		$("#div1").click(function(){
			console.log("div click");
			$("#div2").html('<p id="d1">1111111</p>');
		})
		$("#d1").click(function(){
			console.log("点击后i 为" + i++);
			$("#d1").text(i);
		})
	})
	
</script>
<body>
	<div id="div1" style="border:10px solid green" >
		<p >div1</p>
	</div>
	<div id="div2" style="border:10px solid red" >
		<p id="d1">1111111</p>
	</div>
	
</body>
</html>