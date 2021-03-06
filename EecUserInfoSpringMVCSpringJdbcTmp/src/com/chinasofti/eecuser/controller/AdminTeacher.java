package com.chinasofti.eecuser.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class AdminTeacher extends AdminQueryUtils{
	
	public ModelAndView adminTeacherChain(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ServletResponseMethodArgumentResolver
		
		HttpSession session = request.getSession();
		HashMap<String, Object> defaultData = getDefaultParamBySession(session);
		
		initTeacherManageDefaultParam(defaultData);
		SqlDataPage pageObj = getPageSpliteObjBySession(session);
		// 1代表当前页码 3代表没有包含的行数
		initPageSpliteObj(1, 3, pageObj);
		
		List<UserInfo> userList = getDateFromService(request, defaultData, pageObj);
		session.setAttribute("teacherAllData", userList);
		session.setAttribute("teacherPageMax", pageObj.getMaxPageIndex());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Z6Admin/TeacherManage");
		return modelAndView;
	}
	public ModelAndView adminTeacherQueryData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String result = "";
		HttpSession session = request.getSession();
		HashMap<String, Object> defaultData = getDefaultParamBySession(session);
		initTeacherManageDefaultParam(defaultData);
		if(!paramDataCheck(request, defaultData)){
			result = "[]";
			System.out.println("result:" + result);
			out.write(result);
    		return null;
    	}
		
		SqlDataPage pageObj = getPageSpliteObjBySession(session);
		// 1代表当前页码 3代表没有包含的行数
		initPageSpliteObj(1, 3, pageObj);
		
		List<UserInfo> userList = getDateFromService(request, defaultData, pageObj);
    	// 将数据写入
    	JSONArray fromObject = JSONArray.fromObject(userList);
    	// Json数据格式验证  http://www.bejson.com/
    	result = "{\"pageMaxNum\":"+ pageObj.getMaxPageIndex()+", \"data\":"+ fromObject.toString() +"}";
    	System.out.println("result:" + result);
    	out.write(result);
		return null;
	}
	public ModelAndView adminTeacherQueryByPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		HashMap<String, Object> defaultData = getDefaultParamBySession(session);
		SqlDataPage pageObj = getPageSpliteObjBySession(session);
//    	获取分页的页码
    	String pageMode 		= request.getParameter("pageIndex");
//    	获取分页的位置
    	getPageMode(pageMode, pageObj);
		
		List<UserInfo> userList = getDateFromService(request, defaultData, pageObj);
    	// 将数据写入
    	JSONArray fromObject = JSONArray.fromObject(userList);
    	// Json数据格式验证  http://www.bejson.com/
    	String result = "{\"pageMaxNum\":"+ pageObj.getMaxPageIndex()+", \"data\":"+ fromObject.toString() +"}";
    	out.write(result);
		return null;
	}

}
