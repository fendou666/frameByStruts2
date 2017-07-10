package com.chinasofti.eecuser.model.service;

import java.util.List;

import com.chinasofti.eecuser.model.dao.AdminTheacherDAOImp;
import com.chinasofti.eecuser.model.dao.IAdminTheacherDAO;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class AdminTheacherServiceImp implements IAdminTheacherService {
	private IAdminTheacherDAO adminTheacherDAO;
	
	public AdminTheacherServiceImp(){
		adminTheacherDAO = new AdminTheacherDAOImp();
	}
	
	@Override
	public List<UserInfo> queryEecUserOutClass(int id, String name,
			SqlDataPage teacherPage) {
		return adminTheacherDAO.queryEecUserOutClass(id, name, teacherPage);
	}

	@Override
	public boolean updEecUserOutClass(int id, int classId, int theacherType) {
		// TODO Auto-generated method stub
		return adminTheacherDAO.updEecUserOutClass(id, classId, theacherType);
	}

	@Override
	public List<UserInfo> queryDataByCondition(int roleId, int classId, int id,
			String name, SqlDataPage teacherPage) {
		return adminTheacherDAO.queryDataByCondition(roleId, classId, id, name, teacherPage);
	}

	@Override
	public boolean deleteTheacher(int id) {
		return adminTheacherDAO.deleteTheacher(id);
	}

	@Override
	public boolean updateTheacher(UserInfo u) {
		return adminTheacherDAO.updateTheacher(u);
	}

	

}
