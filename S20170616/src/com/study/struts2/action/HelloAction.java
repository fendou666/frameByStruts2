package com.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	@Override
	public String execute() throws Exception {
		this.message = "我是HelloAction里面的access成员属性 message ，\n"
				+ "我在 继承了ActionSupport类中 重写execute() 方法中，\n"
				+ "返回值为ActionSupport中的SUCEESS";
		System.out.println("执行了HelloAction.java中 的execute()方法");
		return SUCCESS;
	}

}
