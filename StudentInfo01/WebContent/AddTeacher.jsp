<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	div#main {
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
	td{
		border: 1px solid blue;
	}
</style>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" >
	function initCheckInfo(errInfoId){
		$(errInfoId).text("*");
	}
	// 关于性别的判断数据库需要去对性别设计固定值
	function checkDataAddErr(id, errInfoId){
		var value = $(id).val();
		console.log(id + " 值为 " + value);
		if(value == null || value==""){
			$(errInfoId).text("不可以为空");
			return false; 
		}
		if(id=="#classId" || id=="#eecId"  || id=="#age" || id=="#roleId"){
			//console.log(typeof(parseInt(value)));
			// TODO 格式判断 关系运算符
			/* if(typeof(parseInt(value)) === "number"){
				$(errInfoId).text("格式有误");
				return false; 
			} */
			var reg = new RegExp("^[0-9]*$");
		    if(!reg.test(value)){
		    	$(errInfoId).text("格式有误");
		    	return;
		    }
		}
		if(id=="#email"){
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(!myreg.test(value))
			{
				$(errInfoId).text("请输入有效的E_mail！");
			    return false;
			}
		}
		if(id=="#telephone"){
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
			if(!myreg.test(value)) 
			{ 
				$(errInfoId).text("请输入有效的手机号码！");
			    return false; 
			} 
		}
	}

	function getEecUserInfo(forward){
		$.post(
				"<%=request.getContextPath()%>/AdminServlet",				
				{
					action:forward,
					classId:$("#classId").val(),
					eecId:$("#eecId").val(),
					eecName:$("#eecName").val(),
					sex:$("#sex").val(),
					age:$("#age").val(),
					email:$("#email").val(),
					telephone:$("#telephone").val(),
					roleId:$("#roleId").val()
				},
				function(data){
					//console.log("数据解析支持成功");
					var eecUsers = eval(data);
					var result = "";
					if(eecUsers == null){
						result = "没有获取到数据";
					}else if(eecUsers.error!=null){
						result = eecUsers.error;
					}else{
						location.href = "Admin.jsp";
					}
					$("#msg").text(result);
				},
				"JSON"
			);
	}
	
	$(function(){
		$("#subB").click(function(){
			getEecUserInfo("insertData");
		})
	})

</script>



<body>
	<%-- <c:if test="${empty sessionScope.stu}">
		<jsp:forward page="NoLogin.jsp"></jsp:forward>	
	</c:if>
	<c:if test="${not empty sessionScope.stu}">
		用户名:<c:out value="${sessionScope.stu.name }"></c:out>
	</c:if> --%>
	
	<div id="main">
		<form id="regFM" action="<%=request.getContextPath() %>/AdminServlet?action=insertData" method="post">
			<table>
				<!--TODO 做成下拉框  -->
				<tr>
					<td>班级</td>
					<td>
						<input type="text" name="classId" id="classId"
							onfocusin="initCheckInfo('#classIdCheckInfo')"
							onfocusout="checkDataAddErr('#classId', '#classIdCheckInfo')"
						/>
						<span name="classIdCheckInfo" id="classIdCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>学号</td>
					<td>
						<input type="text" name="eecId"  id="eecId"
							onfocusin="initCheckInfo('#eecIdCheckInfo')"
							onfocusout="checkDataAddErr('#eecId', '#eecIdCheckInfo')"
						/>
						<span name="eecIdCheckInfo" id="eecIdCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>
						<input type="text" name="eecName"  id="eecName" 
							onfocusin="initCheckInfo('#eecNameCheckInfo')"
							onfocusout="checkDataAddErr('#eecName', '#eecNameCheckInfo')"
						/>
						<span name="eecNameCheckInfo" id="eecNameCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
						男<input type="radio" name="sex" id="sex" value="男" />
						女<input type="radio" name="sex" id="sex" value="女" />
						<span name="sexCheckInfo" id="sexCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td>
						<input type="text" name="age"  id="age"
							onfocusin="initCheckInfo('#ageCheckInfo')"
							onfocusout="checkDataAddErr('#age', '#ageCheckInfo')"
						/>
						<span name="ageCheckInfo" id="ageCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td>
						<input type="text" name="email"   id="email"  
							onfocusin="initCheckInfo('#emailCheckInfo')"
							onfocusout="checkDataAddErr('#email', '#emailCheckInfo')"
						/>
						<span name="emailCheckInfo" id="emailCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>手机号</td>
					<td>
						<input type="text" name="telephone" id="telephone" 
							onfocusin="initCheckInfo('#telephoneCheckInfo')"
							onfocusout="checkDataAddErr('#telephone', '#telephoneCheckInfo')"
						 />
						<span name="telephoneCheckInfo" id="telephoneCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td>职务</td>
					<td>
						<select name="roleId" id="roleId" >
							<option value="3004">任课老师</option>
							<option value="3005">班主任</option>
						</select>
						<!-- 	TODO 关于验证  -->
						<!-- <input type="text" name="roleId" id="roleId" 
							onfocusin="initCheckInfo('#roleIdCheckInfo')"
							onfocusout="checkDataAddErr('#roleId', '#roleIdCheckInfo')"
						 /> -->
						<span name="roleIdCheckInfo" id="roleIdCheckInfo" style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="添加数据"  id="subB" >
						<input type="button" value="重置数据" onclick="regFM.reset()" >
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="msg"></div>
	
</body>
</html>