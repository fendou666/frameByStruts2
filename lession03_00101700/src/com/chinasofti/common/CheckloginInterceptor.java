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
        
        
        if (!(invocation.getAction() instanceof LoginAction)) { // �жϵ�ǰaction�Ƿ��ǵ�¼����
            if (null == invocation.getInvocationContext().getSession().get("username")) { // δ��¼
                
                return "loginerror";
            }
        }
        
        // ����ִ��  ��һ�������� ���û��������ִ��Action����
        return invocation.invoke();
    }
    

}
