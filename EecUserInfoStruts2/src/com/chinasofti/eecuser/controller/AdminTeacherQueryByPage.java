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

public class AdminTeacherQueryByPage extends AdminQueryUtils {
	private static final long serialVersionUID = 1L;
	private String result="";
	private int maxPage = 0;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
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
    	result = "{\"pageMaxNum\":"+ pageObj.getMaxPageIndex()+", \"data\":"+ fromObject.toString() +"}";
    	maxPage = pageObj.getMaxPageIndex();
    	System.out.println("maxPage:" + maxPage);
    	
    	System.out.println("result:" + result);
    	return SUCCESS;
	}
}
