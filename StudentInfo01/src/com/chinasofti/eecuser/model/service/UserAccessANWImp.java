package com.chinasofti.eecuser.model.service;

import com.chinasofti.eecuser.model.dao.DAOUserAccessANWImp;
import com.chinasofti.eecuser.model.dao.IDAOUserAccessANW;
import com.chinasofti.eecuser.model.javabean.UserAceessANW;

public class UserAccessANWImp implements ISVCAccessSafeANS {
	private IDAOUserAccessANW userAccessDAO = null;
	
	public UserAccessANWImp(){
		userAccessDAO = new DAOUserAccessANWImp();
	}
	
	@Override
	public boolean accessAnswer(UserAceessANW UA, String answer3) {
		return userAccessDAO.accessAnswer(UA, answer3);
	}

}
