package com.study.struts2.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 证明只要类型不同值是不相同的， Map类型是对底层的封装
 * 
 * 
 * @author Administrator
 *
 */

public class AllObj extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ApplicationAware{
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;     //HttpServletRequest型变量request声明  
	private HttpServletResponse response;   //HttpServletResponse型变量response声明    
	private Map session;            //HttpSession型变量session声明  
	private Map application;        //Application型变量application声明  
	
	@Override
	public String execute() throws Exception {
		// actionContext
		// ==============获取ActionContext========
		ActionContext cxt = ActionContext.getContext();  
		ActionContext cxtS = ServletActionContext.getContext();
		if(cxt == cxtS){
			System.out.println("actionContext  与 ServletActionContext 一样");
		}else{
			System.out.println("actionContext  与 ServletActionContext 不相同");
		}
		
		
		// req
		HttpServletRequest req = (HttpServletRequest)cxt.get(ServletActionContext.HTTP_REQUEST);  
		HttpServletRequest reqS = ServletActionContext.getRequest();
		if(req == reqS && req == request){
			System.out.println("ServletRequestAware 与 ActionContext 和  ServletActionContext一样");
		}else{
			System.out.println("ServletRequestAware 与 ActionContext 和  ServletActionContext 不相同");
		}
		
		// resp
		HttpServletResponse resp = (HttpServletResponse)cxt.get(ServletActionContext.HTTP_RESPONSE);  
		HttpServletResponse respS = ServletActionContext.getResponse();
		
		if(resp == respS && resp == response){
			System.out.println("ServletResponseAware 与 ActionContext 和  ServletActionContext一样");
		}else{
			System.out.println("ServletResponseAware 与 ActionContext 和  ServletActionContext 不相同");
		}
		
		// session
		Map<String, Object> ses1 = (Map<String, Object>)cxt.get(ServletActionContext.SESSION); //(HttpSession)cxt.getSession();  
		Map<String, Object> ses2 = cxt.getSession();
		Map<String, Object> sessS1 = ServletActionContext.getContext().getSession();
		HttpSession sessR1 = request.getSession();
		if(ses1 == ses2 &&  ses1 == session){
			System.out.println("SessionAware 与 ActionContext 和  ServletActionContext一样");
		}else{
			System.out.println("SessionAware 与 ActionContext 和  ServletActionContext 不相同");
		}
		
//		Map<String, Object> sessR2 = (Map<String, Object>)sessR1; // 这里直接转换出错
//		if(session == sessR2){
//			System.out.println("转换后就相同");
//		}
		
		
		// app
		Map<String, Object> app = (Map<String, Object>)cxt.get(ServletActionContext.APPLICATION); //(Application)cxt.getApplication();
		ServletContext appS = ServletActionContext.getServletContext();
		ServletContext appR = request.getServletContext();
		if(app == application){
			System.out.println("ApplicationAware 与 ActionContext 和  ServletActionContext一样");
		}else{
			System.out.println("ApplicationAware 与 ActionContext 和  ServletActionContext 不相同");
		}
		if(appS == appR){
			System.out.println("ServletActionContent 与 request 请求获取的一样");
		}
		
//		Map<String, Object> appS2 = (Map<String, Object>)appS; // 转换直接报错
//		if(appS2 == application){
//			System.out.println(" 类型转换一直后就可以");
//		}
		
		
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
