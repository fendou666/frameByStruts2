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
@WebServlet("/CPAdminServlet")
public class CopyOfAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> queryConf;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CopyOfAdminServlet() {
    	queryConf = new HashMap<String, String>();
    	queryConf.put("sessionPageMapAttr", "sqlPageMap");
    	queryConf.put("teacherAllPage", "teacherAllPage");
    	queryConf.put("queryDefaultMode", "first");
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
    			System.out.println("����ָ��ҳ���������⣬������ҳ");
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
		System.out.println("��ǰ����ҳ���� :" + teacherPage.getCurrentPage());
		
		return teacherPage;
    }
    // ��ҳ��ȡ�͸���ҳ�룬�������º��ҳ�����MAP
    // currentPage �����״����е� ��ǰҳ��
 	// pageMaxRows ����ҳ��������ʾ����
    private HashMap<String,SqlDataPage>  sessionPageHashMap(String pageMode, 
    		String sqlPageMapKey, 
    		int currentPage, int pageMaxRows,
    		HttpSession session){
    	// ��ȡ�洢��session�е� ��ҳMap����
		HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.getAttribute("sqlPageMap"); // TODO����ʹ�ñ���ȥ���
		if(sqlPageHashSet == null){
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
		}
		
		SqlDataPage teacherPage = getPageSpliteObj(currentPage, pageMaxRows, 
				pageMode, sqlPageMapKey, sqlPageHashSet);
		// list�ж��¸�ֵ�ķ�ҳ���󣬽��д洢��list��
		sqlPageHashSet.put(sqlPageMapKey, teacherPage);
		return sqlPageHashSet;
    }
    
    private void queryDateAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	PrintWriter out = response.getWriter();
    	IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
    	HashMap<String, SqlDataPage> sqlPageHashSet = 
				sessionPageHashMap("first", "teacherDate", 1, 3, request.getSession());
		
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
    
    private void queryDate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// ��ȡҳ��Ĳ���
		String roleIdStr 		= 	request.getParameter("roleId");
		String classIdStr 		= 	request.getParameter("classId");
		String idStr			=  	request.getParameter("id");
		String name 			= 	request.getParameter("name");
		String action			= 	""; // �ڲ���ת
		String sqlPageMapKey 	=  request.getParameter("sqlPageMapKey"); // �ݲ�ʹ��
		String pageMode 		= request.getParameter("pageIndex");
		
		// ������Ҫ�Ĳ���
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
		
		// ��������
		if((roleIdStr == null || roleIdStr =="") 
			&& 	(classIdStr == null || classIdStr =="")
			&& 	(idStr == null || idStr =="")
			&& 	(name == null || name =="")
				){
			action = "errorReq";
		}
		// �˴����������ˣ���Ӧ���ݿ�����������ѯ��ʱ��������ж�
		// ֻҪ��ȫ����ѯ�͸�0, Ϊ�ո�-1, 
		// ���ݴ��ж�Ĭ��ȫ����ѯ��Ҫ��SQL WHERE ��������� ������ϸ��俴sql����
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
			System.out.println("������ʽ����");
			action = "errorReq";
		}
		if(action.equals("errorReq")){
			out.write("[]");
			return;
		}
		
		System.out.println("ҳ��ģʽ����Ϊ" + pageMode);
		if(pageMode==null){
			out.write("[]");
			return;
		}
		HashMap<String, SqlDataPage> sqlPageHashSet = 
				sessionPageHashMap(pageMode, sqlPageMapKey, 1, 3, request.getSession());
		
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
    
    
    private void gotoTeacherManage(HttpServletRequest request, HttpServletResponse response){
    	request.getSession().getAttribute()
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action 		= 	request.getParameter("action");
		PrintWriter out 	= 	response.getWriter();
		// ��������
		if(action.equals("gotoTeacherManage")){ // ������Ч�༶����
			
		}else if(action.equals("getQueryTheacher")){
			queryDate(request, response);
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