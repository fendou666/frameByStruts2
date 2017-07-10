package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface ITheTeacherService {
	// ===========个人信息显示=========
	//	调用肖梦娜方法
	public UserInfo showPersonalInfo(int id);
	
	// ===========班级信息查询===============
	// 调用肖梦娜查询
	public List<UserInfo> getClassUserInfo(int classId, SqlDataPage theTheacherPage);
}
