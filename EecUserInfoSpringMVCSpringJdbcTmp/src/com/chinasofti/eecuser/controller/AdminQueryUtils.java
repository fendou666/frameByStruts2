package com.chinasofti.eecuser.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import net.sf.json.JSONArray;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.AdminTheacherServiceImp;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AdminQueryUtils extends MultiActionController {

	private static final long serialVersionUID = 1L;
	private AdminTheacherServiceImp adminTheacherService;
	
	public AdminTheacherServiceImp getAdminTheacherService() {
		return adminTheacherService;
	}
	public void setAdminTheacherService(AdminTheacherServiceImp adminTheacherService) {
		this.adminTheacherService = adminTheacherService;
	}
	
	
	private HashMap<String, String> queryConf;
	
	public AdminQueryUtils() {
		queryConf = new HashMap<String, String>();
    	// 分页相关配置
    	queryConf.put("sessionPageMapAttr", "sqlPageMap");
    	queryConf.put("teacherAllPage", "teacherAllPage");
    	queryConf.put("queryDefaultMode", "first");
    	// 条件相关配置
    	queryConf.put("sessionDefaultDataMapAttr", "defaultDataMap");
    	queryConf.put("teacherDefaultDate", "teacherDefaultDate");
    }
    // ========================条件相关===========================
    // 从session中获取存储所有条件的Map，Object指的是一个dftData对象（起始它的类型是HashMap<String,Object>）
	public HashMap<String,Object> getSessionDefaultDataMap(HttpSession session){
    	HashMap<String,Object> dftDataHashSet  = (HashMap<String,Object>)
				session.getAttribute(queryConf.get("sessionDefaultDataMapAttr")); // TODO这里使用变量去替代
		if(dftDataHashSet == null){
			dftDataHashSet = new HashMap<String,Object>();
			session.setAttribute(queryConf.get("sessionDefaultDataMapAttr"), dftDataHashSet);
		}
		return dftDataHashSet;
    }
    
    // 从所有条件Map中获取需要的条件对象（// 分页获取和更新页码，并将更新后的页码存入MAP）
    public HashMap<String,Object> getDftDataObjObj(HashMap<String,Object> dftDataHashSet, 
    		String dftDataMapKey
    		){
    	HashMap<String,Object> dftDataObj = (HashMap<String,Object>)dftDataHashSet.get(dftDataMapKey);
    	if(dftDataObj == null){
    		dftDataObj = new HashMap<String,Object>();
    		dftDataHashSet.put(dftDataMapKey, dftDataObj);
		}
    	return dftDataObj;
    }
    // 从session中获取条件对象
    public HashMap<String,Object>  getDefaultParamBySession(HttpSession session){
    	HashMap<String, Object> sessionDefaultDataMap = getSessionDefaultDataMap(session);
    	String dftDataMapKey = queryConf.get("teacherDefaultDate");
    	return getDftDataObjObj(sessionDefaultDataMap, dftDataMapKey);
    }
    
    // 默认条件初始化， 设置默认条件
    public void  initTeacherManageDefaultParam(HashMap<String,Object> defaultData){
    	// 参数类型是int值的 -1 代表全部，参数是string值的参数all代表全部， 默认全部查询 
    	defaultData.put("roleId", -1);
    	defaultData.put("classId", -1);
    	defaultData.put("id", -1);
    	defaultData.put("name", null);
    }
    
    // 实际条件与默认条件结合
    public boolean paramDataCheck(HttpServletRequest request, HashMap<String, Object> defaultParam){
    	String roleId = request.getParameter("roleId");
    	String classId = request.getParameter("classId");
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	// TODO 关于参数全是null的情况
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
    		System.out.println("参数不符合 int类型");
    		return false;
    	}
    	System.out.println("name为" +name );
    	if(name!=null && !name.equals("")){
    		defaultParam.put("name", request.getParameter("name")) ;
    	}
		return true;
    }
    
    
    //==========================分页相关=============================
    
    // 从session中获取存储所有分页的Map
    public HashMap<String,SqlDataPage> getSessionPageMap(HttpSession session){
    	HashMap<String,SqlDataPage> sqlPageHashSet  = (HashMap<String,SqlDataPage>)
				session.getAttribute(queryConf.get("sessionPageMapAttr")); // TODO这里使用变量去替代
		if(sqlPageHashSet == null){
			// session中没有分页对象的时候就将其创建，并放入session对象中
			sqlPageHashSet = new HashMap<String,SqlDataPage>();
			session.setAttribute(queryConf.get("sessionPageMapAttr"), sqlPageHashSet);
		
		}
		return sqlPageHashSet;
    }
    // 从所有分页Map中获取需要的分页对象（// 分页获取和更新页码，并将更新后的页码存入MAP）
    public SqlDataPage getPageSpliteObj(HashMap<String,SqlDataPage> sqlPageHashSet, 
    		String sqlPageMapKey
    		){
    	SqlDataPage pageObj = (SqlDataPage)sqlPageHashSet.get(sqlPageMapKey);
    	if(pageObj == null){
    		// 分页中没有需要的分页的时候将其分页创建，并放入session分页Map中
    		pageObj = new SqlDataPage();
    		sqlPageHashSet.put(sqlPageMapKey, pageObj);
		}
    	return pageObj;
    }
    // 从session中获取需要的分页对象， 相当于调用上面两个方法
    public SqlDataPage getPageSpliteObjBySession(HttpSession session){
    	HashMap<String, SqlDataPage> sessionPageMap = getSessionPageMap(session);
    	String sqlPageMapKey = queryConf.get("teacherAllPage");
    	return getPageSpliteObj(sessionPageMap, sqlPageMapKey);
    }
    
    
    // 第一次条件分页需要进行对分页初始化    
    // currentPage 设置首次运行的 当前页面
 	// pageMaxRows 设置页面最大的显示行数
    public void initPageSpliteObj(int currentPage, int pageMaxRows, SqlDataPage pageObj){
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
    
    public List<UserInfo> getDateFromService(HttpServletRequest request,
			HashMap<String, Object> defaultParam,
			SqlDataPage pageObj){
    	return  adminTheacherService.queryDataByCondition
				((int)defaultParam.get("roleId"), 
				 (int)defaultParam.get("classId"), 
				 (int)defaultParam.get("id"),
				 (String)defaultParam.get("name"),
				 pageObj);
    }
    // 请求servlet进行页面跳转
    public void gotoPage(HttpServletRequest request,
							HttpServletResponse response,
							HashMap<String, Object> defaultParam,
							SqlDataPage pageObj) throws IOException{
		List<UserInfo> userList = getDateFromService(request, defaultParam, pageObj);
		// 将数据写入
		request.getSession().setAttribute("teacherAllData", userList);
		request.getSession().setAttribute("teacherPageMax", pageObj.getMaxPageIndex());
		//request.getRequestDispatcher("/Z6Admin/TeacherManage.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/Z6Admin/TeacherManage.jsp"); 
	}
	// 请求servlet进行局部流刷新
    public void ajaxStrStream(HttpServletRequest request,
			HttpServletResponse response,
			HashMap<String, Object> defaultParam,
			SqlDataPage pageObj) throws IOException{
    	List<UserInfo> userList = getDateFromService(request, defaultParam, pageObj);
    	// 将数据写入
    	JSONArray fromObject = JSONArray.fromObject(userList);
    	// Json数据格式验证  http://www.bejson.com/
    	String result = "{\"pageMaxNum\":"+ pageObj.getMaxPageIndex()+", \"data\":"+ fromObject.toString() +"}";
    	response.getWriter().write(result);
    }
	
}
