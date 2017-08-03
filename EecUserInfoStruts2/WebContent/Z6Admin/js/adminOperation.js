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
	
	function changeInfo(servlet, elmtId, eecId){
		var classAry = ["20170207", "20170307", "20170407"];
		var roleInfo = {
				"3004":"任课老师",
				"3005":"班主任"
		};
		var eecInfo={
				"classId": $(elmtId+" td:eq(1)").text()
		}
		var eecInfoRoleId = $(elmtId+" td:eq(8)").text();
		if(eecInfoRoleId == "3004"){
			eecInfo.roleId = "3004";
		}else if(eecInfoRoleId == "3005"){
			eecInfo.roleId = "3005";
		}else{
			console.log("信息出错");
		}
		if(eecInfo.roleId){
			var classSelectHtml = "";
			
		}
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
		var classSelectHtml =  '<select name="className" >';
		for(var i=0;i<classAry.length ;i++){
			if(classAry[i] == roleInfo.classId){
				classSelectHtml += '<option select="select" value="' + classAry[i] +'">' + classAry[i] +'</option>';
			}else{
				classSelectHtml += '<option value="' + classAry[i] +'">' + classAry[i] +'</option>';
			}
		}
		classSelectHtml += '</select>';
		$(elmtId+" td:eq(1)").html(classSelectHtml);
		
		var roleInfoHtml =  '<select name="className" >';
		for(var i=0;i<roleInfo.length ;i++){
			if(classAry[i] == roleInfo.classId){
				roleInfoHtml += '<option select="select" value="' + classAry[i] +'">' + classAry[i] +'</option>';
			}else{
				roleInfoHtml += '<option value="' + classAry[i] +'">' + classAry[i] +'</option>';
			}
		}
		roleInfoHtml += '</select>';
		$(elmtId+" td:eq(1)").html(roleInfoHtml);
		
		
		
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

	function showPageIndex(servlet, pageNum){
		var htmlData = "";
		for(var i=1; i<=pageNum;i++){
			htmlData += '<input type="button"  onclick="getQueryTeacherByPage(\'' + servlet +'\', ' + i +' ,\'query\')" value="' + i +'页">'
			if(pageNum%10==0){
				htmlData += '<br>';
			}
		}
		$("#t3").html(htmlData);
	}


	function QueryShowHtml(servlet, data, operationType){
		console.log(data);
		var recData = eval("(" +data +")");
		var eecUsers = recData.data;
		var htmlData = "";
		htmlData +=	'<tr bgcolor="#8FBC8F">';
		htmlData += '<td>序号</td><td>班级</td><td>学号</td><td>姓名</td><td>性别</td>';
		htmlData += '<td>年龄</td><td>邮箱</td><td>手机号</td><td>职务</td>';
		if(!(operationType === "query")){
			htmlData += "<td>操作</td>";
			console.log("添加操作")
		}
		
		htmlData += '</tr>';
		if(eecUsers == null || eecUsers.length == 0 || eecUsers[0] == null){
			htmlData += "<tr >";
			htmlData += '<td colspan="12">没有获取到数据</td>';
			htmlData += "</tr>";
		}else{
			showPageIndex(servlet, recData.pageMaxNum);
			
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
					htmlData += 'updateInfo(\'' +servlet+'\', \'#usd'+ (i+1) +'\',' + eecUsers[i].id +')" >删除</a></td>';
				}
				
				htmlData += "</tr>";
			})
			
			htmlData +=  '<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage(\''+servlet +'\',\'first\',\'' + operationType +'\' )" value="首页" ></td>';
			htmlData +=  '<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage(\''+servlet +'\',\'pre\',\'' + operationType +'\')" value="前页" ></td>';
			htmlData +=  '<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage(\''+servlet +'\',\'next\',\'' + operationType +'\')" value="次页" ></td>';
			htmlData +=  '<td colspan="2"><input type="button"  onclick="getQueryTeacherByPage(\''+servlet +'\',\'last\',\'' + operationType +'\')" value="尾页" ></td>';
			htmlData +=  '<td colspan="2"><input type="number" id="pageIndex">';
			htmlData +=  '<input type="button" onclick="getQueryTeacherByPage(\''+servlet +'\', getCustomPageIndex(),\'' + operationType +'\')" value="指定页" ></td>';
		}
		$("#t2").html(htmlData);
	}
	
	
	function getEecUserInfo(servlet, forward, operationType){
		var postData = {
				// 分页对象存储在session中的key， 本来考虑通过传参，锁定不同的分页对象，
				// 不同人需求不同，暂时先各自按自己key分页, 这里去掉，暂时后台进行配置
				// sqlPageMapKey:"teacherPage",  
				pageIndex: forward, // 页面跳转参数
				roleId:$("#roleId").val(),
				classId:$("#classId").val(),
				id:$("#id").val(),
				name:$("#name").val()
		};
		$.post(
				"AdminTheacherQueryAction",
				postData,
				function(data){
					//console.log("数据解析支持成功");
					console.log(data);
					QueryShowHtml("AdminTheacherQueryPageAction", data, operationType);
				},
				"JSON"
		);
	}
	
	function getQueryTeacherByPage(servlet, forward, operationType){
		var postData = {
				pageIndex: forward // 页面跳转参数
		};
		$.post(
				"AdminTheacherQueryPageAction",
				postData,
				function(data){
					console.log(data);
					QueryShowHtml("AdminTheacherQueryPageAction", data, operationType);
				},
				"JSON"
		);
	}



