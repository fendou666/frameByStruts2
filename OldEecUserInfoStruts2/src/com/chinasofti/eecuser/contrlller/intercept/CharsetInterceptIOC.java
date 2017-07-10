package com.chinasofti.eecuser.contrlller.intercept;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CharsetInterceptIOC implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// 关于这里如何实现
		String ret = arg0.invoke();
		return ret;
	}

}
