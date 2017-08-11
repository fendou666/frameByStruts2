package com.sims.mvc.model.dao;

import java.util.List;

import com.sims.mvc.model.bean.CClass;

public interface IClassDAO {
	// 通过id查询班信息
	CClass findClassById(String id);
	
	// 通过班名查询班信息
	CClass findClassesByName(String name);
	
	// 通过班长id查询班信息
	CClass findClassesByMonitorId(String monitorId);
	
	// 通过班主任id查询班信息
	CClass findClassesByManTeacherId(String manTeacherId);
	
	// 通过任课老师id查询班信息
	CClass findClassesByTeacTeacherId(String teacTeacherId);
	
	// 添加一个班
	int addClass(CClass cclass);
	
	// 修改班信息
	int modifyClass(CClass cclass);
	
	// 删除班信息
	int deleteClass(String cclassId);
	
	//查询所有班级
	List<CClass> getClassesAll();
}
