package com.sims.mvc.model.dao;

import com.sims.mvc.model.bean.Student;

public interface ILoginDAO {
	//微观具体执行者的主管功能需求
	public Student checkLogin(String id,String pwd);
	
	public int checkID(String id);
	
	public String checkPermi(String permissions, String userId);
}
