function getCustomPageIndex(){
	return document.getElementById("pageIndex").value;
}

function showInfo(operationType){
	console.log("roleId :" + $("#roleId").val());
	console.log("classId :" + $("#classId").val());
	console.log("id :" + $("#id").val());
	console.log("name :" + $("#name").val());
	console.log("operationType" + operationType);
}
var delBtOldHtml = null;

function deleteInfo(servlet, elmtId, eecId){
	$.post(
		servlet,
		{
			action:"deleteTheacher",
			//action:"https:www.baidu.com",
			id:eecId
		},
		// TODO 关于这里如果写成JSON格式返回，应该怎么做
		function(data){
			console.info(data);
			//data = "false";
			if(data=="false"){
				if (delBtOldHtml == null){
					delBtOldHtml = $(elmtId+" td:eq(9)").html();
				}
				$(elmtId+" td:eq(9)").html("参数不对，删除继续点击  "+ delBtOldHtml);
			}else if(data=="delFalse"){
				if (delBtOldHtml == null){
					delBtOldHtml = $(elmtId+" td:eq(9)").html();
				}
				$(elmtId+" td:eq(9)").html("删除失败  "+ delBtOldHtm);
			}else if(data=="success"){
				$(elmtId+" td:eq(9)").text("删除成功");
				$(elmtId).css("display", "none"); // TODO这里正确不正确?
			}
			
		}
	)
	return false;
}

function changeInfo(elmtId){
	var classAry = ["20170201", "20170202", "20170203","20170204", "20170205", "20170206", "20170207"];
	var roleInfo = {
			"3004":"任课老师",
			"3005":"班主任"
	};
	var eecInfo={
			"classId": $(elmtId+" td:eq(1)").text()
	}
	var eecInfoRoleName = $(elmtId+" td:eq(8)").text();
	if(eecInfoRoleName == "任课老师"){
		eecInfo.roleId = "3004";
	}else if(eecInfoRoleName == "班主任"){
		eecInfo.roleId = "3005";
	}else{
		console.log("信息出错");
	}
	var proSelect = ' select="select" ';
	var classSelectHtml = '<select name="classId" id="classId">';
	for(var i=0; i<classAry.length; i++){
		console.log("class 遍历" + classAry[i]);
		if(eecInfo.classId == classAry[i]){
			classSelectHtml += '<option value="'+ classAry[i]+'" '+ proSelect +'>'+ classAry[i] +'</option>'
		}else{
			classSelectHtml += '<option value="'+ classAry[i] +'"  >'+ classAry[i] +'</option>'
		}
	}
	classSelectHtml +='</select>';
	$(elmtId+" td:eq(1)").html(classSelectHtml);
	
	if(eecInfo.roleId){
		
		var roleSelectHtml = '<select name="roleId" id="roleId">';
		for(var i in roleInfo){
			if(eecInfo.roleId == i){
				roleSelectHtml += '<option value="'+ i+'" '+ proSelect +'>'+ roleInfo[i] +'</option>'
			}else{
				roleSelectHtml += '<option value="'+ i+'"  >'+ roleInfo[i] +'</option>'
			}
		}
		roleSelectHtml += '</select>';
		$(elmtId+" td:eq(8)").html(roleSelectHtml);
	}
	$(elmtId+" td:eq(9)").html('<a onclick="">确认更新</a>&nbsp;&nbsp;<a>取消更新</a>');
	
	return false;
}


function updateInfo(servlet, elmtId, eecId){
	var classAry = ["20170207", "20170307", "20170407"];
	var roleInfo = {
			"3004":"任课老师",
			"3005":"班主任"
	};
	var eecInfo={
			"classId": $(elmtId+" td:eq(1)").text()
	}
	eecInfo.roleId = roleInfo["3004"] == $(elmtId+" td:eq(8)").text()?"3004":"3005";
	/*$.post(
		servlet,
		{
			action:"updateTheacher",
			//action:"https:www.baidu.com",
			id:eecId,
			classId:$(),
		},
		// TODO 关于这里如果写成JSON格式返回，应该怎么做
		function(data){
			console.info(data);
			//data = "false";
			if(data=="false"){
				if (delBtOldHtml == null){
					delBtOldHtml = $(elmtId+" td:eq(9)").html();
				}
				$(elmtId+" td:eq(9)").html("参数不对，删除继续点击  "+ delBtOldHtml);
			}else if(data=="delFalse"){
				if (delBtOldHtml == null){
					delBtOldHtml = $(elmtId+" td:eq(9)").html();
				}
				$(elmtId+" td:eq(9)").html("删除失败  "+ delBtOldHtm);
			}else if(data=="success"){
				$(elmtId+" td:eq(9)").text("删除成功");
				$(elmtId).css("display", "none"); // TODO这里正确不正确?
			}
			
		}
	)*/
	return false;
}



function getEecUserInfo(servlet, forward, operationType){
	showInfo(operationType);
	$.post(
			"getQueryTheacher",
			{
				// 分页对象存储在session中的key， 本来考虑通过传参，锁定不同的分页对象，
				// 不同人需求不同，暂时先各自按自己key分页
				//sqlPageMapKey:"teacherPage",  
				pageIndex: forward, // 页面跳转参数
				roleId:$("#roleId").val(),
				classId:$("#classId").val(),
				id:$("#id").val(),
				name:$("#name").val()
			},
			function(data){
				//console.log("数据解析支持成功");
				console.log(data);
				var eecUsers = eval(data);
				var htmlData = "";
				htmlData +=	'<tr bgcolor="#8FBC8F">';
				htmlData += '<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>';
				htmlData += '<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td>';
				if(!(operationType === "query")){
					htmlData += "<td>操作</td>";
					console.log("添加操作")
				}
				
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
							//htmlData += "<td><a href=\"" + servlet +"?action=deleteTheacher&id="+ eecUsers[i].id +"\">删除</a></td>";
							htmlData += '<td><a href="#" onclick="return '
							htmlData += 'deleteInfo(\'' +servlet+'\', \'#usd'+ (i+1) +'\',' + eecUsers[i].id +')" >删除</a></td>';
							
						}
						if(operationType == "update"){
							htmlData += '<td><a href="#" onclick="return '
							htmlData += 'changeInfo(\'#usd'+ (i+1) +'\')" >更新</a></td>';
						}
						
						htmlData += "</tr>";
					})
					
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'first\',\'' + operationType +'\' )" value="首页" ></td>';
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'pre\',\'' + operationType +'\')" value="前页" ></td>';
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'next\',\'' + operationType +'\')" value="次页" ></td>';
					htmlData +=  '<td colspan="2"><input type="button"  onclick="getEecUserInfo(\''+servlet +'\',\'last\',v' + operationType +'\')" value="尾页" ></td>';
					htmlData +=  '<td colspan="2"><input type="number" id="pageIndex">';
					htmlData +=  '<input type="button" onclick="getEecUserInfo(getCustomPageIndex())" value="指定页" ></td>';
				}
				$("#t2").html(htmlData);
			},
			"JSON"
		);
}