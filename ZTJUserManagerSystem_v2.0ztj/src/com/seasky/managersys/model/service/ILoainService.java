package com.seasky.managersys.model.service;

import com.seasky.managersys.model.bean.UserInfo;

public interface ILoainService {
	public UserInfo checkUserInfo(int id,String pwd);
	public UserInfo checkUserInfo(long phone,String pwd);
}
