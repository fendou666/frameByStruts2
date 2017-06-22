<%@page import="com.chinasofti.eecuser.model.javabean.UserInfo"%>
<%@page import="com.chinasofti.eecuser.model.javabean.MsgListNeed"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.chinasofti.eecuser.model.javabean.MsgList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>留言板</title>
	</head>
	<script type="text/javascript">
		var ls = {};
		ls.$ = function(id){
			return document.getElementById(id);
		}
		function testShow(elmt){
			elmt.style.display="block";
		}
		function testHide(elmt){
			elmt.style.display="none";
		}
		function deltInfo(id){
			ls.$(id).style.display="none";
			return false;
		}
		function changeContent(sourceContentId, editeDivId, editeContentId){
			ls.$(editeDivId).style.display = 'block';
			ls.$(sourceContentId).style.display = 'none';
		}
		function submitValue(sourceContentId, editeDivId, editeContentId){
			var value = ls.$(editeContentId).value;
			ls.$(sourceContentId).innerText = value;
			ls.$(sourceContentId).style.display = 'block';
			ls.$(editeDivId).style.display = 'none';
			return false;
		}
		function ajaxGetMsgInfo(){
			var xmlHttp;
			if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			}else{
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState==4 && xmlHttp.status==200){
						
				}
			}
			xmlHttp.open("POST", "MsgServlet?action=page");
			xmlHttp.send(null);
		}
		function ajaxDelMsgInfo(){
			var xmlHttp;
			if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			}else{
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState==4 && xmlHttp.status==200){
						
				}
			}
			xmlHttp.open("POST", "MsgServlet?action=page");
			xmlHttp.send(null);
		}
		function ajaxDelMsgInfo(){
			var xmlHttp;
			if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			}else{
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState==4 && xmlHttp.status==200){
						
				}
			}
			xmlHttp.open("POST", "MsgServlet?action=page");
			xmlHttp.send(null);
		}
		
		
	</script>
	<link rel="stylesheet" type="text/css" href="css/content.css"/>
	<body>
	
		<%-- <%
			UserInfo u = new UserInfo();
			u.setId(170000001);
			session.setAttribute("u", u);
		%> --%>
		
		<%
			ArrayList<MsgListNeed> msgList = new ArrayList<MsgListNeed>();
			;
			msgList.add(new MsgListNeed(1, 170000002, "刘帅", "01.jpg",
					"标题1", "对于你，我始终只能以陌生人的身份去怀念", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(2, 170000003, "张恒", "02.jpg",
					"标题1", "得意时，朋友们认识了你；落难时，你重新认识了朋友", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(3, 170000004, "张宇", "03.jpg",
					"标题1", "空有一身洪荒之力 确没有起床的勇气", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(4, 170000001, "牛振兴", "04.jpg",
					"标题1", "分了就该老死不相往来 展露一丝思念都是贱", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(5, 170000001, "肖梦娜", "05.jpg",
					"标题1", "天干物燥，小心火烛，心情烦躁，生人勿近!", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(6, 170000001, "赵博", "06.jpg",
					"标题1", "世界上唯一不用努力就能得到的只有年龄?", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(7, 170000001, "老师1", "07.jpg",
					"标题1", "对于你，我始终只能以陌生人的身份去怀念", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(8, 170000001, "老师2", "08.jpg",
					"标题1", "对于你，我始终只能以陌生人的身份去怀念", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(9, 170000001, "老师3", "09.jpg",
					"标题1", "对于你，我始终只能以陌生人的身份去怀念", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(10, 170000001, "老师4", "10.jpg",
					"标题1", "对于你，我始终只能以陌生人的身份去怀念", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			msgList.add(new MsgListNeed(11, 170000001, "老师4", "11.jpg",
					"标题1", "对于你，我始终只能以陌生人的身份去怀念", 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					));
			request.setAttribute("msgList", msgList);
			request.setAttribute("msgListLength", msgList.size());
			String indexStr = request.getParameter("index");
			int index = 0;
			System.out.println("index 为" +indexStr);
			if(indexStr!=null){
				index = Integer.parseInt(indexStr);
			}
			request.setAttribute("msgIndex", index);
		%>
		<!-- 待完善功能 1，回复， 收起回复，输入字数量统计  -->
		<div id="show">
			
			<c:forEach var="tmp" items="${requestScope.msgList}" varStatus="iid" begin="${requestScope.msgIndex }" end="${requestScope.msgIndex +9}">
				<div class="c1" id="msgMain_${iid.index +1}">
					<hr style="width: 1000px; height: 1px; background-color: grey; border:0px">
					<div class="imgd">
						<img id="img_0${iid.index +1}" src="img/${tmp.img }"  alt="headImg" width="100px" height="100px" />
					</div>
					<div class="content">
						<p>
							<span class="name" id="name_${iid.index +1}">${tmp.name }</span>
							&nbsp;&nbsp;
							<span class="floorMsgsOnly">只看该作者</span>
							&nbsp;&nbsp;
							<span class="time" id="floor_${iid.index +1}" >第${tmp.msgId }楼</span>
						</p>
						<div class="option">
							<ul style="list-style-type: none; ">
								<li  onmouseleave="testHide(ls.$('hide_${iid.index +1}'))" onmouseover="testShow(ls.$('hide_${iid.index +1}'))">
									<a href="" >▼</a>
									<ul  class="hide" id="hide_${iid.index +1}" style="display: none;text-align:center;list-style-type: none; margin-left: -60px;border: 1px solid;">
										<c:if test="${sessionScope.u.eecId ==  tmp.eecId}">
											<li><a  onclick="return deltInfo('msgMain_${iid.index +1}')">删除</a></li>
											<li><a  onclick="return changeContent('content_${iid.index +1}', 'editeDiv_${iid.index +1}', 'editContent_${iid.index +1}')">更改</a></li>
										</c:if>
										<c:if test="${sessionScope.u.eecId !=  tmp.eecId}">
											<li><a  onclick="return deltInfo('msgMain_${iid.index +1}')">举报</a></li>
											<li><a  onclick="return deltInfo('msgMain_${iid.index +1}')">隐藏该用户内容3天</a></li>
										</c:if>
										
									</ul>
								</li>
							</ul>	
						</div>
						<div class="edit" id="editeDiv_${iid.index +1}">
							<form action="" method="post">
								<textarea id="editContent_${iid.index +1}" name="" rows="10" cols="75">
									${tmp.msgContent  }
								</textarea>
								<p>
									<input type="submit" value="确认"  onclick="return submitValue('content_${iid.index +1}', 'editeDiv_${iid.index +1}', 'editContent_${iid.index +1}')" />
									<input type="reset" value="取消" />
								</p>
							</form>
						</div>
						<div class="innerContern" id="content_${iid.index +1}">
							${tmp.msgContent  }
						</div>
						<p  id="time_${iid.index +1}">
							<span class="time">${tmp.msgTime }</span>
							<span><a href="#" onclick="return false;">&nbsp;&nbsp;&nbsp;&nbsp;回复</a></span>
						</p>
					</div>
					
					<c:if test="${ iid.index == requestScope.msgListLength-1}">
						<hr style="width: 1000px; height: 3px; background-color: gainsboro; border:0px">
					</c:if>
					 
					<c:if test="${requestScope.msgIndex < requestScope.msgListLength && iid.index%10 == 9 }">
						<hr style="width: 1000px; height: 3px; background-color: gainsboro; border:0px">
					</c:if>
				</div>
			</c:forEach>
			<!-- TODO关于这里应该写成ajax方式还是 超级链接方式，还是get/post哪种方式  -->
			<!-- 考虑后面方式的结合决定使用ajax方式  -->
			<c:if test="${requestScope.msgListLength > requestScope.msgIndex +10}">
				<a href="#" onclick="return false, "></a>
				<%-- <a href="msg04.jsp?index=${requestScope.msgIndex +10}">下一页</a> --%>
			</c:if>
		</div>
		
	</body>
</html>