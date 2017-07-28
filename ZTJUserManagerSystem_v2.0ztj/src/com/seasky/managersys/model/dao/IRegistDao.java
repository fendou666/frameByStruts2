package com.seasky.managersys.model.dao;

public interface IRegistDao {
	
	public int checkRegist(String userName,String sex,int age,String pwd,String phone,String mail);
}
