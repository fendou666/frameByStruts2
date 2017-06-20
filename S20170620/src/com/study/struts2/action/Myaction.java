package com.study.struts2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Myaction extends ActionSupport {
	
	

	private String actionMeb;
	
	private static final long serialVersionUID = 1L;
	public String getActionMeb() {
		return actionMeb;
	}

	public void setActionMeb(String actionMeb) {
		this.actionMeb = actionMeb;
	}
	
	@Override
	public String execute() throws Exception {
		ActionContext ac = ActionContext.getContext();
		ac.put("acvv", "我是通过ActionContext设置的value值");
		ac.getSession().put("sesvv", "我是通过session设置的value值");
		ac.getParameters().put("paramvv", "我是通过Parameters设置的value值");
		ac.getApplication().put("appvv", "我是通过Application设置的value值");
		ac.getValueStack().set("vsckvv", "我是通过ValueStack设置的value值");
		ac.getContextMap().put("conmpvv", "我是通过ContextMap设置的value值");
		return SUCCESS;
	}

	
}
