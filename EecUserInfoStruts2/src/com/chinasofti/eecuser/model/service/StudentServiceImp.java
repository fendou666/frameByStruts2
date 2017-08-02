package com.chinasofti.eecuser.model.service;

import java.util.ArrayList;
import java.util.List;

import com.chinasofti.eecuser.model.dao.IStudentDAO;
import com.chinasofti.eecuser.model.dao.StudentDAOImp;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class StudentServiceImp implements IStudentService {
	private IStudentDAO userInfo=null;
	
	public StudentServiceImp(){
		userInfo=new StudentDAOImp();
	}
	
	@Override
	public UserInfo queryUserById(int eecId) {
		return userInfo.queryUserById(eecId);
	}
	
	@Override
	public List<UserInfo> queryByClassId(int classId){
		return userInfo.queryByClassId(classId);
	}

	@Override
	public int updateUserById(long telephone,String email,int eecId) {
		return userInfo.updateUserById(telephone,email,eecId);
	}

	@Override
	public ArrayList<UserInfo> queryUserByConditions(int eecId, int classId,String eecName,
			String roleName) {
		return userInfo.queryUserByConditions(eecId,classId,eecName,roleName);
	}
}
