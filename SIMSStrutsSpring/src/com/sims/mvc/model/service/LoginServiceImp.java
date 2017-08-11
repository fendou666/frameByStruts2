package com.sims.mvc.model.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.dao.ILoginDAO;
import com.sims.mvc.model.dao.LoginDAOImp;

public class LoginServiceImp implements ILoginService {
    private ILoginDAO loginDao;
    
    public LoginServiceImp(){
    	//资源准备
    }

	public ILoginDAO getLoginDao() {
		return loginDao;
	}


	public void setLoginDao(ILoginDAO loginDao) {
		this.loginDao = loginDao;
	}


	@Override
	public Student checkStuInfo(String id, String pwd) {
		return loginDao.checkLogin(id, pwd);
	}

	@Override
	public int checkID(String id) {
		return loginDao.checkID(id);
	}

	@Override
	public String checkPermi(String permissions, String userId) {
		return loginDao.checkPermi(permissions, userId);
	}
	
	
	@Test
	public void loginServiceTest(){
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		LoginServiceImp bean = ac.getBean("loginService", LoginServiceImp.class);
		System.out.println(bean.checkStuInfo("413", "123"));
	}
}
