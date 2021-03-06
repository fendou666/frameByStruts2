package com.chinasofti.eecuser.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class AdminTeacherQueryData extends AdminQueryUtils {

	private static final long serialVersionUID = 1L;
	private String result="";
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		
		HashMap<String, Object> defaultData = getDefaultParamBySession(session);
		initTeacherManageDefaultParam(defaultData);
		if(!paramDataCheck(request, defaultData)){
			result = "[]";
			System.out.println("result:" + result);
    		return SUCCESS;
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
    	return SUCCESS;
	}
	
}
