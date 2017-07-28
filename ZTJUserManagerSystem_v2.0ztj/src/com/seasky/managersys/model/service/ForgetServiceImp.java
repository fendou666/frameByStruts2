package com.seasky.managersys.model.service;

import com.seasky.managersys.model.dao.ForgetDaoImp;

public class ForgetServiceImp implements IForgetService {
	ForgetDaoImp loginDao=null; 
	public ForgetServiceImp() {
		loginDao=new ForgetDaoImp();
	}
	@Override
	public int checkForget(String name, int userId, String pwd, String phone) {
		return loginDao.checkForget(name, userId, pwd, phone);
	}

	
}