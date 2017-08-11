function createTeam(){
	$.post(
		path + "/createTeam",	
		{
			teamId:$("#teamId").val(),
			teamName:$("#teamName").val(),
			leaderId:$("#leaderId").val()
		},
		function(data){	
			console.log("result data[" + data +"]");
			alert("----------------data" + data + ":---");
			$("#mesg").css({"color":"red"});
			getNewRand();
			var loginErr=eval('(' + data + ')').mesg;
			alert("mesg.mesg:"+loginErr);
			
			if(loginErr=="1"){
				$("#mesg").html("组ID,组名或者组长ID不能为空！");
			}else if(loginErr=="2"){
				$("#mesg").html("创建成功");
				window.location.href="showTeam";
			}
		}, "JSON"
	)
}
