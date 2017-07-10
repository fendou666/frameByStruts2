package com.chinasofti.eecuser.contrlller.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CharsetInterceptNOIOC implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map request = (Map)ActionContext.getContext().get("request");
		HttpServletRequest req = (HttpServletRequest)request;
		req.setCharacterEncoding("UTF-8");
		System.out.println("执行CharsetInterceptNOIOC invoke 前");
		String ret = arg0.invoke();
		System.out.println("执行CharsetInterceptNOIOC invoke 后");
		return ret;
	}

}
