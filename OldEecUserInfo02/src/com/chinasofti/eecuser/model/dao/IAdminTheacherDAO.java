package com.chinasofti.eecuser.model.dao;

import java.util.List;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface IAdminTheacherDAO {
	// ==============��ʦ���===================
	//  	��ѯû�з���༶��ѧԱ
	public List<UserInfo> queryEecUserOutClass(int id, String name, SqlDataPage teacherPage);
	//  	��ͨѧԱ����Ϊ��ʦ
	public boolean updEecUserOutClass(int id, int classId, int theacherType);
	
	
	//================��ʦ����==================
	// 		����Ȩ���ǳƵı���ʦ��  
	public List<UserInfo> queryDataByCondition(int roleId, int classId,
			int id, String name, SqlDataPage teacherPage);
	
	//================��ʦɾ��==================
	// 		��Ҫ��ѯ��ɾ�� ��������Ľ�ʦ����
	// 		ɾ����ʦͨ��IDɾ��
	public boolean deleteTheacher(int id);
	
	//================��ʦ��Ϣ����==================
	// 		��Ҫ��ѯ����� ��������Ľ�ʦ����
	public boolean updateTheacher(UserInfo u);	
}
