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
 * ֤��ֻҪ���Ͳ�ֵͬ�ǲ���ͬ�ģ� Map�����ǶԵײ�ķ�װ
 * 
 * 
 * @author Administrator
 *
 */

public class AllObj extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ApplicationAware{
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;     //HttpServletRequest�ͱ���request����  
	private HttpServletResponse response;   //HttpServletResponse�ͱ���response����    
	private Map session;            //HttpSession�ͱ���session����  
	private Map application;        //Application�ͱ���application����  
	
	@Override
	public String execute() throws Exception {
		// actionContext
		// ==============��ȡActionContext========
		ActionContext cxt = ActionContext.getContext();  
		ActionContext cxtS = ServletActionContext.getContext();
		if(cxt == cxtS){
			System.out.println("actionContext  �� ServletActionContext һ��");
		}else{
			System.out.println("actionContext  �� ServletActionContext ����ͬ");
		}
		
		
		// req
		HttpServletRequest req = (HttpServletRequest)cxt.get(ServletActionContext.HTTP_REQUEST);  
		HttpServletRequest reqS = ServletActionContext.getRequest();
		if(req == reqS && req == request){
			System.out.println("ServletRequestAware �� ActionContext ��  ServletActionContextһ��");
		}else{
			System.out.println("ServletRequestAware �� ActionContext ��  ServletActionContext ����ͬ");
		}
		
		// resp
		HttpServletResponse resp = (HttpServletResponse)cxt.get(ServletActionContext.HTTP_RESPONSE);  
		HttpServletResponse respS = ServletActionContext.getResponse();
		
		if(resp == respS && resp == response){
			System.out.println("ServletResponseAware �� ActionContext ��  ServletActionContextһ��");
		}else{
			System.out.println("ServletResponseAware �� ActionContext ��  ServletActionContext ����ͬ");
		}
		
		// session
		Map<String, Object> ses1 = (Map<String, Object>)cxt.get(ServletActionContext.SESSION); //(HttpSession)cxt.getSession();  
		Map<String, Object> ses2 = cxt.getSession();
		Map<String, Object> sessS1 = ServletActionContext.getContext().getSession();
		HttpSession sessR1 = request.getSession();
		if(ses1 == ses2 &&  ses1 == session){
			System.out.println("SessionAware �� ActionContext ��  ServletActionContextһ��");
		}else{
			System.out.println("SessionAware �� ActionContext ��  ServletActionContext ����ͬ");
		}
		
//		Map<String, Object> sessR2 = (Map<String, Object>)sessR1; // ����ֱ��ת������
//		if(session == sessR2){
//			System.out.println("ת�������ͬ");
//		}
		
		
		// app
		Map<String, Object> app = (Map<String, Object>)cxt.get(ServletActionContext.APPLICATION); //(Application)cxt.getApplication();
		ServletContext appS = ServletActionContext.getServletContext();
		ServletContext appR = request.getServletContext();
		if(app == application){
			System.out.println("ApplicationAware �� ActionContext ��  ServletActionContextһ��");
		}else{
			System.out.println("ApplicationAware �� ActionContext ��  ServletActionContext ����ͬ");
		}
		if(appS == appR){
			System.out.println("ServletActionContent �� request �����ȡ��һ��");
		}
		
//		Map<String, Object> appS2 = (Map<String, Object>)appS; // ת��ֱ�ӱ���
//		if(appS2 == application){
//			System.out.println(" ����ת��һֱ��Ϳ���");
//		}
		
		
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