package com.study.mvc.model.dao;

import java.util.Map;

import com.study.mvc.model.bean.UserInfo;
import com.study.mvc.tools.DButil;

public class LoginDAOImp implements ILoginDAO {
	
	@Override
	public UserInfo checkLogin(int id, String pwd) {
		DButil dbutil=new DButil();
		UserInfo user=null;
		
		Map<String, String> hashMap = dbutil.checkUserInfo(id, pwd);
		if(hashMap!=null){
			user=new UserInfo();
			user.setId(id);
			user.setName(hashMap.get("username"));
		}
		
		return user;
	}

}
