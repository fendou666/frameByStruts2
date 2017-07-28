package com.seasky.managersys.model.dao;

public interface IForgetDao {
	
	public int checkForget(String name,int userId,String pwd,String phone);
}
