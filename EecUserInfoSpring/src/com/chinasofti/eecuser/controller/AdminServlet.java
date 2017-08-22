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

import org.apache.catalina.core.ApplicationContext;

import net.sf.json.JSONArray;

import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.AdminClassServiceImp;
import com.chinasofti.eecuser.model.service.AdminTheacherServiceImp;
import com.chinasofti.eecuser.model.service.IAdminClassService;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;
import com.sun.glass.ui.Application;

/**
 * Servlet implementation class AdminServlet
 */

//TODO����������ķ����������ֵ������ļ���

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> queryConf;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
    	queryConf = new HashMap<String, String>();
    	// ��ҳ�������
    	queryConf.put("sessionPageMapAttr", "sqlPageMap");
    	queryConf.put("teacherAllPage", "teacherAllPage");
    	queryConf.put("queryDefaultMode", "first");
    	// �����������
    	queryConf.put("sessionDefaultDataMapAttr", "defaultDataMap");
    	queryConf.put("teacherDefaultDate", "teacherDefaultDate");
    }
    // ========================�������===========================
    // ��session�л�ȡ�洢����������Map��Objectָ����һ��dftData������ʼ����������HashMap<String,Object>��
    private HashMap<String,Object> getSessionDefaultDataMap(HttpSession session){
    	HashMap<String,Object> dftDataHashSet  = (HashMap<String,Object>)
				session.getAttribute(queryConf.get("sessionDefaultDataMapAttr")); // TODO����ʹ�ñ���ȥ���
		if(dftDataHashSet == null){
			dftDataHashSet = new HashMap<String,Object>();
			session.setAttribute(queryConf.get("sessionDefaultDataMapAttr"), dftDataHashSet);
		}
		return dftDataHashSet;
    }
    
    // ����������Map�л�ȡ��Ҫ����������// ��ҳ��ȡ�͸���ҳ�룬�������º��ҳ�����MAP��
    private HashMap<String,Object> getDftDataObjObj(HashMap<String,Object> dftDataHashSet, 
    		String dftDataMapKey
    		){
    	HashMap<String,Object> dftDataObj = (HashMap<String,Object>)dftDataHashSet.get(dftDataMapKey);
    	if(dftDataObj == null){
    		dftDataObj = new HashMap<String,Object>();
    		dftDataHashSet.put(dftDataMapKey, dftDataObj);
		}
    	return dftDataObj;
    }
    // ��session�л�ȡ��������
    private HashMap<String,Object>  getDefaultParamBySession(HttpSession session){
    	HashMap<String, Object> sessionDefaultDataMap = getSessionDefaultDataMap(session);
    	String dftDataMapKey = queryConf.get("teacherDefaultDate");
    	return getDftDataObjObj(sessionDefaultDataMap, dftDataMapKey);
    }
    
    // Ĭ��������ʼ���� ����Ĭ������
    private void  initTeacherManageDefaultParam(HashMap<String,Object> defaultData){
    	// ����������intֵ�� -1 ����ȫ����������stringֵ�Ĳ���all����ȫ���� Ĭ��ȫ����ѯ 
    	defaultData.put("roleId", -1);
    	defaultData.put("classId", -1);
    	defaultData.put("id", -1);
    	defaultData.put("name", null);
    }
    
    // ʵ��������Ĭ���������
    private boolean paramDataCheck(HttpServletRequest request, HashMap<String, Object> defaultParam){
    	String roleId = request.getParameter("roleId");
    	String classId = request.getParameter("classId");
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	// TODO ���ڲ���ȫ��null�����
    	try{
    		if(roleId!=null && !roleId.equals("")){
    			defaultParam.put("roleId", Integer.parseInt(roleId)) ;
    		}
    		if(classId!=null && !classId.equals("")){
    			defaultParam.put("classId", Integer.parseInt(classId)) ;
    		}
    		if(id!=null && !id.equals("")){
    			defaultParam.put("id", Integer.parseInt(id));
    		}
    	}catch(NumberFormatException e){
    		System.out.println("���������� int����");
    		return false;
    	}
    	System.out.println("nameΪ" +name );
    	if(name!=null && !name.equals("")){
    		defaultParam.put("name", request.getParameter("name")) ;
    	}
		return true;
    }
    
    
    //==========================��ҳ���=============================
    
    // ��session�л�ȡ�洢���з�ҳ��Map
    private HashMap<String,SqlDataPage> getSessionPageMap(HttpSession session){
    	HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.getAttribute(queryConf.get("sessionPageMapAttr")); // TODO����ʹ�ñ���ȥ���
		if(sqlPageHashSet == null){
			// session��û�з�ҳ�����ʱ��ͽ��䴴����������session������
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
			session.setAttribute(queryConf.get("sessionPageMapAttr"), sqlPageHashSet);
		
		}
		return sqlPageHashSet;
    }
    // �����з�ҳMap�л�ȡ��Ҫ�ķ�ҳ����// ��ҳ��ȡ�͸���ҳ�룬�������º��ҳ�����MAP��
    private SqlDataPage getPageSpliteObj(HashMap<String,SqlDataPage> sqlPageHashSet, 
    		String sqlPageMapKey
    		){
    	SqlDataPage pageObj = (SqlDataPage)sqlPageHashSet.get(sqlPageMapKey);
    	if(pageObj == null){
    		// ��ҳ��û����Ҫ�ķ�ҳ��ʱ�����ҳ������������session��ҳMap��
    		pageObj = new SqlDataPage();
    		sqlPageHashSet.put(sqlPageMapKey, pageObj);
		}
    	return pageObj;
    }
    // ��session�л�ȡ��Ҫ�ķ�ҳ���� �൱�ڵ���������������
    private SqlDataPage getPageSpliteObjBySession(HttpSession session){
    	HashMap<String, SqlDataPage> sessionPageMap = getSessionPageMap(session);
    	String sqlPageMapKey = queryConf.get("teacherAllPage");
    	return getPageSpliteObj(sessionPageMap, sqlPageMapKey);
    }
    
    // ��һ��������ҳ��Ҫ���жԷ�ҳ��ʼ��    
    // currentPage �����״����е� ��ǰҳ��
 	// pageMaxRows ����ҳ��������ʾ����
    private void initPageSpliteObj(int currentPage, int pageMaxRows, SqlDataPage pageObj){
    	pageObj.setCurrentPage(currentPage); 
    	pageObj.setPageMaxRows(pageMaxRows);
    }
    
    //  ��ȡ��ҳģʽ�� ���ݷ�ҳģʽ���Է�ҳ������и�ֵ��Ӧ�ķ�ҳ
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
    
    private List<UserInfo> getDateFromService(HttpServletRequest request,
			HashMap<String, Object> defaultParam,
			SqlDataPage pageObj){
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
    	return  adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
    }
    // ����servlet����ҳ����ת
	private void gotoPage(HttpServletRequest request,
							HttpServletResponse response,
							HashMap<String, Object> defaultParam,
							SqlDataPage pageObj) throws IOException{
		List<UserInfo> userList = getDateFromService(request, defaultParam, pageObj);
		// ������д��
		request.getSession().setAttribute("teacherAllData", userList);
		request.getSession().setAttribute("teacherPageMax", pageObj.getMaxPageIndex());
		//request.getRequestDispatcher("/Z6Admin/TeacherManage.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/Z6Admin/TeacherManage.jsp"); 
	}
	// ����servlet���оֲ���ˢ��
    private void ajaxStrStream(HttpServletRequest request,
			HttpServletResponse response,
			HashMap<String, Object> defaultParam,
			SqlDataPage pageObj) throws IOException{
    	List<UserInfo> userList = getDateFromService(request, defaultParam, pageObj);
    	// ������д��
    	JSONArray fromObject = JSONArray.fromObject(userList);
    	// Json���ݸ�ʽ��֤  http://www.bejson.com/
    	String result = "{\"pageMaxNum\":"+ pageObj.getMaxPageIndex()+", \"data\":"+ fromObject.toString() +"}";
    	response.getWriter().write(result);
    }
    private void teacherInfoQuery(HttpServletRequest request, HttpServletResponse response, boolean goPage) throws IOException, ServletException{
    	// ��ȡĬ�ϲ����������г�ʼ��
    	HashMap<String, Object> defaultParam = getDefaultParamBySession(request.getSession());
    	initTeacherManageDefaultParam(defaultParam);
    	if(!goPage && !paramDataCheck(request, defaultParam)){
    		response.getWriter().write("[]");
    		return;
    	}
    	System.out.println("name:" + defaultParam.get("name"));
    	// ��ȡ��ҳ���󲢽��г�ʼ��
    	int currentPage = 1;
    	int pageMaxRows = 3;
    	SqlDataPage pageObj = getPageSpliteObjBySession(request.getSession());
    	// pageObj��ʼ���� ��ʼ��ÿҳ�����ʾ�������״ε�ҳ��
    	initPageSpliteObj(1, 3, pageObj);
    	if(goPage){
    		gotoPage(request, response, defaultParam, pageObj);
    	}else{
    		ajaxStrStream(request, response, defaultParam, pageObj);
    	}
    }
    
    private void getDataByPageMode(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	// ��ȡĬ�ϲ����������г�ʼ��
    	HashMap<String, Object> defaultParam = getDefaultParamBySession(request.getSession());
    	// ��ȡ�洢�ڷ�ҳ�еĶ���
    	SqlDataPage pageObj = getPageSpliteObjBySession(request.getSession());
//    	��ȡ��ҳ��ҳ��
    	String pageMode 		= request.getParameter("pageIndex");
//    	��ȡ��ҳ��λ��
    	getPageMode(pageMode, pageObj);
    	ajaxStrStream(request, response, defaultParam, pageObj);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action 		= 	request.getParameter("action");
		PrintWriter out 	= 	response.getWriter();
		// ��������
		if(action.equals("gotoTeacherManage")){ // �����ʦ�� �����ֱ����תҳ��
			teacherInfoQuery(request, response, true);
		}else if(action.equals("getQueryTheacher")){ // �����ajax��ˢ��
			teacherInfoQuery(request, response, false);
		}else if(action.equals("getQueryTheacherByPage")){ // �����ajax��ˢ��
			getDataByPageMode(request, response);
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