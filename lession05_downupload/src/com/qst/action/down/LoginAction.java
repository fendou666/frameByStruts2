package com.qst.action.down;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport 
{
	private String user;
	private String pass;

	
	public void setUser(String user)
	{
		this.user = user; 
	}
	public String getUser() 
	{
		return (this.user); 
	}
	
	public void setPass(String pass)
	{
		this.pass = pass; 
	}

	public String getPass()
	{
		return (this.pass); 
	}

	public String execute()
	{
		ActionContext.getContext().getSession().put("user" , getUser());
		return SUCCESS;
	}
}
