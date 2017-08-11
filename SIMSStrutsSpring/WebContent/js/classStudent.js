$(function(){
	getClassStudentAll("first");
})

//获取
function getClassStudentAll(page) {
	var listInfo = "";
	//请求所有学生数据
	$.post(
			path+"/checkClassStudent",
		{
			page:page
		},
		function(data) {
			var student=eval(data);
			listInfo = "";
			var i = 0;
			listInfo += "<table class=\"table table-striped\"><caption>班级成员</caption>" + 
				"<td>序号</td><td>学号</td><td>姓名</td><td>性别</td><td>年龄</td><td>电话</td><td>毕业院校</td><td>住址</td><td>邮箱</td><td>身份证</td><td>班长</td><td>班主任</td><td>任课老师</td>";
			if(student == null){
				listInfo += "<tr><td colspan=\"14\">暂无数据</td></tr></table>";
			}else{
				$.each(student, function(i,info){
					listInfo += "<tr>" + 
					"<td>" + (i+1) + "</td>" + 
					"<td>" + info.id + "</td>" + 
					"<td>" + info.name + "</td>" + 
					"<td>" + info.sex + "</td>" + 
					"<td>" + info.age + "</td>" + 
					"<td>" + info.tel + "</td>" + 
					"<td>" + info.gradFrom + "</td>" + 
					"<td>" + info.addr + "</td>" + 
					"<td>" + info.email + "</td>" + 
					"<td>" + info.idCard + "</td>" + 
					"<td>" + info.c_monitor + "</td>"+
					"<td>" + info.c_man_teacher + "</td>"+
					"<td>" + info.c_teac_teacher + "</td>";
					/*"+ <td><a href=\""+path+"/getStuData?action=deletestudent&studyid=${clas.id}\" name=\"di\" style=\"display: none;\"> 开除学员</a></td>";*/
				});
				
				listInfo += 
				"<tr><td ><a href=\"#\" onclick=\"getClassStudentAll('first')\"> 首页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getClassStudentAll('pre')\">上页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getClassStudentAll('nxt')\">下页</a></td>" +
				"<td><a href=\"#\" onclick=\"getClassStudentAll('last')\">尾页</a></td>" +
				"<td colspan=\"9\"><a href=\"#\" onclick=\"getClassStudentAll(getNumber())\">跳到指定页</a><input type=\"number\" id=\"to\">";
				
				listInfo += "</table>";
			}
			$("#info").html(listInfo);
			setHeight();
		},
		"JSON"
	);
}

function getNumber(){
	return $("#to").val();
}