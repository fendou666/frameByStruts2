package com.study.mvc.controller.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.bean.UserInfo;
import com.study.mvc.model.service.ILoginService;
import com.study.mvc.model.service.LoginServiceImp;

public class LoginAjaxAction extends ActionSupport  {

	private static final long serialVersionUID = 1L;
	private String userid;
	private String pwd;
	private HttpServletRequest req;
	private UserInfo user;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("进入请求用户验证方法a");
		String errInfo = "";
		if(userid==null || userid==""){
			errInfo = "用户名不能为空";
		}
		if(pwd==null || pwd==""){
			errInfo += " 密码不能为空";
		}
		if(!errInfo.equals("")){
			ILoginService loginService=new LoginServiceImp();
			if(userid.matches("[0-9]+")){
				user = loginService.checkUserInfo(Integer.parseInt(userid), pwd);
				if(user == null){
					errInfo = "用户名或者密码错误";
				}
			}else{
				errInfo = "用户名格式不对";
			}
		}
		//System.out.println("进入action 请求");
				//req.getSession().setAttribute("errorMsg", "用户名或者密码错误");
				//return LOGIN;
				//System.out.println("session 设置 attr后");
		//req = ServletActionContext.getRequest();
		ServletActionContext.getServletContext().setAttribute("errorMsg", "用户名或者密码错误");
		ServletActionContext.getRequest().setAttribute("errorMsg", "用户名或者密码错误");
		ActionContext.getContext().getSession().put("errorMsg", "用户名或者密码错误");
		//req.getSession().setAttribute("userInfo", user);
		return LOGIN;
	}

}
