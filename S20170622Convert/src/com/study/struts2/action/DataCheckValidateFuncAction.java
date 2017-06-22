package com.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import com.study.struts2.javabean.Telephone;

public class DataCheckValidateFuncAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String userName;
	private int age;
	private String[] likes;
	private Telephone tel;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		//System.out.println("设置的姓名是:" + userName);
		this.userName = userName;
	}

	public String[] getLikes() {
		return likes;
	}

	public void setLikes(String[] likes) {
		this.likes = likes;
	}
	// get/set 需要吗？
	public Telephone getTel() {
		return tel;
	}

	public void setTel(Telephone tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void validateFunc(){
		if(age<0 || age>60){
			addActionError("validateFunc中 年龄只能在0到60之间");
		}
	}
	public String func() throws Exception{
		return SUCCESS;
	}
	
	/*@Override
	public void validate() {
		if(age<0 || age>60){
			addActionError("validate中 年龄只能在0到60之间");
		}
	}
	
	@Override
	public String execute() throws Exception {
		if(age<0 || age>60){
			addActionError("execute 中 年龄只能在0到60之间");
			return INPUT;
		}
		return SUCCESS;
	}*/
	
}
