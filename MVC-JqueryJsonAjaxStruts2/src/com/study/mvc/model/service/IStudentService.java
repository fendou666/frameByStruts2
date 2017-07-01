package com.study.mvc.model.service;

import java.util.List;

import com.study.mvc.model.bean.StudentInfo;

//规范开发的范围
//规范开发的目标
public interface IStudentService {
	//查
	
	//可以用id去查找和锁定一个学员
	public StudentInfo getStudentById(int id);
	//可以用name去查找和锁定多个学员
	public List<StudentInfo> getStudentsByName(String name);
	//可以用性别去查找和锁定多个学员
	public List<StudentInfo> getStudentsBySex(String sex);
	//可以用年龄范围去查找和锁定多个学员
	public List<StudentInfo> getStudentsByAgeRange(int startAge,int endAge);
	//可以用毕业院校去查找和锁定多个学员
	public List<StudentInfo> getStudentsByGradeFrom(String gradeFrom);
	//可以用多个条件查询多个学员
	public List<StudentInfo> getStudentsByCondition(
			int id,
			String name,
			String sex,
			int startAge,int endAge,
			String gradeFrom
			);
	//查询所有学员信息
	public List<StudentInfo> getStudentAll();
	//增 删 改
	public int insert(StudentInfo stu);//增
	public int save(StudentInfo stu);  //改
	public int delete(int id);	       //删
}
