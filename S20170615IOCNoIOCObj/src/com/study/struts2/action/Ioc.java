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
	
	private HttpServletRequest request;     //HttpServletRequest型变量request声明  
	private HttpServletResponse response;   //HttpServletResponse型变量response声明    
	private Map session;            //HttpSession型变量session声明  
	private Map application;        //Application型变量application声明  
	
	@Override
	public String execute() throws Exception {
		// 这里直接使用
		request.getParameter("user");
		request.getServletContext();
		return super.execute();
	}
	@Override
	public void setServletRequest(HttpServletRequest request){ //实现接口中的方法  
	    this.request = request;  
	}  
	
	@Override  
	public void setServletResponse(HttpServletResponse response){ //实现接口中的方法  
	    this.response = response;  
	}  
	
	@Override
	public void setSession(Map<String, Object> session){ //实现接口中的方法   
	    this.session = session;  
	}  
	
	@Override
	public void setApplication(Map<String, Object> application){ //实现接口中的方法  
	    this.application = application;  
	}  
}
