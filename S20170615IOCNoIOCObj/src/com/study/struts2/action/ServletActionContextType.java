package com.study.struts2.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ServletActionContextType extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// ��ȡsession
		HttpSession session = request.getSession();
		Map<String, Object> session2 = ServletActionContext.getContext().getSession();
		
		// ��ȡ�����ʶ���
		ActionContext context = ServletActionContext.getContext();
		Map<String, Object> application = ServletActionContext.getContext().getApplication();
		// ��������֤������������ͬ��
		ServletContext application1 = ServletActionContext.getServletContext();
		ServletContext servletContext = request.getServletContext();
		if(application1 == servletContext){
			System.out.println("������ͬ");
		}
		return super.execute();
	}
	
	
}