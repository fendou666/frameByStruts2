package com.study.struts2.action;

import com.opensymphony.xwork2.ModelDriven;
import com.study.struts2.pojo.UserInfo;

public class PojoModelImpAction implements ModelDriven<UserInfo> {

	

	private UserInfo u = new UserInfo(); // �����ֵ���ܸ�ֵ
	
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
	

	public String test(){
		System.out.println("��action�ж���ͨ��ModelDirven��ֵ ");
		return "success";
	}
	

}
