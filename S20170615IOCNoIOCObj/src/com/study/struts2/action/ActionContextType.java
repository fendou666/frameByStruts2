package com.study.struts2.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext; 

import com.opensymphony.xwork2.ActionContext; 
import com.opensymphony.xwork2.ActionSupport;

// �����ķ�ʽ

public class ActionContextType extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// ==============��ȡActionContext========
		ActionContext cxt = ActionContext.getContext();  
		
		// ==================ͨ��ActionContext �����Ļ�ȡ��Ҫ�Ķ���
		HttpServletRequest request = (HttpServletRequest)cxt.get(ServletActionContext.HTTP_REQUEST);  
		HttpServletResponse response = (HttpServletResponse)cxt.get(ServletActionContext.HTTP_RESPONSE);  
		// ��ȡsession
		HttpSession session = (HttpSession)cxt.get(ServletActionContext.SESSION); //(HttpSession)cxt.getSession();  
		Map<String, Object> session2 = cxt.getSession();
		
		ServletContext application = (ServletContext)cxt.get(ServletActionContext.APPLICATION); //(Application)cxt.getApplication();  
//		// ==================��ȡreq���� 
//		// ��ȡreq��4�з�ʽ(http://blog.csdn.net/agogwalker/article/details/52343871)
//		//��һ�ֻ�ȡrequet���󷽷�---HttpServletRequest�����attributes(����)
//		Map<String, Object> request1 = (Map)cxt.get("request");
//		//�ڶ��ֻ�ȡrequest���󷽷�(����)
//		HttpServletRequest request2 = (HttpServletRequest)cxt.get(StrutsStatics.HTTP_REQUEST);
//		// ���ַ�ʽ��ServletRequestAware���� ���
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		// ==================��ȡresp
//		HttpServletRequest response1 = (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		
//		// ==================��ȡsession1
//		HttpSession session = request.getSession();
//		// ==================��ȡsession2
//		Map<String, Object> session2 = ActionContext.getContext().getSession();
//		
		return super.execute();
	}
	
}
