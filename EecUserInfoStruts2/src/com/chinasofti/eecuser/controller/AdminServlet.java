package com.chinasofti.eecuser.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.AdminTheacherServiceImp;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
    }
    
    public void getPageMode(String pageMode, SqlDataPage teacherPage){
    	if(pageMode.equals("first")){
    		teacherPage.getFirstPage();
    	}else if(pageMode.equals("pre")){
    		teacherPage.getPrePage();
    	}else if(pageMode.equals("next")){
    		teacherPage.getNextPage();
    	}else if(pageMode.equals("last")){
    		teacherPage.getLastPage();
    	}else{
    		try{
    			teacherPage.getCustomPage(Integer.parseInt(pageMode));
    		}catch(NumberFormatException e){
    			System.out.println("传入指定页参数有问题，跳入首页");
    			teacherPage.getCustomPage(1);
    		}
    	}
    }
    
	
    
    private SqlDataPage getPageSpliteObj(int currentPage, int pageMaxRows,
    		String pageMode, String sqlPageMapKey, 
    		HashMap<String,SqlDataPage> sqlPageHashSet){
    	
		SqlDataPage teacherPage = (SqlDataPage)sqlPageHashSet.get(sqlPageMapKey);
		if(teacherPage == null){
			teacherPage = new SqlDataPage();
			teacherPage.setCurrentPage(currentPage); 
			teacherPage.setPageMaxRows(pageMaxRows);
		}
		getPageMode(pageMode, teacherPage);
		System.out.println("当前请求页码是 :" + teacherPage.getCurrentPage());
		
		return teacherPage;
    }
    // 分页获取和更新页码，并将更新后的页码存入MAP
    // currentPage 设置首次运行的 当前页面
 	// pageMaxRows 设置页面最大的显示行数
    private HashMap<String,SqlDataPage>  sessionPageHashMap(String pageMode, 
    		String sqlPageMapKey, 
    		int currentPage, int pageMaxRows,
    		HttpSession session){
    	// 获取存储在session中的 分页Map对象
		HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.getAttribute("sqlPageMap"); // TODO这里使用变量去替代
		if(sqlPageHashSet == null){
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
		}
		
		SqlDataPage teacherPage = getPageSpliteObj(currentPage, pageMaxRows, 
				pageMode, sqlPageMapKey, sqlPageHashSet);
		// list中对新赋值的分页对象，进行存储到list中
		sqlPageHashSet.put(sqlPageMapKey, teacherPage);
		return sqlPageHashSet;
    }
    
    private void queryDate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 获取页面的参数
		String roleIdStr 		= 	request.getParameter("roleId");
		String classIdStr 		= 	request.getParameter("classId");
		String idStr			=  	request.getParameter("id");
		String name 			= 	request.getParameter("name");
		String action			= 	""; // 内部跳转
		String sqlPageMapKey 	=  request.getParameter("sqlPageMapKey"); // 暂不使用
		String pageMode 		= request.getParameter("pageIndex");
		
		// 其他需要的参数
		PrintWriter out = response.getWriter();
		IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		int roleId = 0;
		int classId = 0;
		int id = 0;
		System.out.println("roleIdStr :" + roleIdStr);
		System.out.println("classIdStr :" + classIdStr);
		System.out.println("id :" + id);
		System.out.println("name :" + name);
		System.out.println("action :" + action);
		
		// 参数处理
		if((roleIdStr == null || roleIdStr =="") 
			&& 	(classIdStr == null || classIdStr =="")
			&& 	(idStr == null || idStr =="")
			&& 	(name == null || name =="")
				){
			action = "errorReq";
		}
		// 此处参数处理了，对应数据库设置条件查询的时候的条件判断
		// 只要是全部查询就给0, 为空给-1, 
		// 根据此判断默认全部查询需要的SQL WHERE 语句条件， 关于详细语句看sql函数
		if(roleIdStr.equals("postAll")){
			roleIdStr = "000";
		}
		if(classIdStr.equals("classAll")){
			classIdStr = "000";
		}
		if(roleIdStr == null || roleIdStr == ""){
			roleIdStr = "-1";
		}
		if(classIdStr == null || classIdStr == ""){
			classIdStr = "-1";
		}
		if(idStr == null || idStr == ""){
			idStr = "-1";
		}
		try{
			roleId 		= 	Integer.parseInt(roleIdStr);
			classId 	= 	Integer.parseInt(classIdStr);
			id			= 	Integer.parseInt(idStr);
		}catch(NumberFormatException E){
			System.out.println("参数格式不对");
			action = "errorReq";
		}
		if(action.equals("errorReq")){
			out.write("[]");
			return;
		}
		
		System.out.println("页码模式传参为" + pageMode);
		if(pageMode==null){
			out.write("[]");
			return;
		}
		HashMap<String, SqlDataPage> sqlPageHashSet = 
				sessionPageHashMap(pageMode, sqlPageMapKey, 1, 10, request.getSession());
		
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				(roleId, classId, id, name, sqlPageHashSet.get(sqlPageMapKey));
		if(userList!=null && userList.size()>0){
			out.write("[");
			Iterator<UserInfo> iterator = userList.iterator();
			UserInfo tmp = null;
			while(iterator.hasNext()){
				tmp = iterator.next();
				out.write("{");
				out.write("\"classId\":\""+ tmp.getClassId() +"\"," );
				out.write("\"id\":\""+ tmp.getId() +"\"," );
				out.write("\"name\":\""+ tmp.getName() +"\"," );
				out.write("\"sex\":\""+ tmp.getSex() +"\"," );
				out.write("\"age\":\""+ tmp.getAge() +"\"," );
				out.write("\"email\":\""+ tmp.getEmail() +"\"," );
				out.write("\"telephone\":\""+ tmp.getTelephone() +"\"," );
				out.write("\"roleName\":\""+ tmp.getRoleName() +"\"" );
				if(!iterator.hasNext()){
					out.write("}");
				}else{
					out.write("},");
				}
			}
			out.write("]");
		}else{
			out.write("[]");
		}
		request.getSession().setAttribute("sqlPageMap", sqlPageHashSet);
    }
    
    private void insertData(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	// 添加页面另外需要的数据
    	// TODO 关于任课老师带多个班级的情况
    	String idStr		=  	request.getParameter("id");
		String name 	= 	request.getParameter("name");
		String action 	= 	"";
		String sqlPageMapKey =  request.getParameter("sqlPageMapKey"); // 暂不使用
		String pageMode = request.getParameter("pageIndex");
		int id = -1;
		
		PrintWriter out = response.getWriter();
		IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		if((idStr == null || idStr =="") && 	(name == null || name =="")){
			action = "seacherAll";
		}else{
			try{
				id	= 	Integer.parseInt(idStr);
			}catch(NumberFormatException E){
				System.out.println("参数格式不对");
				action = "errorReq";
			}
		}
		if(action.equals("errorReq")){
			System.out.println("参数有误");
			out.write("[]");
			return;
		}
		
		System.out.println("页码模式传参为" + pageMode);
		if(pageMode==null){
			out.write("[]");
			return;
		}
		HashMap<String, SqlDataPage> sqlPageHashSet = 
				sessionPageHashMap(pageMode, sqlPageMapKey, 1, 10, request.getSession());
		List<UserInfo> userList = null;
		if(action.equals("seacherAll")){
			System.out.println("默认 seacherAll");
			userList = adminTheacherService.queryEecUserOutClass(-1, null, 
					sqlPageHashSet.get(sqlPageMapKey));
		}else{
			userList = adminTheacherService.queryEecUserOutClass(id, name, 
					sqlPageHashSet.get(sqlPageMapKey));
		}
		if(userList!=null && userList.size()>0){
			out.write("[");
			Iterator<UserInfo> iterator = userList.iterator();
			UserInfo tmp = null;
			while(iterator.hasNext()){
				tmp = iterator.next();
				out.write("{");
				out.write("\"id\":\""+ tmp.getId() +"\"," );
				out.write("\"name\":\""+ tmp.getName() +"\"," );
				out.write("\"sex\":\""+ tmp.getSex() +"\"," );
				out.write("\"age\":\""+ tmp.getAge() +"\"," );
				out.write("\"email\":\""+ tmp.getEmail() +"\"," );
				out.write("\"telephone\":\""+ tmp.getTelephone() +"\"," );
				out.write("\"classNames\":[\"20170207\",\"20170201\",\"20170202\"]");
				if(!iterator.hasNext()){
					out.write("}");
				}else{
					out.write("},");
				}
			}
			out.write("]");
		}else{
			out.write("[]");
		}
		request.getSession().setAttribute("sqlPageMap", sqlPageHashSet);
		
    }
    // 添加教师信息，并将信息返回给网页
    private void addTheacherTrueAndRet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	// 参数获取
    	String idStr			=  	request.getParameter("id");
		String classIdStr 		= 	request.getParameter("classId");
		String theacherTypeStr 	= 	request.getParameter("theacherType");
		String action			= 	"";
		int id					= -1;
		int classId 			= -1;
		int theacherType 		= -1;
		PrintWriter out 		= response.getWriter();
		IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		
		System.out.println("id:" + idStr);
		System.out.println("classId:" + classIdStr);
		System.out.println("theacherType:" + theacherTypeStr);
		if(idStr==null || idStr=="" 
				|| classIdStr==null || theacherTypeStr==""
				|| theacherTypeStr==null || theacherTypeStr==""){
			action	= "errorReq";
		}else{
			try{
				id				= 	Integer.parseInt(idStr);
				classId			=	Integer.parseInt(classIdStr);
				theacherType	=	Integer.parseInt(theacherTypeStr);
			}catch(NumberFormatException E){
				System.out.println("参数格式不对");
				action = "errorReq";
			}
		}
		if(action.equals("errorReq")){
			System.out.println("参数错误");
			out.write("false");
			return;
		}
		boolean updFlag = adminTheacherService.updEecUserOutClass(id, classId, theacherType);
		if(updFlag){
			out.write("success");
		}else{
			out.write("updFalse");
		}
    }
    private void delteTheacherById(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	// 参数获取
    	String idStr			=  	request.getParameter("id");
    	String action			= 	"";
		int id					= 	-1;
		PrintWriter out 		= 	response.getWriter();
		IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		System.out.println("id:" + idStr);
		if(idStr==null){
			
		}else{
			try{
				id				= 	Integer.parseInt(idStr);
			}catch(NumberFormatException E){
				System.out.println("参数格式不对");
				action = "errorReq";
			}
		}
		if(action.equals("errorReq")){
			System.out.println("参数错误");
			out.write("false");
			return;
		}
		boolean updFlag = adminTheacherService.deleteTheacher(id);
		if(updFlag){
			out.write("success");
		}else{
			out.write("delFalse");
		}
		
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action 		= 	request.getParameter("action");
		PrintWriter out 	= 	response.getWriter();
		
		// 按需请求
		if(action.equals("getQueryTheacher")){
			queryDate(request, response);
		}else if(action.equals("addTheacher")){
			insertData(request, response);
		}else if(action.equals("addTheacherTrue")){
			addTheacherTrueAndRet(request, response);
		}else if(action.equals("deleteTheacher")){
			
		}else if(action.equals("updateTheacher")){
			
		}else if(action.equals("getQueryClass")){
			
		}else if(action.equals("createClass")){
			
		}else if(action.equals("deleteClass")){
			
		}else if(action.equals("errorReq")){
			out.write("[]");
		}else{ 
			out.write("[]");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
