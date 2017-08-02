package com.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import com.study.struts2.pojo.UserInfo;

public class PojoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private UserInfo u ;
	
	public UserInfo getU() {
		return u;
	}

	public void setU(UserInfo u) {
		this.u = u;
	}

	public String test(){
		System.out.println("给action中对象赋值 ");
		System.out.println("POJO userInfo:" + u.toString());
		return "success";
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("给action中对象赋值 ");
		System.out.println("POJO userInfo: name " + u.getName());
		//System.out.println("POJO userInfo:" + u.toString());
		return SUCCESS;
	}
}
