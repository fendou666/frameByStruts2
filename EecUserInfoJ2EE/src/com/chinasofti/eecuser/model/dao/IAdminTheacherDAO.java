package com.chinasofti.eecuser.model.dao;

import java.util.List;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface IAdminTheacherDAO {
	// ==============教师添加===================
	//  	查询没有分配班级的学员
	public List<UserInfo> queryEecUserOutClass(int id, String name, SqlDataPage teacherPage);
	//  	普通学员更新为教师
	public boolean updEecUserOutClass(int id, int classId, int theacherType);
	
	
	//================教师查找==================
	// 		关联权限昵称的表（教师）  
	public List<UserInfo> queryDataByCondition(int roleId, int classId,
			int id, String name, SqlDataPage teacherPage);
	
	//================教师删除==================
	// 		先要查询后删除 调用上面的教师查找
	// 		删除教师通过ID删除
	public boolean deleteTheacher(int id);
	
	//================教师信息更新==================
	// 		先要查询后更新 调用上面的教师查找
	public boolean updateTheacher(UserInfo u);	
}
