function appointTeamLeader(){
	$.post(
		path + "/appoinTeamLeader",	
		{
			teamId:$("#teamId").val(),
			leaderId:$("#leaderId").val()
		},
		function(data){	
			console.log("result data[" + data +"]");
			alert("----------------data" + data + ":---");
			$("#mesg").css({"color":"red"});
			var loginErr=eval('(' + data + ')').mesg;
			alert("mesg.mesg:"+loginErr);			
			if(loginErr=="1"){
				$("#mesg").html("组ID,组长ID不能为空！");
			}else if(loginErr=="2"){
				$("#mesg").html("任命成功");
				window.location.href="showTeam";
			}
		}, "JSON"
	)
}
