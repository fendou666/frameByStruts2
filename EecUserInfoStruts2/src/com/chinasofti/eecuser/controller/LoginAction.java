package com.chinasofti.eecuser.controller;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String userPermission;
	
	public String getUserPermission() {
		return userPermission;
	}
	public void setUserPermission(String userPermission) {
		this.userPermission = userPermission;
	}

	@Override
	public String execute() throws Exception {
		if(userPermission!=null){
			if(userPermission.equals("0")){
				return "adminPage";
			}else{
				return "error";
			}
		}else{
			return "error";
		}
	}
	
	
	
}
