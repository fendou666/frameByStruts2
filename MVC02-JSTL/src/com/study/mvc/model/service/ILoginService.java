package com.study.mvc.model.service;

import com.study.mvc.model.bean.UserInfo;

public interface ILoginService {
	//��۵Ĺ�������
	public UserInfo checkUserInfo(int id,String pwd);
}
