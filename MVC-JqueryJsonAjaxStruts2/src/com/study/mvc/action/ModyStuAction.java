package com.study.mvc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;

public class ModyStuAction extends ActionSupport {

	private static final long serialVersionUID = -5303418352802545536L;

	private int id;
	private String name;
	private String sex;
	private int age;
	private String gradeFrom;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		IStudentService stuService = new StudentServiceImp();
		StudentInfo stu = new StudentInfo(id, name, sex, age, gradeFrom);
		int count = stuService.save(stu);
		if(count>0){
			addActionMessage("ÐÞ¸Ä³É¹¦£¡£¡");
		}
		return SUCCESS;
	}
	
}
