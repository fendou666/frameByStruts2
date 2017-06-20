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
	
	public String execute2() {
		this.message = "我是HelloAction里面的access成员属性 message ，\n"
				+ "我在 继承了ActionSupport类中 重写execute()2 方法中，\n"
				+ "返回值为ActionSupport中的SUCEESS";
		System.out.println("执行了HelloAction.java中 的execute2()方法");
		return SUCCESS;
	}
	
	public String add() {
		System.out.println("测试以下 这是Hello的 add 方法");
		return SUCCESS;
	}

}
