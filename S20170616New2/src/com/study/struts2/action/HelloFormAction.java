package com.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloFormAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String pwd;
	private String message;
	
	@Override
	public String execute() throws Exception {
		message = "我是HelloFormAction里面的message属性";
		System.out.println("userName 为:" + userName);
		if(userName.equals("刘帅")){
			return SUCCESS;
		}else{
			return ERROR;
		}
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

	

	
}
