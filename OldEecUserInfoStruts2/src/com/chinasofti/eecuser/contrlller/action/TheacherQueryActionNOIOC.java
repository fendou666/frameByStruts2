package com.chinasofti.eecuser.contrlller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.AdminTheacherServiceImp;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TheacherQueryActionNOIOC extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	
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
    		Map<String, Object> session){
    	// ��ȡ�洢��session�е� ��ҳMap����
		HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.get("sqlPageMap"); // TODO����ʹ�ñ���ȥ���
		if(sqlPageHashSet == null){
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
		}
		
		SqlDataPage teacherPage = getPageSpliteObj(currentPage, pageMaxRows, 
				pageMode, sqlPageMapKey, sqlPageHashSet);
		// list�ж��¸�ֵ�ķ�ҳ���󣬽��д洢��list��
		sqlPageHashSet.put(sqlPageMapKey, teacherPage);
		return sqlPageHashSet;
    }
    
    private void queryDate(Map request, Map<String, Object> session){
		// ��ȡҳ��Ĳ���
		String roleIdStr 		= 	(String) request.get("roleId");
		String classIdStr 		= 	(String) request.get("classId");
		String idStr			=  	(String) request.get("id");
		String name 			= 	(String) request.get("name");
		String action			= 	""; // �ڲ���ת
		String sqlPageMapKey 	=  (String) request.get("sqlPageMapKey"); // �ݲ�ʹ��
		String pageMode 		= (String) request.get("pageIndex");
		
		// ������Ҫ�Ĳ���
		IAdminTheacherService adminTheacherService = new AdminTheacherServiceImp();
		int roleId = 0;
		int classId = 0;
		int id = 0;
		StringBuffer out = new StringBuffer();
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
			out.append("[]");
			request.put("teacherListJson", out);
			return;
		}
		
		System.out.println("ҳ��ģʽ����Ϊ" + pageMode);
		if(pageMode==null){
			out.append("[]");
			request.put("teacherListJson", out);
			return;
		}
		HashMap<String, SqlDataPage> sqlPageHashSet = 
				sessionPageHashMap(pageMode, sqlPageMapKey, 1, 10, session);
		
		List<UserInfo> userList = adminTheacherService.queryDataByCondition
				(roleId, classId, id, name, sqlPageHashSet.get(sqlPageMapKey));
		if(userList!=null && userList.size()>0){
			out.append("[");
			Iterator<UserInfo> iterator = userList.iterator();
			UserInfo tmp = null;
			while(iterator.hasNext()){
				tmp = iterator.next();
				out.append("{");
				out.append("\"classId\":\""+ tmp.getClassId() +"\"," );
				out.append("\"id\":\""+ tmp.getId() +"\"," );
				out.append("\"name\":\""+ tmp.getName() +"\"," );
				out.append("\"sex\":\""+ tmp.getSex() +"\"," );
				out.append("\"age\":\""+ tmp.getAge() +"\"," );
				out.append("\"email\":\""+ tmp.getEmail() +"\"," );
				out.append("\"telephone\":\""+ tmp.getTelephone() +"\"," );
				out.append("\"roleName\":\""+ tmp.getRoleName() +"\"" );
				if(!iterator.hasNext()){
					out.append("}");
				}else{
					out.append("},");
				}
			}
			out.append("]");
		}else{
			out.append("[]");
		}
		request.put("teacherListJson", out);
		session.put("sqlPageMap", sqlPageHashSet);
		return;
    }
	
	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> application = context.getApplication();
		Map<String, Object> session = context.getSession();
		Map request = (Map) context.get("request");
		queryDate(request, session);
		return SUCCESS; // �ɹ����ͨ��Json�����ж�
	}
}
