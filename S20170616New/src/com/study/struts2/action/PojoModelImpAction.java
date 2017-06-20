package com.study.struts2.action;

import com.opensymphony.xwork2.ModelDriven;
import com.study.struts2.pojo.UserInfo;

public class PojoModelImpAction implements ModelDriven<UserInfo> {

	

	private UserInfo u = new UserInfo(); // 必须给值才能赋值
	
	public UserInfo getU() {
		return u;
	}
	public void setU(UserInfo u) {
		this.u = u;
	}
	
	@Override
	public UserInfo getModel() {
		System.out.println("ModelDirven 方式赋值的UserInfo值:" + u);
		return u;
	}
	

	public String test(){
		System.out.println("给action中对象通过ModelDirven赋值 ");
		return "success";
	}
	

}
