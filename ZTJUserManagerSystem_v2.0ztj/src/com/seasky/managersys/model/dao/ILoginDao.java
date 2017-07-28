package com.seasky.managersys.model.dao;

import com.seasky.managersys.model.bean.UserInfo;


public interface ILoginDao {
	public UserInfo checkLogin(int userId,String pwd);
	public UserInfo checkLogin(long phone,String pwd);
}
