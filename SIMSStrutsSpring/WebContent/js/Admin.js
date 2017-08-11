var mesg = {
		"1":"班号不能为空!",
		"2":"查无此班！",
		"3":"没有班级，请创建！",
		"4":"请填写所有项！",
		"5":"当前班级已经存在！",
		"6":"新建成功！",
		"7":"班级ID或班主任ID不能为空！",
		"8":"任命成功！",
		"9":"任命失败，不是本班人！"
}

var tableHead = "<table class=\"table table-striped\"><caption>班级信息一览</caption><tr><td colspan=\"2\" >请输入你想要查询的班级号：</td>" +
		"<td colspan=\"2\" ><input type=\"text\" id=\"classID\" name=\"classID\" placeholder=\"请输入班级号\"></td>" +
		"<td colspan=\"2\"><button id=\"submit\"  onclick=\"getClassInfoById()\">查询</button></td>" +
		"<tr><td>序号</td><td>班级ID</td><td>班级名</td><td>班长</td><td>班主任</td><td>代课老师</td></tr>";
		
function getTableFoot(action){
	return  "<tr><td ><a href=\"#\" onclick=\"getData('"+action + "','first')\"> 首页</a></td>" +
	"<td ><a href=\"#\" onclick=\"getData('"+action+"','pre')\">上页</a></td>" +
	"<td ><a href=\"#\" onclick=\"getData('"+action+"','nxt')\">下页</a></td>" +
	"<td><a href=\"#\" onclick=\"getData('"+action+"','last')\">尾页</a></td>" +
	"<td colspan=\"2\"><a href=\"#\" onclick=\"getData('"+action+"',getNumber())\">跳到指定页</a><input type=\"number\" id=\"to\"></td></table>";	

} 

function getClassInfo(i, classInfo){
	return "<tr>" + 
	"<td>" + (i+1) + "</td>" + 
	"<td>" + classInfo.id + "</td>" + 
	"<td>" + classInfo.name + "</td>" + 
	"<td>" + classInfo.monitorID + "</td>" + 
	"<td>" + classInfo.manTeacherID + "</td>" + 
	"<td>" + classInfo.teacTeacherID + "</td></tr>";
}

//获取所有一般管理数据
function getData(action, page){
	var listInfo = "";
	$.post(
		path + "/" + action + "?page=" + page,
		{
			classID:$("#classID").val()
		},
		function(data){
			listInfo = "";
			var i = 0;
			listInfo += tableHead;
			
			var chats=eval('(' + data + ')');
			
			if(chats.mesg != null){
				listInfo += "<tr><td colspan=\"7\">" + mesg[chats.mesg] + "</td></tr></table>";
			}else{
				$.each(chats, function(i, classInfo){
					listInfo += getClassInfo(i, classInfo);
				});
				listInfo += getTableFoot(action);
			}
			$("#info").html(listInfo);
		}, "JSON"
	);
}

function getNumber(){
	return $("#to").val();
}

function addClassInfo(){
	$.post(
		path +"/addClassInfo",
		{
			id:$("#id").val(),
			name:$("#name").val(),
			manTeacherID:$("#manTeacherID").val(),
			teacTeacherID:$("#teacTeacherID").val(),
			
		},
		function(data){
			var chats=eval('(' + data + ')');
			if(chats.mesg != null){
				$("#mesg").html(mesg[chats.mesg]);
			}
		}, "JSON"
	)
}

function appointManTeacher(){
	$.post(
		path +"/appointManTeacher",
		{
			clID:$("#clID").val(),
			clManId:$("#clManId").val()
		},
		function(data){
			var chats=eval('(' + data + ')');
			if(chats.mesg != null){
				$("#mesg").html(mesg[chats.mesg]);
			}
		}, "JSON"
	)
}
