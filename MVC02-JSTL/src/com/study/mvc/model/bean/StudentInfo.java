package com.study.mvc.model.bean;

public class StudentInfo {
	private int id;
	private String name;
	private String sex;
	private int age;
	private String gradeFrom;
	public StudentInfo(){
		
	}
	
	public StudentInfo(int id, String name, String sex, int age,
			String gradeFrom) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.gradeFrom = gradeFrom;
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
	public String getGradeFrom() {
		return gradeFrom;
	}
	public void setGradeFrom(String gradeFrom) {
		this.gradeFrom = gradeFrom;
	}
	
}
