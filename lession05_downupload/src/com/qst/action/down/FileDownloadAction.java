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
	 下载用的Action应该返回一个InputStream实例，
	 该方法对应在result里的inputName属性值为targetFile
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
