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
		System.out.println("���������û���֤����a");
		String errInfo = "";
		if(userid==null || userid==""){
			errInfo = "�û�������Ϊ��";
		}
		if(pwd==null || pwd==""){
			errInfo += " ���벻��Ϊ��";
		}
		if(!errInfo.equals("")){
			ILoginService loginService=new LoginServiceImp();
			if(userid.matches("[0-9]+")){
				user = loginService.checkUserInfo(Integer.parseInt(userid), pwd);
				if(user == null){
					errInfo = "�û��������������";
				}
			}else{
				errInfo = "�û�����ʽ����";
			}
		}
		//System.out.println("����action ����");
				//req.getSession().setAttribute("errorMsg", "�û��������������");
				//return LOGIN;
				//System.out.println("session ���� attr��");
		//req = ServletActionContext.getRequest();
		ServletActionContext.getServletContext().setAttribute("errorMsg", "�û��������������");
		ServletActionContext.getRequest().setAttribute("errorMsg", "�û��������������");
		ActionContext.getContext().getSession().put("errorMsg", "�û��������������");
		//req.getSession().setAttribute("userInfo", user);
		return LOGIN;
	}

}