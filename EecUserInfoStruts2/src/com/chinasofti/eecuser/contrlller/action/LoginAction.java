package com.chinasofti.eecuser.contrlller.action;

import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.ILoginService;
import com.chinasofti.eecuser.model.service.LoginServiceImp;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String id;
	private String pwd;
	private ILoginService loginService = new LoginServiceImp();
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("Ö´ÐÐ execute·½·¨");
		System.out.println("id:"+id);
		System.out.println("pwd:" +pwd);
		if(id==null || id=="" || pwd == null || pwd == ""){
			return NONE;
		}
		if(loginService.checkLoginInfo()){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
