package com.seasky.managersys.model.bean;

public class TeacherInfo {
	private int id;
	private String name;
	private String sex;
	private int age;
	private Long phone;
	private String address;
	private String mail;
	private int power;
	private String deleteStual;//É¾³ý×´Ì¬
	
	public TeacherInfo(int id, String name, String sex, int age, Long phone,
			String address, String mail, int power) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.mail = mail;
		this.power = power;
	}
	public TeacherInfo(int id, String name, int power, String deleteStual) {
		this.id = id;
		this.name = name;
		this.power = power;
		this.deleteStual = deleteStual;
	}
	public TeacherInfo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public TeacherInfo() {
	}
	
	
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
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getDeleteStual() {
		return deleteStual;
	}
	public void setDeleteStual(String deleteStual) {
		this.deleteStual = deleteStual;
	}
	
}
