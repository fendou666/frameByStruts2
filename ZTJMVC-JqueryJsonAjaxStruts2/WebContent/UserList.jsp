<%@page import="com.study.mvc.tools.PageManager"%>
<%@page import="com.study.mvc.model.bean.StudentInfo"%>
<%@page import="com.study.mvc.model.bean.UserInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- uri:标签库描述文件所在位置 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>具体功能页面</title>
<style type="text/css">
	body{
		font-size: 26px;
	}
</style>
<script type="text/javascript" src="Myjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function delArm(){
		if(confirm("确认要删除吗？")){
			return true;
		}	
		else{
			return false;
		}
	}
	function pageNumberS(){
		return document.getElementById('pagenum').value;
	}
</script>
<script type="text/javascript">
    function getBodyData(forword){
		$.post(
			"getdata?page="+forword,
			{
				stuname:$("#stuname").val()
			},
			function(data){	
				//将Json转换为Js数组对象
				var pageNum=data.maxpage;
				/* alert(pageNum) */
				var students=eval(data.result);
				var htmStr="";
				htmStr+="<hr><table border=\"3\">";
				htmStr+="<caption>学员信息一览</caption>";
				htmStr+="<tr align='center'><td>序号</td><td>学号</td><td>姓名</td><td>性别</td><td>年龄</td><td>毕业院校</td><td colspan=\"2\">操作</td></tr>";
				if(students==null){
					htmStr+="<tr align='center'>";
					htmStr+="<td colspan=\"8\" align=\"center\">没有符合条件的数据</td>";
					htmStr+="</tr>";
					htmStr+="</table>";
					$("#tbl").html(htmStr);
					return;
				}
				//如何使用Jquery解析XML中的数据
				//用Jquery方法each遍历该数组对象，尤其注意遍历规则函数的写法
				$.each(students,function(i,stu){
					htmStr+="<tr align='center'>";
					htmStr+="<td>"+(i+1)+"</td>";
					htmStr+="<td>"+stu.id+"</td>";
					htmStr+="<td>"+stu.name+"</td>";
					htmStr+="<td>"+stu.sex+"</td>";
					htmStr+="<td>"+stu.age+"</td>";
					htmStr+="<td>"+stu.gradeFrom+"</td>";
					htmStr+="<td><a href=\"modifyStu?id="+stu.id+"\">修改</a></td>";
					/* 删除没搞定 */
					htmStr+="<td><a href=\"delStu?id="+stu.id+"\" onclick=\"return delArm()\">删除</a></td>";
					htmStr+="</tr>";
				});
				htmStr+="<tr align='center'>";
				htmStr+="<td colspan='2'><input type=\"button\" value='首页' onclick='getBodyData(\"first\")'></td>";
				htmStr+="<td><input type=\"button\" value='前页' onclick='getBodyData(\"pre\")'></td>";
				htmStr+="<td colspan='2'><input type=\"button\" value='次页' onclick='getBodyData(\"nxt\")'></td>";
				htmStr+="<td><input type=\"button\" value='尾页' onclick='getBodyData(\"last\")'></td>";
				htmStr+="<td colspan='2'><input type='number' id='pagenum'><input type=\"button\" value='指定页' onclick='getBodyData(pageNumberS())'></td>";
				htmStr+="</tr>";
				if(pageNum>1){
					htmStr+="<tr align='center'>";
					htmStr+="<td colspan='8'>"
					for(var i=1;i<=pageNum;i++){
						htmStr+="<a href=\"#\" onclick=\"getBodyData("+i+")\" style='margin-left:20px;'>"+i+"</a>"
						if(i%10==0){
							htmStr+="<br>";
						}
					}
					htmStr+="</td>";
					htmStr+="</tr>";
				 } 
				htmStr+="</table>";
				//首页 前页 次页 尾页
				$("#tbl").html(htmStr);	
			},
			"JSON"
		);
    }
	$(function(){
		$("#search").click(function(){
			getBodyData('first');
		});
	});
</script>
</head>
<body>
	欢迎您，<s:property value="#session.userInfo.name"/>
	<hr>
    <form action="" >
		<table border="3">
		    <caption>学员信息查询处理</caption>
		    <tr>
		       <td>姓名：</td><!--模糊查询-->
		       <td><input type="text" name="stuname" id="stuname" style="height:30px;"></td>
		    </tr>
		    <tr>
		       <td colspan="2" style="text-align: center;">
		       		<input type="button" id="search" value="查询" style="height:30px;font-size: 20px;">
		       		<input type="reset" id="clear" value="重置查询" style="height:30px;font-size: 20px;margin-left: 20px;">
		       	</td>
		    </tr>
		</table>
    </form>
    <!-- Ajax需要刷新的区域 -->
    <div id="tbl"></div>
    <s:actionmessage/>
    <%-- <c:if test="${not empty sessionScope.maxpage}">
	    <c:forEach begin="1" end="${maxpage}" var="nun"> 
	    	<a href="#" onclick="getBodyData(${nun})">${nun}</a>
	    </c:forEach>
    </c:if> --%>
</body>
</html>