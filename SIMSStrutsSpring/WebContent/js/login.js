
//验证用户名和密码是否正确
function login() {
	var flag = yj.checkAll();
	
	//如果验证通过，发送登录请求
	if(flag){
		$.post(
			path+"/login",
			{
				userId:$("#userId").val(),
				pwd:$("#pwd").val(),
				permissions:$("#permissions").val(),
				randNum:$("#randNum").val()
			},
			function(data) {
				console.log("result data[" + data +"]");
				$("#loginErr").css({"color":"red"});
				getNewRand();
				var loginErr=eval('(' + data + ')').mesg;
				
				if(loginErr=="1"){
					$("#loginErr").html("用户名、密码或权限不能为空！");
				}else if(loginErr=="2"){
					$("#loginErr").html("用户名或密码错误");
				}else if(loginErr=="3"){
					$("#loginErr").html("权限不对");
				}else if(loginErr=="4"){
					$("#loginErr").html("验证码不匹配");
				}else if(loginErr=="5"){
					window.location.href="mainPage";
				}
			},
			"JSON"
		)
	}else{
		return false;
	}
	
}


//验证码输入检查
function checkImgNum(){
	$.post(
		path+"/identifyNum",
		{
			randNum:$("#randNum").val(),
		},
		function(data){
			if(data == "NULL"){
				$("#c_randNum").css({"color":"red"});
				$("#c_randNum").html("请输入验证码");
			}else if(data == "NG"){
				$("#c_randNum").css({"color":"blue"});
				$("#c_randNum").html("验证码输入有误，请重新输入");
			}else {
				$("#c_randNum").css({"color":"green"});
				$("#c_randNum").html("验证码输入正确");
			}
		}
		
	);
} 
  
	
//验证码图片改变
function getNewRand(){
    $("#image").attr("src", path+"/createNum?randNum="+Math.random());
}

var yj={};

yj.check=function(elementIn,elementDis){
	if(elementIn.val()==null||elementIn.val()==""){
		elementDis.css({"display":"block"});
		return false;
	}else{				
		return true;
	}
}
yj.checkAll=function(){
	$("#c_randNum").css({"display":"none"});
	$("#c_password").css({"display":"none"});
	$("#c_randNum").css({"display":"none"});
	
	if((!this.check($("#userId"),$("#c_userId")))|
	(!this.check($("#pwd"),$("#c_password")))|
	(!this.check($("#randNum"),$("#c_randNum")))){
		$("#c_randNum").css({"color":"red"});
		$("#c_randNum").html("请输入验证码");
		return false;
	}else{	
		return true;
	}
}