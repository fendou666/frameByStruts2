package com.study.mvc.model.dao;

import com.study.mvc.model.bean.UserInfo;

public interface ILoginDAO {
	//微观具体执行者的主管功能需求
	public UserInfo checkLogin(int id,String pwd);
}
