package com.qst.action.down;

import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AuthorityDownAction extends ActionSupport 
{
    private String inputPath;
    public void setInputPath(String value)
	{
        inputPath = value;
    }

	/*
	 �����õ�ActionӦ�÷���һ��InputStreamʵ����
	 �÷�����Ӧ��result���inputName����ֵΪtargetFile
	*/
    public InputStream getTargetFile() throws Exception 
	{
        return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
    }

    public String execute() throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		String user = (String)session.get("user");
		if ( user !=  null && user.equals("admin"))
		{
			return SUCCESS;
		}
		ctx.put("tip" , "����û�е�½�����ߵ�½���û�������ȷ�������µ�½��");
		return LOGIN;
    }

}
