package com.chinasofti.eecuser.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminTeacherChain extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private AdminQueryUtils aUtil;
	public AdminQueryUtils getaUtil() {
		return aUtil;
	}
	public void setaUtil(AdminQueryUtils aUtil) {
		this.aUtil = aUtil;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		
		HashMap<String, Object> defaultData = aUtil.getDefaultParamBySession(session);
		aUtil.initTeacherManageDefaultParam(defaultData);
		SqlDataPage pageObj = aUtil.getPageSpliteObjBySession(session);
		// 1代表当前页码 3代表没有包含的行数
		aUtil.initPageSpliteObj(1, 3, pageObj);
		
		List<UserInfo> userList = aUtil.getDateFromService(request, defaultData, pageObj);
		session.setAttribute("teacherAllData", userList);
		session.setAttribute("teacherPageMax", pageObj.getMaxPageIndex());
		return SUCCESS;
	}
	
}
