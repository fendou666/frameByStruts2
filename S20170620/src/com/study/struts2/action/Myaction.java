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
		ac.put("acvv", "����ͨ��ActionContext���õ�valueֵ");
		ac.getSession().put("sesvv", "����ͨ��session���õ�valueֵ");
		ac.getParameters().put("paramvv", "����ͨ��Parameters���õ�valueֵ");
		ac.getApplication().put("appvv", "����ͨ��Application���õ�valueֵ");
		ac.getValueStack().set("vsckvv", "����ͨ��ValueStack���õ�valueֵ");
		ac.getContextMap().put("conmpvv", "����ͨ��ContextMap���õ�valueֵ");
		return SUCCESS;
	}

	
}
