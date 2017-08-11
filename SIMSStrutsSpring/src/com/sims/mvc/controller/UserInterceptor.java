package com.sims.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sims.mvc.model.bean.Student;

public class UserInterceptor implements Interceptor{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session = null;
	
	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		if(action instanceof LoginAction){
			System.out.println("exit check login, because this is login action.");
            return actionInvocation.invoke();
		}
		
		session = actionInvocation.getInvocationContext().getSession();
		
		Student stu = (Student)session.get("stuInfo");
		if(stu != null){
			System.out.println("already login!");
			String flag = checkSeesion();
			if(flag == null){
				return actionInvocation.invoke();
			}else{
				return flag;
			}
            
		}else{
			 System.out.println("no login, forward login page!");
	         return "error";
		}
		
	}
	
	public String checkSeesion(){
		String flag = null;
		HttpServletRequest req =  ServletActionContext.getRequest();
		String path = new String(req.getRequestURL());
//		System.out.println("path:---------" + path);
		
		
		String user_right = (String)session.get("user_right");
			
		if( (path.contains("/teamLeader/") && !user_right.equals("isleader"))
			|| (path.contains("/classMonit/") && !user_right.equals("ismonitor"))
			|| (path.contains("/classManger/") && !user_right.equals("ismanteach"))
			|| (path.contains("/teacher/") && !user_right.equals("isteacteach"))
			|| (path.contains("/admin/") && !user_right.equals("isnormadmin"))
			|| (path.contains("/superAdmin/") && !user_right.equals("admin"))){
			
			flag = "noRight";
		}
		return flag;
	}


}
