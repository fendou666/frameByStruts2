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
		this.message = "����HelloAction�����access��Ա���� message ��\n"
				+ "���� �̳���ActionSupport���� ��дexecute() �����У�\n"
				+ "����ֵΪActionSupport�е�SUCEESS";
		System.out.println("ִ����HelloAction.java�� ��execute()����");
		return SUCCESS;
	}

}
