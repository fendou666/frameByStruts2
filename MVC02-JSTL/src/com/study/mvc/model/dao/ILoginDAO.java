package com.study.mvc.model.dao;

import com.study.mvc.model.bean.UserInfo;

public interface ILoginDAO {
	//΢�۾���ִ���ߵ����ܹ�������
	public UserInfo checkLogin(int id,String pwd);
}
