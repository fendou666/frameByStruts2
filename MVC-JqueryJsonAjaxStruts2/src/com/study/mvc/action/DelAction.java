package com.study.mvc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;

public class DelAction extends ActionSupport {

	private static final long serialVersionUID = 424165627138343950L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		IStudentService stuService = new StudentServiceImp();
		int count = stuService.delete(id);
		if(count>0){
			addActionMessage("É¾³ý³É¹¦£¡£¡");
		}
		return SUCCESS;
	}
	
	
}
