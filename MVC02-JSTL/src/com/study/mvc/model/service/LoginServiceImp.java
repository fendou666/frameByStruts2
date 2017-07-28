package com.study.mvc.model.service;

import com.study.mvc.model.bean.UserInfo;
import com.study.mvc.model.dao.ILoginDAO;
import com.study.mvc.model.dao.LoginDAOImp;

public class LoginServiceImp implements ILoginService {
    private ILoginDAO loginDao;
    
    public LoginServiceImp(){
    	//资源准备
    	loginDao=new LoginDAOImp();
    }
    
	@Override
	public UserInfo checkUserInfo(int id, String pwd) {
		return loginDao.checkLogin(id, pwd);
	}
}
