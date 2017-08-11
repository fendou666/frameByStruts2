$(function(){
	getClass("first");
})

//获取
function getClass(page) {
	var listInfo = "";
	//请求所有学生数据
	$.post(
			path+"/showClass",
		{
			page:page
		},
		function(data) {
			var student=eval('(' + data + ')');
			listInfo = "";
			var i = 0;
			listInfo += "<table class=\"table table-striped\"><caption>班级结构</caption>" + 
				"<td>班级ID</td><td>班级名</td><td>班长ID</td><td>班主任ID</td><td>任课老师ID</td>";
			if(student == null){
				listInfo += "<tr><td colspan=\"5\">暂无数据</td></tr></table>";
			}else{
				$.each(student, function(i,info){
				    listInfo += "<tr>" + 
					"<td>" + info.id + "</td>" + 
					"<td>" + info.name + "</td>" + 
					"<td>" + info.monitorID + "</td>" + 
					"<td>" + info.manTeacherID + "</td>" + 
					"<td>" + info.teacTeacherID + "</td>";
					/*"+ <td><a href=\""+path+"/getStuData?action=deletestudent&studyid=${clas.id}\" name=\"di\" style=\"display: none;\"> 开除学员</a></td>";*/
				});
				listInfo += 
				"<tr><td ><a href=\"#\" onclick=\"getClassStudentAll('first')\"> 首页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getClassStudentAll('pre')\">上页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getClassStudentAll('nxt')\">下页</a></td>" +
				"<td><a href=\"#\" onclick=\"getClassStudentAll('last')\">尾页</a></td>" +
				"<td><a href=\"#\" onclick=\"getClassStudentAll(getNumber())\">跳到指定页</a><input type=\"number\" id=\"to\">";
				
				listInfo += "</table>";
			}
			$("#info").html(listInfo);
		},
		"JSON"
	);
}

function getNumber(){
	return $("#to").val();
}