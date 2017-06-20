package com.chinasofti.common;

import com.chinasofti.actions.LoginAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class CheckloginInterceptor implements Interceptor {

    @Override
    public void destroy() {
        System.out.println("---------------destroy()-------------------");
    }

    @Override
    public void init() {
        System.out.println("---------------init()-------------------");
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("---------------intercept()-------------------");
        
        
        if (!(invocation.getAction() instanceof LoginAction)) { // 判断当前action是否是登录操作
            if (null == invocation.getInvocationContext().getSession().get("username")) { // 未登录
                
                return "loginerror";
            }
        }
        
        // 正常执行  下一个拦截器 如果没有拦截器执行Action方法
        return invocation.invoke();
    }
    

}
