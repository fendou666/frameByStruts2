package com.study.struts2.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Ioc extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ApplicationAware {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;     //HttpServletRequest�ͱ���request����  
	private HttpServletResponse response;   //HttpServletResponse�ͱ���response����    
	private Map session;            //HttpSession�ͱ���session����  
	private Map application;        //Application�ͱ���application����  
	
	@Override
	public String execute() throws Exception {
		// ����ֱ��ʹ��
		request.getParameter("user");
		request.getServletContext();
		return super.execute();
	}
	@Override
	public void setServletRequest(HttpServletRequest request){ //ʵ�ֽӿ��еķ���  
	    this.request = request;  
	}  
	
	@Override  
	public void setServletResponse(HttpServletResponse response){ //ʵ�ֽӿ��еķ���  
	    this.response = response;  
	}  
	
	@Override
	public void setSession(Map<String, Object> session){ //ʵ�ֽӿ��еķ���   
	    this.session = session;  
	}  
	
	@Override
	public void setApplication(Map<String, Object> application){ //ʵ�ֽӿ��еķ���  
	    this.application = application;  
	}  
}