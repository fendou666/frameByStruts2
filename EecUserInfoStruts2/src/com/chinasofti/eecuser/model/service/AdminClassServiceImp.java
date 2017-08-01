package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.dao.AdminClassDAOImp;
import com.chinasofti.eecuser.model.dao.IAdminClassDAO;
import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;

public class AdminClassServiceImp implements IAdminClassService {
	private IAdminClassDAO adminClassDAO;
	
	public AdminClassServiceImp(){
		adminClassDAO = new AdminClassDAOImp();
	}
	
	@Override
	public List<ClassInfo> queryClassInfoByCondition(int class_id, SqlDataPage classPage) {
		
		return adminClassDAO.queryClassInfoByCondition(class_id, classPage);
	}

	@Override
	public boolean createClass(ClassInfo newClass) {
		return adminClassDAO.createClass(newClass);
	}

	@Override
	public boolean deleteClass(int classId) {
		return adminClassDAO.deleteClass(classId);
	}

}
