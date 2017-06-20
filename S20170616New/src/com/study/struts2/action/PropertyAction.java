package com.study.struts2.action;

public class PropertyAction {
	

	private String name;
	private String sex;
	private String age;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String test(){
		System.out.println("直接给action 基本类型属性赋值");
		System.out.println("赋值后的Action对象toString值:" + this.toString());
		return "success";
	}
	@Override
	public String toString() {
		return "PropertyAction [name=" + name + ", sex=" + sex + ", age=" + age
				+ "]";
	}
}
