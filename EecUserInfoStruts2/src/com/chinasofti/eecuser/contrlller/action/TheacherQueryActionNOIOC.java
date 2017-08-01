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
    		Map<String, Object> session){
    	// 获取存储在session中的 分页Map对象
		HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.get("sqlPageMap"); // TODO这里使用变量去替代
		if(sqlPageHashSet == null){
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
		}
		
		SqlDataPage teacherPage = getPageSpliteObj(currentPage, pageMaxRows, 
				pageMode, sqlPageMapKey, sqlPageHashSet);
		// list中对新赋值的分页对象，进行存储到list中
		sqlPageHashSet.put(sqlPageMapKey, teacherPage);
		return sqlPageHashSet;
    }
    
    private void queryDate(Map request, Map<String, Object> session){
		// 获取页面的参数
		String roleIdStr 		= 	(String) request.get("roleId");
		String classIdStr 		= 	(String) request.get("classId");
		String idStr			=  	(String) request.get("id");
		String name 			= 	(String) request.get("name");
		String action			= 	""; // 内部跳转
		String sqlPageMapKey 	=  (String) request.get("sqlPageMapKey"); // 暂不使用
		String pageMode 		= (String) request.get("pageIndex");
		
		// 其他需要的参数
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
			out.append("[]");
			request.put("teacherListJson", out);
			return;
		}
		
		System.out.println("页码模式传参为" + pageMode);
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
		return SUCCESS; // 成功与否通过Json数据判断
	}
}
