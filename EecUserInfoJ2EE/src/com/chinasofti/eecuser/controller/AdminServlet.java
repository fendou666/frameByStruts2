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
    	// 分页相关配置
    	queryConf.put("sessionPageMapAttr", "sqlPageMap");
    	queryConf.put("teacherAllPage", "teacherAllPage");
    	queryConf.put("queryDefaultMode", "first");
    	// 条件相关配置
    	queryConf.put("sessionDefaultDataMapAttr", "defaultDataMap");
    	queryConf.put("teacherDefaultDate", "teacherDefaultDate");
    }
    
    //==========================分页相关=============================
    
    // 从session中获取存储所有分页的Map
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
    // 从所有分页Map中获取需要的分页对象（// 分页获取和更新页码，并将更新后的页码存入MAP）
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
    // 第一次条件分页需要进行对分页初始化    
    // currentPage 设置首次运行的 当前页面
 	// pageMaxRows 设置页面最大的显示行数
    private void initPageSpliteObj(int currentPage, int pageMaxRows, SqlDataPage pageObj){
    	pageObj.setCurrentPage(currentPage); 
    	pageObj.setPageMaxRows(pageMaxRows);
    }
    
    //  获取分页模式， 根据分页模式，对分页对象进行赋值相应的分页
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
    
    // ========================条件相关===========================
    // 从session中获取存储所有条件的Map，Object指的是一个dftData对象（起始它的类型是HashMap<String,Object>）
    private HashMap<String,Object> getSessionDefaultDataMap(HttpSession session){
    	HashMap<String,Object> dftDataHashSet  = (HashMap<String,Object>)
				session.getAttribute(queryConf.get("sessionDefaultDataMapAttr")); // TODO这里使用变量去替代
		if(dftDataHashSet == null){
			dftDataHashSet = new HashMap<String,Object>();
		}
		return dftDataHashSet;
    }
    
    private void putSessionDefaultDataMap(HttpSession session, HashMap<String,Object> dftDataHashSet){
    	session.setAttribute(queryConf.get("sessionDefaultDataMapAttr"), dftDataHashSet);
    }
    // 从所有条件Map中获取需要的条件对象（// 分页获取和更新页码，并将更新后的页码存入MAP）
    private HashMap<String,Object> getDftDataObjObj(HashMap<String,Object> dftDataHashSet, 
    		String dftDataMapKey
    		){
    	HashMap<String,Object> dftDataObj = (HashMap<String,Object>)dftDataHashSet.get(dftDataMapKey);
    	if(dftDataObj == null){
    		dftDataObj = new HashMap<String,Object>();
		}
    	return dftDataObj;
    }
    
    private void setPageSpliteObj(HashMap<String,Object> dftDataHashSet, 
    		String dftDataMapKey,
    		HashMap<String,Object> dftDataObj
    		){
    	dftDataHashSet.put(dftDataMapKey, dftDataObj);
    }
    
    
    // 默认条件初始化， 设置默认条件
    private HashMap<String,Object>  gotoTeacherManageDefaultParam(HttpSession session){
    	String dftDataMapKey = queryConf.get("teacherDefaultDate");
    	HashMap<String,Object> defaultData = getDftDataObjObj(getSessionDefaultDataMap(session), dftDataMapKey);
    	// 参数类型是int值的 -1 代表全部，参数是string值的参数all代表全部， 默认全部查询 
    	defaultData.put("roleId", -1);
    	defaultData.put("classId", -1);
    	defaultData.put("id", -1);
    	defaultData.put("name", null);
    	return defaultData;
    }
    
    // 实际条件与默认条件结合
    private boolean paramDataCheck(HttpServletRequest request, HashMap<String, Object> defaultParam){
    	// TODO 关于参数全是null的情况
    	try{
    		defaultParam.put("roleId", Integer.parseInt(request.getParameter("roleId"))) ;
    		defaultParam.put("classId", Integer.parseInt(request.getParameter("classId"))) ;
    		defaultParam.put("id", Integer.parseInt(request.getParameter("id")));
    	}catch(NumberFormatException e){
    		System.out.println("参数不符合 int类型");
    		return false;
    	}
    	defaultParam.put("name", request.getParameter("name")) ;
		return true;
    }
    
    private void gotoTeacherManage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	String sqlPageMapKey = request.getParameter("sqlPageMapKey");
    	int currentPage = 1;
    	int pageMaxRows = 3;
    	HashMap<String, Object> defaultParam = gotoTeacherManageDefaultParam(request.getSession());
    	
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
    
//  TODO怎样将这里的方法进行区分到其他文件呢
    private void setSessionPageDefaultParam(){
    	
    }
    
    private void getQueryTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	System.out.println("进入自定条件请求");
    	String sqlPageMapKey = request.getParameter("sqlPageMapKey");
    	int currentPage = 1;
    	int pageMaxRows = 3;
    	PrintWriter out = response.getWriter();
    	HashMap<String, Object> defaultParam =  gotoTeacherManageDefaultParam(request.getSession());
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
<<<<<<< HEAD
    private void getTeacherByPageIndex(HttpServletRequest request, HttpServletResponse response){
//    	获取分页的key
    	String sqlPageMapKey 	= request.getParameter("sqlPageMapKey");
//    	获取分页的页码
    	String pageMode 		= request.getParameter("pageIndex");
//    	获取存储在session中的分页对象Map
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
//    	获取当前分页key对应的分页对象
    	SqlDataPage pageObj = getPageSpliteObj(sqlPageHashSet, sqlPageMapKey);
//    	获取分页的位置
    	getPageMode(pageMode, pageObj);
//    	获取当前分页对应的条件
    	// TODO这里的条件对象可以定义为一个对象，里面包含各种条件的数组?
    	
=======
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
>>>>>>> 2b137ed67297ad2e4f3e20162c47fa64835bfbaf
    	
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action 		= 	request.getParameter("action");
		PrintWriter out 	= 	response.getWriter();
		// 按需请求
		if(action.equals("gotoTeacherManage")){ // 请求有效班级数量
			gotoTeacherManage(request, response);
		}else if(action.equals("getQueryTheacher")){
			getQueryTeacher(request, response);
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
