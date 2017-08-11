<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE>
<html>
<head>
	<%
		String path = request.getContextPath();  //取到绝对根目录
		pageContext.setAttribute("path", path);
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>密码找回页面</title>
		
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css"/>
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/login.css" />
		<script src="${pageScope.path}/js/login.js"></script>
		
		<c:if test="${not empty requestScope.chpwd}">
		<div style="color: red;">${requestScope.chpwd}</div>
		</c:if>
        <script type="text/javascript">
		var yj={};
		yj.$=function(element){
		 	return document.getElementById(element);
		}
		yj.check=function(elementIn,elementDis){
			if(elementIn.value==null||elementIn.value==""){
				elementDis.style.display="block";
				return false;
			}else{				
				return true;
			}
		}
		yj.checkAll=function(){
			yj.$("c_id").style.display="none";
			yj.$("c_tel").style.display="none";
			yj.$("c_idCard").style.display="none";
			yj.$("c_email").style.display="none";
			
			if((!this.check(yj.$("userid"),yj.$("c_id")))|
			(!this.check(yj.$("tel"),yj.$("c_tel")))|
			(!this.check(yj.$("idCard"),yj.$("c_idCard")))|
			(!this.check(yj.$("email"),yj.$("c_email")))){
				return false;
			}else{					
				return true;
			}
		}
</script>
</head>
<body>
   <div id="layer1">
    		<img src="${pageScope.path}/img/bg.jpg" width="100%" height="100%"/>
    	</div>
	<form action="passwordBack" method="post" >
		<table id="td">
			<caption style="font-size:300%;padding: 30px;">密码找回页面</caption>
			<tr>
				<td class="left_td">账号：</td>
				<td class="right_td" ><input type="text" id="userId" name="userId" placeholder="请输入账号">
					<div id="c_id" style="display: none;color: red;">*账号不能为空</div>
				</td>
			</tr>
			<tr>
				<td class="left_td">手机号：</td>
				<td class="right_td" ><input type="number" id="tel" name="tel" placeholder="请输入手机号">
					<div id="c_tel" style="display: none;color: red;">*手机号不能为空</div>				
				</td>
			</tr>
			<tr>
				<td class="left_td">身份证号：</td>
				<td class="right_td" ><input type="text" id="idCard" name="idCard" placeholder="请输入身份证号">
					<div id="c_idCard" style="display: none;color: red;">*身份证号不能为空</div>
				</td>
			</tr>
			<tr>
				<td class="left_td">邮箱：</td>
				<td class="right_td" ><input type="text" id="email" name="email" placeholder="请输入邮箱">
					<div id="c_email" style="display: none;color: red;">*邮箱不能为空</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" onclick="return yj.checkAll() " value="提交" style="width: 80px;">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置" style="width: 80px;">
				</td>
			</tr>
			
			<c:if test="${not empty requestScope.findpwdMes}">
				<tr>
				<td colspan="2"><div style="color: red">${requestScope.findpwdMes}</div></td>
				</tr>
			</c:if>	
			<s:actionerror/>
			<s:actionmessage/>
			<%-- <c:if test="${requestScope.findpwdMes eq '验证成功'}">
				<jsp:forward page="changepwd.jsp?id=${requestScope.id}"></jsp:forward>
			</c:if> --%>
		</table>
	</form>
</body>
</html>