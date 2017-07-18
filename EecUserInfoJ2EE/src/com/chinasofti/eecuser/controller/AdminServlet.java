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
    			System.out.println("����ָ��ҳ���������⣬������ҳ");
    			pageObj.getCustomPage(1);
    		}
			break;
		}
    	pageObj.getCustomPage(needPage);
    }
    
	
    
    // ��ҳ��ȡ�͸���ҳ�룬�������º��ҳ�����MAP
    // currentPage �����״����е� ��ǰҳ��
 	// pageMaxRows ����ҳ��������ʾ����
    
    
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
				session.getAttribute(queryConf.get("sessionPageMapAttr")); // TODO����ʹ�ñ���ȥ���
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
			System.out.println("������ʽ����");
		}
    }

    
    private HashMap<String,Object> gotoTeacherManageDefaultParam(){
    	HashMap<String,Object> defaultData = new HashMap<String,Object>();
    	// ����������intֵ�� -1 ����ȫ����������stringֵ�Ĳ���all����ȫ���� Ĭ��ȫ����ѯ 
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
    	// ��ȡ�洢��session�� �洢���з�ҳ�����Map����
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
    	// ��ȡ�洢�ڷ�ҳMap�е�ǰ��Ҫ�ķ�ҳ����
    	SqlDataPage pageObj = getPageSpliteObj(sqlPageHashSet, sqlPageMapKey);
    	// pageObj��ʼ���� ��ʼ��ÿҳ�����ʾ�������״ε�ҳ��
    	initPageSpliteObj(1, 3, pageObj);
		
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
		// �����������ǲ��ǲ���Ҫ���� ��Ϊ�������ʹ�����session��
		setPageSpliteObj(sqlPageHashSet, sqlPageMapKey, pageObj);
		putSessionPageMap(request.getSession(), sqlPageHashSet);
		// ������д��
		request.getSession().setAttribute("teacherAllData", userList);
		//request.getRequestDispatcher("/Z6Admin/TeacherManage.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/Z6Admin/TeacherManage.jsp"); 
    }
    private boolean paramDataCheck(HttpServletRequest request, HashMap<String, Object> defaultParam){
    	// TODO ���ڲ���ȫ��null�����
    	try{
    		defaultParam.put("roleId", Integer.parseInt(request.getParameter("roleId"))) ;
    		defaultParam.put("classId", Integer.parseInt(request.getParameter("classId"))) ;
    		defaultParam.put("id", Integer.parseInt(request.getParameter("id"))) ;
    	}catch(NumberFormatException e){
    		System.out.println("���������� int����");
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
    	// ��ȡ�洢��session�� �洢���з�ҳ�����Map����
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
    	// ��ȡ�洢�ڷ�ҳMap�е�ǰ��Ҫ�ķ�ҳ����
    	SqlDataPage pageObj = getPageSpliteObj(sqlPageHashSet, sqlPageMapKey);
    	// pageObj��ʼ���� ��ʼ��ÿҳ�����ʾ�������״ε�ҳ��
    	initPageSpliteObj(1, 3, pageObj);
		
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
		// �����������ǲ��ǲ���Ҫ���� ��Ϊ�������ʹ�����session��
		setPageSpliteObj(sqlPageHashSet, sqlPageMapKey, pageObj);
		putSessionPageMap(request.getSession(), sqlPageHashSet);
		// ������д��
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
    	// ��ȡ�洢��session�� �洢���з�ҳ�����Map����
    	HashMap<String,SqlDataPage> sqlPageHashSet = getSessionPageMap(request.getSession());
    	// ��ȡ�洢�ڷ�ҳMap�е�ǰ��Ҫ�ķ�ҳ����
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
		// �����������ǲ��ǲ���Ҫ���� ��Ϊ�������ʹ�����session��
		setPageSpliteObj(sqlPageHashSet, sqlPageMapKey, pageObj);
		putSessionPageMap(request.getSession(), sqlPageHashSet);
		// ������д��
		JSONArray fromObject = JSONArray.fromObject(userList);
		out.write(fromObject.toString());
    	
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action 		= 	request.getParameter("action");
		PrintWriter out 	= 	response.getWriter();
		// ��������
		if(action.equals("gotoTeacherManage")){ // ������Ч�༶����
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
