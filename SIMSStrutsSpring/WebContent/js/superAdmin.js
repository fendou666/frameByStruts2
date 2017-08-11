
//获取所有一般管理数据
function getData(page){
	var listInfo = "";
	
	$.post(
		path + "/getNormAdminData?page=" + page,
		{},
		function(data){
			listInfo = "";
			var i = 0;
			listInfo += "<table class=\"table table-striped\"><caption>管理员信息</caption>" + 
				"<tr><td>序号</td><td>学号</td><td>姓名</td><td>电话</td><td>操作</td></tr>";
			
			var chats=eval('(' + data + ')');
			
			if(chats.mesg != null){
				listInfo += "<tr><td colspan=\"7\">暂无数据</td></tr></table>";
			}else{
				$.each(chats, function(i, info){
					
					listInfo += "<tr>" + 
					"<td>" + (i+1) + "</td>" + 
					"<td>" + info.id + "</td>" + 
					"<td>" + info.name + "</td>" + 
					"<td>" + info.tel + "</td>" + 
					"<td><a href=\"#\" onclick=\"getDelMesg("+info.id+")\">罢免</a></td></tr>";
				});
				
				listInfo += 
				"<tr><td ><a href=\"#\" onclick=\"getData('first')\"> 首页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getData('pre')\">上页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getData('nxt')\">下页</a></td>" +
				"<td><a href=\"#\" onclick=\"getData('last')\">尾页</a></td>" +
				"<td><a href=\"#\" onclick=\"getData(getNumber())\">跳到指定页</a><input type=\"number\" id=\"to\">";
				
				listInfo += "</table>";
			}
			$("#info").html(listInfo);
			
		}, "JSON"
	);
}

function getNumber(){
	return $("#to").val();
}

function getDelMesg(id){
	$.post(
		path +"/getDelMesg?id=" + id,
		{},
		function(data){
			var chats=eval('(' + data + ')');
			if(chats.mesg != null){
				$("#mesg").html(chats.mesg);
			}
		}, "JSON"
	)
}

function addAdmin(){
	$.post(
		path + "/getAddMesg",	
		{
			id:$("#id").val(),
		},
		function(data){
			var chats=eval('(' + data + ')');
			if(chats.mesg != null){
				$("#mesg").html(chats.mesg);
			}
		}, "JSON"
	)
}
