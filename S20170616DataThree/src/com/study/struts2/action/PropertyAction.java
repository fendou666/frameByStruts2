package com.study.struts2.action;

public class PropertyAction {
	

	private String name;
	private String sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String test(){
		System.out.println("ֱ�Ӹ�action �����������Ը�ֵ");
		System.out.println("��ֵ���Action����toStringֵ:" + this.toString());
		return "success";
	}
	@Override
	public String toString() {
		return "PropertyAction [name=" + name + ", sex=" + sex + ", age=" + age
				+ "]";
	}
}