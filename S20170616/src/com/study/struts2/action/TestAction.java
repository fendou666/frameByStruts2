package com.study.struts2.action;

public class TestAction {
	public String execute(){
		System.out.println("执行了没有继承 ActionSupport的execute方法");
		return "sue";
	}
	/*public String executeaa(){
		System.out.println("执行了没有继承 ActionSupport的execute方法");
		return "sue";
	}*/
}
