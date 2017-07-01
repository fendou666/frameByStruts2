package com.study.mvc.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;

public class QueryAction extends ActionSupport {

	private static final long serialVersionUID = 8994009187505139726L;
	private int id;
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		IStudentService stuService = new StudentServiceImp();
		StudentInfo stu = stuService.getStudentById(id);
		ActionContext ac = ActionContext.getContext();
		if(stu==null){
			ac.put("msg", "不存在给id的学员");
			return LOGIN;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("stu", stu);
		return SUCCESS;
	}

}
