package com.sims.mvc.model.dao;

import com.sims.mvc.model.bean.Student;

public interface IStudentUpdateDAO {
	
	//���ѧ��
	int addStudent(Student student, String pwd);
	
	//ɾ��ѧ��
	int deleteStuent(Student student);
	
	//�޸�ѧ��
	int modifyStudent(Student student);
	
	//�޸�ѧ������
	int modifyStudentPWD(String id, String pwd);
}
