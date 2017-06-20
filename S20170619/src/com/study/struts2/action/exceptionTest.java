package com.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.study.struts2.exception.Myexcetion;
import com.study.struts2.pojo.UserInfo;

public class exceptionTest extends ActionSupport implements ModelDriven<UserInfo> {
	

	private static final long serialVersionUID = 1L;

	public UserInfo getU() {
		return u;
	}

	public void setU(UserInfo u) {
		this.u = u;
	}

	private UserInfo u = new UserInfo();
	
	@Override
	public UserInfo getModel() {
		return u;
	}

	@Override
	public String execute() throws Myexcetion {
		if(u.getName().equals("刘帅")){
			throw new Myexcetion("用户名不可为刘帅");
		}
		return SUCCESS;
	}
}
