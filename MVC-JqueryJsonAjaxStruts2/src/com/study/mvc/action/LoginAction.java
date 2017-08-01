package com.study.mvc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.bean.UserInfo;
import com.study.mvc.model.service.ILoginService;
import com.study.mvc.model.service.LoginServiceImp;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 6063585412168144980L;
	private int userid;
	private String pwd;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public void validate() {
		ILoginService loginService = new LoginServiceImp();
		UserInfo userInfo = loginService.checkUserInfo(userid, pwd);
		ActionContext.getContext().getSession().put("userInfo", userInfo);
		if(userInfo==null){
			addActionError("用户名或者密码错误！！！");
		}
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		System.out.println("userId:"+userid);
		System.out.println("pwd:"+pwd);
		return SUCCESS;
	}
	
	
}
