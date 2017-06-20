package com.study.struts2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.study.struts2.pojo.UserInfo;

public class PojoModelImpAction extends ActionSupport implements ModelDriven<UserInfo> {

	


	private static final long serialVersionUID = 1L;
	private UserInfo u = new UserInfo(); // �����ֵ���ܸ�ֵ
	
	public PojoModelImpAction(){
		System.out.println("action ��ʼ��");
	}
	
	public UserInfo getU() {
		return u;
	}
	public void setU(UserInfo u) {
		this.u = u;
	}
	
	@Override
	public UserInfo getModel() {
		System.out.println("ModelDirven ��ʽ��ֵ��UserInfoֵ:" + u);
		return u;
	}
	

	@Override
	public String execute() throws Exception {
		System.out.println("execute");
		if(u.getName().equals("ls")){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String execute2() throws Exception {
		System.out.println("execute2");
		if(u.getName().equals("ls")){
			return ERROR;
		}
		return SUCCESS;
	}
	public String execute3() throws Exception {
		System.out.println("execute3");
		if(u.getName().equals("ls")){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String execute4(){
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return SUCCESS;
	}

	public String execute5(){
		System.out.println("ִ��action execute����");
		ActionContext.getContext().getSession().put("userInfo", u);
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
