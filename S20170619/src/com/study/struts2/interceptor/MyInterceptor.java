package com.study.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor implements Interceptor {

	@Override
	public void destroy() {
		System.out.println("ִ���� �Զ����������� destroy����");
	}

	@Override
	public void init() {
		System.out.println("ִ���� ��������init ����");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("ִ�� invoke֮ǰ");
		System.out.println("ִ��֮ǰ��ȡ��session��Ϣ" + arg0.getInvocationContext().getSession().get("userInfo"));
		String ret = arg0.invoke();
		System.out.println("ִ�� invoke֮��");
		System.out.println("ִ��֮���ȡ��session��Ϣ" + arg0.getInvocationContext().getSession().get("userInfo"));
		// ������һ��struts2���: ������/action
		return ret;
	}

}
