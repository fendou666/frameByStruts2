package com.sims.mvc.model.service;

import com.sims.mvc.model.bean.Student;

public interface ILoginService {
	//宏观的功能需求
	public Student checkStuInfo(String id,String pwd);
	//检查ID是否存在
	public int checkID(String id);
	
	public String checkPermi(String permissions, String userId);
}
