package com.study.struts2.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CollectionAction extends ActionSupport {
	
	

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
		ArrayList<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		ActionContext ac = ActionContext.getContext();
		ac.put("acList", list);
		
		return SUCCESS;
	}

	
}
