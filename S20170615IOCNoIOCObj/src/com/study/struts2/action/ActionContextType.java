package com.study.struts2.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext; 

import com.opensymphony.xwork2.ActionContext; 
import com.opensymphony.xwork2.ActionSupport;

// 上下文方式

public class ActionContextType extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// ==============获取ActionContext========
		ActionContext cxt = ActionContext.getContext();  
		
		// ==================通过ActionContext 上下文获取需要的对象
		HttpServletRequest request = (HttpServletRequest)cxt.get(ServletActionContext.HTTP_REQUEST);  
		HttpServletResponse response = (HttpServletResponse)cxt.get(ServletActionContext.HTTP_RESPONSE);  
		// 获取session
		HttpSession session = (HttpSession)cxt.get(ServletActionContext.SESSION); //(HttpSession)cxt.getSession();  
		Map<String, Object> session2 = cxt.getSession();
		
		ServletContext application = (ServletContext)cxt.get(ServletActionContext.APPLICATION); //(Application)cxt.getApplication();  
//		// ==================获取req对象 
//		// 获取req的4中方式(http://blog.csdn.net/agogwalker/article/details/52343871)
//		//第一种获取requet对象方法---HttpServletRequest对象的attributes(解耦)
//		Map<String, Object> request1 = (Map)cxt.get("request");
//		//第二种获取request对象方法(解耦)
//		HttpServletRequest request2 = (HttpServletRequest)cxt.get(StrutsStatics.HTTP_REQUEST);
//		// 这种方式和ServletRequestAware都是 耦合
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		// ==================获取resp
//		HttpServletRequest response1 = (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		
//		// ==================获取session1
//		HttpSession session = request.getSession();
//		// ==================获取session2
//		Map<String, Object> session2 = ActionContext.getContext().getSession();
//		
		return super.execute();
	}
	
}
