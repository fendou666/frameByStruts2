package com.study.struts2.i18n;

import com.opensymphony.xwork2.ActionSupport;

public class I18nAction extends ActionSupport {
	
	public static String testEval(){
		return "����Action�ж���ľ�̬������ ���� %�Ƿ����ֱ�Ӱ�����������ó���";
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("���� ���¶�ȡ��Դ�ļ�" + getText("login"));
		System.out.println("���� ȫ�ֶ�ȡ��Դ�ļ�" + getText("succPage"));
		return SUCCESS;
	}
	
}