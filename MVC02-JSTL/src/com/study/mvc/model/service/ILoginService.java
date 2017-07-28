package com.study.mvc.model.service;

import com.study.mvc.model.bean.UserInfo;

public interface ILoginService {
	//宏观的功能需求
	public UserInfo checkUserInfo(int id,String pwd);
}
