package com.study.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor implements Interceptor {

	@Override
	public void destroy() {
		System.out.println("执行了 自定义拦截器的 destroy方法");
	}

	@Override
	public void init() {
		System.out.println("执行了 拦截器的init 方法");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("执行 invoke之前");
		System.out.println("执行之前获取的session信息" + arg0.getInvocationContext().getSession().get("userInfo"));
		String ret = arg0.invoke();
		System.out.println("执行 invoke之后");
		System.out.println("执行之后获取的session信息" + arg0.getInvocationContext().getSession().get("userInfo"));
		// 调用下一个struts2组件: 拦截器/action
		return ret;
	}

}
