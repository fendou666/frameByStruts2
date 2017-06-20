function getCustomPageIndex(){
	return document.getElementById("pageIndex").value;
}

function showInfo(){
	console.log("roleId :" + $("#roleId").val());
	console.log("classId :" + $("#classId").val());
	console.log("id :" + $("#id").val());
	console.log("name :" + $("#name").val());
}

function getEecUserInfo(servlet, forward, operationType){
	showInfo();
	$.post(
			servlet,				
			{
				action:"getQueryTheacher",
				// 分页对象存储在session中的key， 本来考虑通过传参，锁定不同的分页对象，
				// 不同人需求不同，暂时先各自按自己key分页
				sqlPageMapKey:"teacherPage",  
				pageIndex: forward, // 页面跳转参数
				roleId:$("#roleId").val(),
				classId:$("#classId").val(),
				id:$("#id").val(),
				name:$("#name").val()
			},
			function(data){
				//console.log("数据解析支持成功");
				var eecUsers = eval(data);
				var htmlData = "";
				htmlData +=	'<tr bgcolor="#8FBC8F">';
				htmlData += '<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>';
				htmlData += '<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td>';
				htmlData += '</tr>';
				if(eecUsers == null || eecUsers.length == 0){
					htmlData += "<tr >";
					htmlData += '<td colspan="12">没有获取到数据</td>';
					htmlData += "</tr>";
				}else{
					
					$.each(eecUsers ,function(i){
						htmlData += "<tr id=\"usd" + (i+1) + "\">";
						htmlData += "<td>" + (i+1)  +"</td>";
						htmlData += "<td>" + eecUsers[i].classId  +"</td>";
						htmlData += "<td>" + eecUsers[i].id  +"</td>";
						htmlData += "<td>" + eecUsers[i].name  +"</td>";
						htmlData += "<td>" + eecUsers[i].sex  +"</td>";
						htmlData += "<td>" + eecUsers[i].age  +"</td>";
						htmlData += "<td>" + eecUsers[i].email  +"</td>";
						htmlData += "<td>" + eecUsers[i].telephone  +"</td>";
						htmlData += "<td>" + eecUsers[i].roleName  +"</td>";
						if(operationType == "delete"){
							htmlData += "<td><a href=\"" + servlet +"?action=deleteTheacher&id="+ eecUsers[i].id +"\">修改</a></td>";
						}
						if(operationType == "update"){
							htmlData += "<td><a href=\"" + servlet +"?action=deleteTheacher&id="+ eecUsers[i].id +"\">删除</a></td>";
						}
						
						htmlData += "</tr>";
					})
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'first\')" value="首页" ></td>';
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'pre\')" value="前页" ></td>';
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'next\')" value="次页" ></td>';
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'last\')" value="尾页" ></td>';
					htmlData +=  '<td colspan="2"><input type="number" id="pageIndex">';
					htmlData +=  '<input type="button" onclick="getEecUserInfo(getCustomPageIndex())" value="指定页" ></td>';
				}
				$("#t2").html(htmlData);
			},
			"JSON"
		);
}