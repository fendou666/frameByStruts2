$(function(){
	getClassStudentAll("first");
})
//获取
function getClassStudentAll(page) {
	var listInfo = "";
	//请求所有学生数据
	$.post(
			 path + "/teamcheck",
		{
			page:page
		},
		function(data){
			listInfo = "";
		    var	student=eval(data);
			var i = 0;
			listInfo += "<table class=\"table table-striped\"><caption>本组成员</caption>" + 
				"<td>序号</td><td>学号</td><td>姓名</td><td>性别</td><td>年龄</td><td>电话</td><td>毕业院校</td><td>住址</td><td>邮箱</td><td>身份证</td><td>班长</td><td>操作</td>";
			if(student == null){
				listInfo += "<tr><td colspan=\"12\">暂无数据</td></tr></table>";
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
					"<td>" + info.c_monitor + "</td>" + 
					"<td></td>";
				});
				
				listInfo += 
				"<tr><td ><a href=\"#\" onclick=\"getClassStudentAll('first')\"> 首页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getClassStudentAll('pre')\">上页</a></td>" +
				"<td ><a href=\"#\" onclick=\"getClassStudentAll('nxt')\">下页</a></td>" +
				"<td><a href=\"#\" onclick=\"getClassStudentAll('last')\">尾页</a></td>" +
				"<td colspan=\"8\"><a href=\"#\" onclick=\"getClassStudentAll(getNumber())\">跳到指定页</a><input type=\"number\" id=\"to\">";
				
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
