package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.dao.ITheTheacherDAO;
import com.chinasofti.eecuser.model.dao.TheTHeacherDAOImp;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class TheTeacherServiceImp implements ITheTeacherService {
	private ITheTheacherDAO  theTheacherDAO;
	public TheTeacherServiceImp(){
		theTheacherDAO = new TheTHeacherDAOImp();
	}
	
	@Override
	public UserInfo showPersonalInfo(int id) {
		return theTheacherDAO.showPersonalInfo(id);
	}

	@Override
	public List<UserInfo> getClassUserInfo(int classId, SqlDataPage theTheacherPage) {
		return theTheacherDAO.getClassUserInfo(classId, theTheacherPage);
	}

}
