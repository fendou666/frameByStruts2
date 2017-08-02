package com.chinasofti.eecuser.model.service;

import java.util.ArrayList;
import java.util.List;

import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface IStudentService {
	/**
	 * ����eecId��ѯ�û�������Ϣ
	 * @param id
	 * @return ���ز�ѯ���Ķ����û���Ϣ
	 */
	public UserInfo queryUserById(int eecId);
	/**
	 * ����������������ѯ�û���Ϣ
	 * @param eecId
	 * @param eecName
	 * @param roleName
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	public ArrayList<UserInfo> queryUserByConditions(int eecId, int classId,String eecName,
			String roleName);
	/**
	 * ��ѯ�༶��Ϣ
	 * @return ���ذ༶��Ϣ�ļ���
	 */
	public List<UserInfo> queryByClassId(int classId);
	/**
	 * ����eecId�޸��û���Ϣ
	 * @param eecId
	 * @return �����޸ĺ�Ķ����û���Ϣ
	 */
	public int updateUserById(long telephone,String email,int eecId);
}
