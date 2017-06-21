package com.study.mvc.model.service;

import java.util.List;

import com.study.mvc.model.bean.StudentInfo;

//�淶�����ķ�Χ
//�淶������Ŀ��
public interface IStudentService {
	//��
	
	//������idȥ���Һ�����һ��ѧԱ
	public StudentInfo getStudentById(int id);
	//������nameȥ���Һ��������ѧԱ
	public List<StudentInfo> getStudentsByName(String name);
	//�������Ա�ȥ���Һ��������ѧԱ
	public List<StudentInfo> getStudentsBySex(String sex);
	//���������䷶Χȥ���Һ��������ѧԱ
	public List<StudentInfo> getStudentsByAgeRange(int startAge,int endAge);
	//�����ñ�ҵԺУȥ���Һ��������ѧԱ
	public List<StudentInfo> getStudentsByGradeFrom(String gradeFrom);
	//�����ö��������ѯ���ѧԱ
	public List<StudentInfo> getStudentsByCondition(
			int id,
			String name,
			String sex,
			int startAge,int endAge,
			String gradeFrom
			);
	//��ѯ����ѧԱ��Ϣ
	public List<StudentInfo> getStudentAll();
	//�� ɾ ��
	public int insert(StudentInfo stu);//��
	public int save(StudentInfo stu);  //��
	public int delete(int id);	       //ɾ
}
