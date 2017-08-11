package com.sims.mvc.model.dao;

import java.util.List;

import com.sims.mvc.model.bean.CClass;

public interface IClassDAO {
	// ͨ��id��ѯ����Ϣ
	CClass findClassById(String id);
	
	// ͨ��������ѯ����Ϣ
	CClass findClassesByName(String name);
	
	// ͨ���೤id��ѯ����Ϣ
	CClass findClassesByMonitorId(String monitorId);
	
	// ͨ��������id��ѯ����Ϣ
	CClass findClassesByManTeacherId(String manTeacherId);
	
	// ͨ���ο���ʦid��ѯ����Ϣ
	CClass findClassesByTeacTeacherId(String teacTeacherId);
	
	// ���һ����
	int addClass(CClass cclass);
	
	// �޸İ���Ϣ
	int modifyClass(CClass cclass);
	
	// ɾ������Ϣ
	int deleteClass(String cclassId);
	
	//��ѯ���а༶
	List<CClass> getClassesAll();
}
