package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface ITheTeacherService {
	// ===========������Ϣ��ʾ=========
	//	����Ф���ȷ���
	public UserInfo showPersonalInfo(int id);
	
	// ===========�༶��Ϣ��ѯ===============
	// ����Ф���Ȳ�ѯ
	public List<UserInfo> getClassUserInfo(int classId, SqlDataPage theTheacherPage);
}
