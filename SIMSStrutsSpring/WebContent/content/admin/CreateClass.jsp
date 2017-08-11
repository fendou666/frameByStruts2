<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		String path = request.getContextPath();
		pageContext.setAttribute("path", path);
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>班级注册页面</title>
		<link rel="stylesheet" href="${pageScope.path}/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/bg.css" />
		<link rel="stylesheet" href="${pageScope.path}/css/main.css" />
		
		<script src="${pageScope.path}/js/jquery-1.7.2.min.js"></script>
		<script src="${pageScope.path}/js/main.js"></script>
		<script src="${pageScope.path}/js/Admin.js"></script>

	<script type="text/javascript">
	
		var path = "<%=path%>";
		var yj={};
		yj.$=function(element){
		 	return document.getElementById(element);
		}
		yj.check=function(elementIn,elementDis){
			if(elementIn.value==null||elementIn.value==""){
				elementDis.style.display="block";
				return false;
			}else{				
				return true;
			}
		}
		yj.checkAll=function(){
			yj.$("c_id").style.display="none";
			yj.$("c_name").style.display="none";
			yj.$("c_manTeacherID").style.display="none";
			yj.$("c_teacTeacherID").style.display="none";
			
			
			if((!this.check(yj.$("id"),yj.$("c_id")))|
			(!this.check(yj.$("name"),yj.$("c_name")))|
			(!this.check(yj.$("teacTeacherID"),yj.$("c_teacTeacherID")))|
			(!this.check(yj.$("manTeacherID"),yj.$("c_manTeacherID")))){
				return false;
			}else{					
				return true;
			}
		}
	</script>
</head>
	<body>
		<div id="layer1" >
    		<img src="${pageScope.path}/img/bg.jpg" width="100%" height="100%"/>
    	</div>
		<div id="outer">
			<div id="logo" class="div_b show">学生信息管理系统</div>
			<!--主导航页面-->
			<div id="main_navig" class="div_b">
				<ul class="main_navig">
					<jsp:include page="../user_right.jsp"></jsp:include>
				</ul>
			</div>
		<div id="inner_navig" class="div_b">
				<ul>
					<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/admin/Admin.jsp">查询所有班级</a></li>
					<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/admin/CreateClass.jsp">创建新班级</a></li>
					<li class="inner_navig"><a class="btn btn-primary" href="${pageScope.path}/content/admin/appClassLeader.jsp">任命班主任</a></li>
				</ul>
			</div>
		<div id="show_info">
			<div id="info" align="center">
				<table  border="1" style="text-align: center;" class=" table-striped">
					<caption>班级注册</caption>
					<tr>
						<td>班级ID</td>
						<td><input type="text" id="id" name="id">
							<div id="c_id" style="display: none;color: red;">班级ID不能为空</div>
						</td>
					</tr>
					<tr>
						<td>班级名称</td>
						<td><input type="text" id="name" name="name">
							<div id="c_name" style="display: none;color: red;">班级名称不能为空</div>
						</td>
					</tr>
					<tr>
						<td>班主任ID</td>
						<td><input type="text" id="manTeacherID" name="manTeacherID">
							<div id="c_manTeacherID" style="display: none;color: red;">班主任不能为空</div>
						</td>
					</tr>
					<tr>
						<td>任课老师ID</td>
						<td><input type="text" id="teacTeacherID" name="teacTeacherID">
							<div id="c_teacTeacherID" style="display: none;color: red;">任课老师为空</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button id="submit" onclick="addClassInfo()" >提交</button>
						</td>
					</tr>
				</table>
				<div id="mesg"></div>
			</div>
			
		</div>
			<div id="footer" class="div_b">链接信息区</div>
		</div>
	</body>
</html>