package com.study.struts2.i18n;

import com.opensymphony.xwork2.ActionSupport;

public class I18nAction extends ActionSupport {
	
	public static String testEval(){
		return "我是Action中定义的静态方法， 测试 %是否可以直接把这个方法调用出来";
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("测试 包下读取资源文件" + getText("login"));
		System.out.println("测试 全局读取资源文件" + getText("succPage"));
		return SUCCESS;
	}
	
}
