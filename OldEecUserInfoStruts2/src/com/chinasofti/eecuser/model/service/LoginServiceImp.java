package com.chinasofti.eecuser.model.service;

import com.chinasofti.eecuser.model.dao.ILoginDAO;
import com.chinasofti.eecuser.model.dao.LoginDAOImp;

public class LoginServiceImp implements ILoginService {
	private ILoginDAO loginDAO;
	public LoginServiceImp(){
		loginDAO = new LoginDAOImp();
	}
	@Override
	public boolean checkLoginInfo() {
		
		return loginDAO.checkLoginInfo();
	}

}
