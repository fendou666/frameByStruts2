package com.study.mvc.model.bean;

public class UserInfo {
	private int id;
	private String name;
	
	public UserInfo() {
		
	}
	
	public UserInfo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	//private String pwd;//������������Ϣ ����ֱ�ӷŵ�������Ϊ��Ա��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
