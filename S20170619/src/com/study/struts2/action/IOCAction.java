package com.study.struts2.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class IOCAction extends ActionSupport implements SessionAware, RequestAware,  {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		
		
		return SUCCESS;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
	
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		
	}
	
}
