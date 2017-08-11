/**
 * ���ܣ�������ѧ���йؽӿ�
 * �����ˣ�������
 */
package com.sims.mvc.model.service;

import java.util.List;

import com.sims.mvc.model.bean.Student;

public interface IStudentService {
	//��ѯ����
	List<Student> findStudentAll();
	
	//���ѧ��
	int addStudent(Student student, String pwd);
	
	//ɾ��ѧ��
	int deleteStuent(Student student);
	
	//�޸�ѧ��
	int modifyStudent(Student student);
	
	//�޸�ѧ������
	int modifyStudentPWD(String id, String pwd);
	
	//��ѯѧ��
	//����id��ѯһ��ѧ��
	Student findStudentById(String id);
	
	//����������ѯ���ѧ��
	List<Student> findStudentByName(String name);
	
	//�����Ա��ѯ���ѧ��
	List<Student> findStudentBySex(String sex);
	
	//��������β�ѯ���ѧ��
	List<Student> findStudentByAgeRange(int startAge, int endAge);
	
	//���ݱ�ҵԺУ��ѯ���ѧ��
	List<Student> findStudentByGradFrom(String gradFrom);
	
	//���ݵ绰��ѯ���ѧ��
	List<Student> findStudentByTelphone(int telephone);
	
	//���ݵ�ַ��ѯ���ѧ��
	List<Student> findStudentByAddress(String address);

	//�������֤�Ų�ѯ���ѧ��
	List<Student> findStudentByIDCard(String idCard);
	
	//���������ѯ���ѧ��
	List<Student> findStudentByEmail(String email);
	
	//������id��ѯ���ѧ��
	List<Student> findStudentByTeamID(String teamId,String classid);

	//���ݰ༶id��ѯ���ѧ��
	List<Student> findStudentByClassID(String classId);
	
	//���ݶ��������ѯ���ѧ��
	List<Student> findStudentByCondition(String id, String name, String sex, int startAge, int endAge, String gradFrom,
			int telephone, String address, String idCard, String email, String teamId, String classId);
	
	List<Student> findStudentByIsAdmin();

}
