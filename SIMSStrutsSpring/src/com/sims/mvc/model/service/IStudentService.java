/**
 * 功能：所有与学生有关接口
 * 制作人：李泽明
 */
package com.sims.mvc.model.service;

import java.util.List;

import com.sims.mvc.model.bean.Student;

public interface IStudentService {
	//查询所有
	List<Student> findStudentAll();
	
	//添加学生
	int addStudent(Student student, String pwd);
	
	//删除学生
	int deleteStuent(Student student);
	
	//修改学生
	int modifyStudent(Student student);
	
	//修改学生密码
	int modifyStudentPWD(String id, String pwd);
	
	//查询学生
	//根据id查询一个学生
	Student findStudentById(String id);
	
	//根据姓名查询多个学生
	List<Student> findStudentByName(String name);
	
	//根据性别查询多个学生
	List<Student> findStudentBySex(String sex);
	
	//根据年龄段查询多个学生
	List<Student> findStudentByAgeRange(int startAge, int endAge);
	
	//根据毕业院校查询多个学生
	List<Student> findStudentByGradFrom(String gradFrom);
	
	//根据电话查询多个学生
	List<Student> findStudentByTelphone(int telephone);
	
	//根据地址查询多个学生
	List<Student> findStudentByAddress(String address);

	//根据身份证号查询多个学生
	List<Student> findStudentByIDCard(String idCard);
	
	//根据邮箱查询多个学生
	List<Student> findStudentByEmail(String email);
	
	//根据组id查询多个学生
	List<Student> findStudentByTeamID(String teamId,String classid);

	//根据班级id查询多个学生
	List<Student> findStudentByClassID(String classId);
	
	//根据多个条件查询多个学生
	List<Student> findStudentByCondition(String id, String name, String sex, int startAge, int endAge, String gradFrom,
			int telephone, String address, String idCard, String email, String teamId, String classId);
	
	List<Student> findStudentByIsAdmin();

}
