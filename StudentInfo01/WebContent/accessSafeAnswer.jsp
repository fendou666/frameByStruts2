<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>密码找回</title>
	</head>
	<script src="js/cookie.js" type="text/javascript" charset="utf-8"></script>
	<script type="application/javascript">
		var ls = {};
		var nameIsOk = false;
		
		ls.$ = function(id){
			return document.getElementById(id);
		}
		ls.checkData = function(elmt){
			var ret = true;
			if(elmt.value == null || elmt.value==""){
				ret = false;
			}
			return ret;
		}
		ls.initCheckInfo = function(errElmt){
			console.log("focus in");
			errElmt.innerText = "*";
		}
		
		ls.checkDataAddErr = function(elmt, inputName , errElmt){
			var ret = true;
			if(!ls.checkData(elmt)){
				errElmt.innerText = inputName + "不能为空";
				ret = false;
			}
			return ret;
		}
		ls.checkAllData = function(){
			var ret = true;
			if(!nameIsOk|
				!ls.checkDataAddErr(ls.$('answer1'), '答案', ls.$('answer1CheckInfo'))|
				!ls.checkDataAddErr(ls.$('answer2'), '答案', ls.$('answer2CheckInfo'))|
				!ls.checkDataAddErr(ls.$('answer3'), '答案', ls.$('answer3CheckInfo'))
			){
				ret = false;
			}
			return ret;
		}
		
		ls.getEECIdCookie = function(){
			var eecIdCK = getCookie("eecId");
			if(eecIdCK!=null&&eecIdCK!=""){
				ls.$("eecId").value=eecIdCK;
			}
		}
		ls.nameCheck = function(){
			var nameElmt = ls.$("eecId");
			nameIsOk = false;
			if(!ls.checkDataAddErr(nameElmt, "学号",  ls.$("eecIdCheckInfo"))){
				return;
			}
			nameIsOk = true;
			ls.$("eecIdCheckInfo").innerText = "用户名正确";
			ls.$("eecIdCheckInfo").style.color = "green";
			/*var xmlHttp;
			if(Window.ActiveXObject){
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			}else{
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState==4 && xmlHttp.status==200){
					if(xmlHttp.responseText == "false"){
						nameIsOk = false;
						ls.$("eecIdCheckInfo").innerText = "用户名不存在";
					}else{
						nameIsOk = true;
						ls.$("eecIdCheckInfo").innerText = "用户名正确";
						ls.$("eecIdCheckInfo").style.color = "green";
					}
				}
			}
			xmlHttp.open("myServlet?name=" + name);
			xmlHttp.send();*/
		}
		
		ls.submitForm = function(){
			if(!ls.checkAllData()){
				return false;
			}else{
				return true;
			}
			
			//document.forms["getAnsewerForm"].action = "https://www.baidu.com";
			//document.Submit();
		}
	</script>
	<body  onload="ls.getEECIdCookie()">
		<form id="getAnsewerForm" action="AccessSafeAnswerServlet" method="post">
			<div>
				<p>学号</p>
				<input  type="text" name="eecId" id="eecId" 
					onfocusin="ls.initCheckInfo(ls.$('eecIdCheckInfo'))"
					onfocusout="ls.nameCheck()" autocomplete="on"  />
				<span id="eecIdCheckInfo" style="color:red">*</span>
			</div>
			<div>
				<p style="font-size: large; font-weight: 800;">安全回答</p>
			</div>	
			<div>
				<p>你的家乡在哪里？</p>
				<input  type="text" name="answer1" id="answer1"
					onfocusin="ls.initCheckInfo(ls.$('answer1CheckInfo'))"
					onfocusout="ls.checkDataAddErr(ls.$('answer1'), '答案', ls.$('answer1CheckInfo'))" />
				<span name="answer1CheckInfo" id="answer1CheckInfo" style="color:red">*</span>
				
				<p>你的班主任姓名？</p>
					<input  type="text" name="answer2" id="answer2"
					onfocusin="ls.initCheckInfo(ls.$('answer2CheckInfo'))"
					onfocusout="ls.checkDataAddErr(ls.$('answer2'), '答案', ls.$('answer2CheckInfo'))" />
				<span name="answer2CheckInfo" id="answer2CheckInfo" style="color:red">*</span>
				
				<p>你喜欢什么运动？</p>
					<input  type="text" name="answer3" id="answer3"
					onfocusin="ls.initCheckInfo(ls.$('answer3CheckInfo'))"
					onfocusout="ls.checkDataAddErr(ls.$('answer3'), '答案', ls.$('answer3CheckInfo'))" />
				<span name="answer3CheckInfo" id="answer3CheckInfo" style="color:red">*</span>
			</div>
			<div>
				<input type="submit" onclick="return ls.submitForm()"  />
				<input type="reset" />
			</div>
		</form>
	</body>
</html>