package com.chinasofti.eecuser.model.dao;

import java.util.ArrayList;

import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface IStudentDAO {
	public UserInfo queryUserById(int eecId);
	public ArrayList<UserInfo> queryUserByConditions(int eecId, int classId,String eecName,
			String roleName);
	public ArrayList<UserInfo> queryByClassId(int classId);
	public int updateUserById(long telephone,String email,int eecId);
}
