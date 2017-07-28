package com.seasky.managersys.model.bean;

public class UserInfo {
	private int id;
	private String name;
	private String sex;
	private int age;
	private int headmasterId;
	private int classId;
	private int groupId;
	private int teacherId;
	private long phone;
	private String address;
	private String mail;
	private int power;
	private String delete;
	
	public UserInfo() { }
	
	public UserInfo(int id, String name, String sex, int age, int headmasterId, int teacherId,
			int classId, int groupId, long phone, String address, String mail,
			int power, String delete) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.headmasterId = headmasterId;
		this.teacherId = teacherId;
		this.classId = classId;
		this.groupId = groupId;
		this.phone = phone;
		this.address = address;
		this.mail = mail;
		this.power = power;
		this.delete = delete;
	}

	public UserInfo(int id, String name, String sex, int age, long phone,int classId,
			int groupId, String address, String mail, int power, String delete) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.classId = classId;
		this.groupId = groupId;
		this.address = address;
		this.mail = mail;
		this.power = power;
		this.delete = delete;
	}

	public UserInfo(int id, String name, String sex, int age, 
			long phone, String address, String mail, int power, String delete) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.mail = mail;
		this.power = power;
		this.delete = delete;
	}

	public UserInfo(int id, String name,int classId, int power, String delete) {
		this.id = id;
		this.name = name;
		this.classId = classId;
		this.power = power;
		this.delete = delete;
	}

	public UserInfo(String name) {
		this.name = name;
	}

	public UserInfo(int id, int power, String name, int groupId,
			String delete) {
		this.id = id;
		this.name = name;
		this.groupId = groupId;
		this.power = power;
		this.delete = delete;
	}

	public UserInfo(int id, int power, int classId, String name,
			int groupId, String delete) {
		this.id = id;
		this.power = power;
		this.classId = classId;
		this.name = name;
		this.groupId = groupId;
		this.delete = delete;
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

	public int getHeadmasterId() {
		return headmasterId;
	}

	public void setHeadmasterId(int headmasterId) {
		this.headmasterId = headmasterId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
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

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}
	
}
