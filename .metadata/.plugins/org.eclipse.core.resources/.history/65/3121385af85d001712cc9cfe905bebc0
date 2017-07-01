package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;

public interface IAdminClassService {
	
	// ===============班级查询============
	// 		班级查询所有
	public List<ClassInfo> queryClassInfoByCondition(int class_id, SqlDataPage classPage);
	
	// ===============班级创建（添加）============
	// 		直接一个页面创建
	public boolean createClass(ClassInfo newClass);
	
	// ===============班级删除============
	// 		先通过上面的查找找到所有班级
	// 		班级删除
	public boolean deleteClass(int classId);
	
	
}
