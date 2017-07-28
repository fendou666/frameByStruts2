package com.seasky.managersys.model.service;

import com.seasky.managersys.model.dao.RegistDaoImp;;;

public class RegistServiceImp implements IRegistService {
	RegistDaoImp loginDao=null; 
	public RegistServiceImp() {
		loginDao=new RegistDaoImp();
	}

	@Override
	public int checkRegist(String userName,String sex,int age,String pwd,String phone,String mail) {
		return loginDao.checkRegist(userName,sex,age,pwd,phone,mail);
	}
}