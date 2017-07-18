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

import net.sf.json.JSONArray;

import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.AdminClassServiceImp;
import com.chinasofti.eecuser.model.service.AdminTheacherServiceImp;
import com.chinasofti.eecuser.model.service.IAdminClassService;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> queryConf;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
    	queryConf = new HashMap<String, String>();
    	queryConf.put("sessionPageMapAttr", "sqlPageMap");
    	queryConf.put("teacherAllPage", "teacherAllPage");
    	queryConf.put("queryDefaultMode", "first");
    }
    
    public void getPageMode(String pageMode, SqlDataPage pageObj){
    	int needPage = 0;
    	switch (pageMode) {
		case "first":
			needPage = 1;
			break;
		case "pre":
			needPage = pageObj.getCurrentPage() -1;
			break;
		case "next":
			needPage = pageObj.getCurrentPage() +1;
			break;
		case "last":
			needPage = pageObj.getMaxPageIndex();
			break;
		default:
			try{
				needPage = Integer.parseInt(pageMode);
    		}catch(NumberFormatException e){
    			System.out.println("传入指定页参数有问题，跳入首页");
    			pageObj.getCustomPage(1);
    		}
			break;
		}
    	pageObj.getCustomPage(needPage);
    }
    
	
    
    // 分页获取和更新页码，并将更新后的页码存入MAP
    // currentPage 设置首次运行的 当前页面
 	// pageMaxRows 设置页面最大的显示行数
    
    
    private SqlDataPage getPageSpliteObj(HashMap<String,SqlDataPage> sqlPageHashSet, 
    		String sqlPageMapKey
    		){
    	SqlDataPage pageObj = (SqlDataPage)sqlPageHashSet.get(sqlPageMapKey);
    	if(pageObj == null){
    		pageObj = new SqlDataPage();
		}
    	return pageObj;
    }
    
    private void setPageSpliteObj(HashMap<String,SqlDataPage> sqlPageHashSet, 
    		String sqlPageMapKey,
    		SqlDataPage pageObj
    		){
    	sqlPageHashSet.put(sqlPageMapKey, pageObj);
    }
    
    private void initPageSpliteObj(int currentPage, int pageMaxRows, SqlDataPage pageObj){
    	pageObj.setCurrentPage(currentPage); 
    	pageObj.setPageMaxRows(pageMaxRows);
    }
    
    private HashMap<String,SqlDataPage> getSessionPageMap(HttpSession session){
    	HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.getAttribute(queryConf.get("sessionPageMapAttr")); // TODO这里使用变量去替代
		if(sqlPageHashSet == null){
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
		}
		return sqlPageHashSet;
    }
    
    private void putSessionPageMap(HttpSession session, HashMap<String,SqlDataPage> sqlPageHashSet){
    	session.setAttribute(queryConf.get("sessionPageMapAttr"), sqlPageHashSet);
    }
    
    private void mableNeed(HttpServletRequest request){
    	int currentPage;
    	int pageMaxRows;	
    	try{
    		currentPage 	= 	Integer.parseInt(request.getParameter("currentPage"));
    		pageMaxRows 	= 	Integer.parseInt(request.getParameter("pageMaxRows"));
		}catch(NumberFormatException E){
			System.out.println("参数格式不对");
		}
    }

    
    private HashMap<String,Object> gotoTeacherManageDefaultParam(){
    	HashMap<String,Object> defaultData = new HashMap<String,Object>();
    	// 参数类型是int值的 -1 代表全部，参数是string值的参数all代表全部， 默认全部查询 
    	defaultData.put("roleId", -1);
    	defaultData.put("classId", -1);
    	defaultData.put("id", -1);
    	defaultData.put("name", null);
    	return defaultData;
    }
    
    private void gotoTeacherManage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	String sqlPageMapKey = request.getParameter("sqlPageMapKey");
    	int currentPage = 1;
    	int pageMaxRows = 3;
    	HashMap<String, Object> defaultParam = gotoTeacherManageDefaultParam();
    	
    	PrintWriter out = response.getWriter();
    	// 获取存储在session中 存储所有分页对象的Map对象
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
    	// 获取存储在分页Map中当前需要的分页对象
    	SqlDataPage pageObj = getPageSpliteObj(sqlPageHashSet, sqlPageMapKey);
    	// pageObj初始化， 初始化每页最大显示行数和首次的页码
    	initPageSpliteObj(1, 3, pageObj);
		
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
		// 后面这两步是不是不需要？， 因为对象本来就存在与session中
		setPageSpliteObj(sqlPageHashSet, sqlPageMapKey, pageObj);
		putSessionPageMap(request.getSession(), sqlPageHashSet);
		// 将数据写入
		request.getSession().setAttribute("teacherAllData", userList);
		//request.getRequestDispatcher("/Z6Admin/TeacherManage.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/Z6Admin/TeacherManage.jsp"); 
    }
    private boolean paramDataCheck(HttpServletRequest request, HashMap<String, Object> defaultParam){
    	// TODO 关于参数全是null的情况
    	try{
    		defaultParam.put("roleId", Integer.parseInt(request.getParameter("roleId"))) ;
    		defaultParam.put("classId", Integer.parseInt(request.getParameter("classId"))) ;
    		defaultParam.put("id", Integer.parseInt(request.getParameter("id"))) ;
    	}catch(NumberFormatException e){
    		System.out.println("参数不符合 int类型");
    		return false;
    	}
    	defaultParam.put("name", request.getParameter("name")) ;
		return true;
    }
    
    private void getQueryTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String sqlPageMapKey = request.getParameter("sqlPageMapKey");
    	int currentPage = 1;
    	int pageMaxRows = 3;
    	PrintWriter out = response.getWriter();
    	HashMap<String, Object> defaultParam = gotoTeacherManageDefaultParam();
    	if(!paramDataCheck(request, defaultParam)){
    		out.write("[]");
    		return;
    	}
    	// 获取存储在session中 存储所有分页对象的Map对象
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
    	// 获取存储在分页Map中当前需要的分页对象
    	SqlDataPage pageObj = getPageSpliteObj(sqlPageHashSet, sqlPageMapKey);
    	// pageObj初始化， 初始化每页最大显示行数和首次的页码
    	initPageSpliteObj(1, 3, pageObj);
		
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
		// 后面这两步是不是不需要？， 因为对象本来就存在与session中
		setPageSpliteObj(sqlPageHashSet, sqlPageMapKey, pageObj);
		putSessionPageMap(request.getSession(), sqlPageHashSet);
		// 将数据写入
		JSONArray fromObject = JSONArray.fromObject(userList);
		out.write(fromObject.toString());
    }
    private HashMap<String, Object> getConditionMap(String sqlPageMapKey, 
    					HttpSession session
    				){
    	String conditionMapKey = sqlPageMapKey+"condition";
    	HashMap<String, Object> defaultParam = (HashMap<String, Object>)session.getAttribute(conditionMapKey);
    	return defaultParam;
    }
    private void setConditionMap(String sqlPageMapKey, 
    							HashMap<String, Object> defaultParam,
    							HttpSession session){
    	String conditionMapKey = sqlPageMapKey+"condition";
    	session.setAttribute(conditionMapKey, defaultParam);
    }
    
    
    private void getDataByPageMode(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String sqlPageMapKey 	= request.getParameter("sqlPageMapKey");
    	String pageMode 		= request.getParameter("pageIndex");
    	// 获取存储在session中 存储所有分页对象的Map对象
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
    	// 获取存储在分页Map中当前需要的分页对象
    	SqlDataPage pageObj = getPageSpliteObj(sqlPageHashSet, sqlPageMapKey);
    	getPageMode(pageMode, pageObj);
    	PrintWriter out = response.getWriter();
    	HashMap<String, Object> defaultParam = getConditionMap(sqlPageMapKey, request.getSession());
    	if(!paramDataCheck(request, defaultParam)){
    		out.write("[]");
    		return;
    	}
    	
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
		// 后面这两步是不是不需要？， 因为对象本来就存在与session中
		setPageSpliteObj(sqlPageHashSet, sqlPageMapKey, pageObj);
		putSessionPageMap(request.getSession(), sqlPageHashSet);
		// 将数据写入
		JSONArray fromObject = JSONArray.fromObject(userList);
		out.write(fromObject.toString());
    	
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action 		= 	request.getParameter("action");
		PrintWriter out 	= 	response.getWriter();
		// 按需请求
		if(action.equals("gotoTeacherManage")){ // 请求有效班级数量
			gotoTeacherManage(request, response);
		}else if(action.equals("getQueryTheacher")){
		
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
