package com.qst.action.down;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class FileDownloadAction extends ActionSupport 
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
        return SUCCESS;
    }

}
