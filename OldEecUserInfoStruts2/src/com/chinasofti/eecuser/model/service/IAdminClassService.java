package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;

public interface IAdminClassService {
	
	// ===============�༶��ѯ============
	// 		�༶��ѯ����
	public List<ClassInfo> queryClassInfoByCondition(int class_id, SqlDataPage classPage);
	
	// ===============�༶��������ӣ�============
	// 		ֱ��һ��ҳ�洴��
	public boolean createClass(ClassInfo newClass);
	
	// ===============�༶ɾ��============
	// 		��ͨ������Ĳ����ҵ����а༶
	// 		�༶ɾ��
	public boolean deleteClass(int classId);
	
	
}
