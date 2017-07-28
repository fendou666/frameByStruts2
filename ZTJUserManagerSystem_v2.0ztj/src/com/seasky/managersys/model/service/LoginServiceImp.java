package com.seasky.managersys.model.service;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.dao.LoginDaoImp;

public class LoginServiceImp implements ILoainService {
	LoginDaoImp loginDao=null; 
	public LoginServiceImp() {
		loginDao=new LoginDaoImp();
	}

	@Override
	public UserInfo checkUserInfo(int userId, String pwd) {
		UserInfo user=null;
		user=loginDao.checkLogin(userId, pwd);
		return user;
	}

	@Override
	public UserInfo checkUserInfo(long phone, String pwd) {
		UserInfo user=null;
		user=loginDao.checkLogin(phone, pwd);
		return user;
	}
}