package com.sims.mvc.model.service;

import com.sims.mvc.model.bean.Student;

public interface ILoginService {
	//��۵Ĺ�������
	public Student checkStuInfo(String id,String pwd);
	//���ID�Ƿ����
	public int checkID(String id);
	
	public String checkPermi(String permissions, String userId);
}
