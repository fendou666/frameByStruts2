package com.qst.action.test1;

import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default") 
@Namespace("/test1") 
@Results( { @Result(name = "success", location = "/test1/welcome.jsp"), 
       @Result(name = "error", location = "/test1/error.jsp") }) 
public class LoginAction extends ActionSupport
{
    private String username;
    private String password;

    public String getUsername()
	{
        return username;
    }
    public void setUsername(String username)
	{
        this.username = username;
    }

    public String getPassword()
	{
        return password;
    }
    public void setPassword(String password)
	{
        this.password = password;
    }

	public String execute() throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
        if ("admin".equals(username)
			&& "admin".equals(password))
		{			
			ctx.getSession().put("user" , username);
			ctx.put("tip" , getText("succTip"));
            return SUCCESS;
        }
		else
		{
			ctx.put("tip" , getText("failTip"));
            return ERROR;
        }
    }
}