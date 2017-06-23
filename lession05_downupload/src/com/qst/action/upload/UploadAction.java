package com.qst.action.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;



public class UploadAction extends ActionSupport
{
	private String title;
    private File[] upload;
    private String[] uploadContentType;
    private String[] uploadFileName;

	//��������ע�������
    private String savePath;
	//��������ע��ķ���
    public void setSavePath(String value)
	{
        this.savePath = value;
    }

    private String getSavePath() throws Exception 
	{
        return ServletActionContext.getRequest().getRealPath(savePath);
    }
	
	public void setTitle(String title) {
		this.title = title; 
	}

	public void setUpload(File[] upload) {
		this.upload = upload; 
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType; 
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName; 
	}

	public String getTitle() {
		return (this.title); 
	}

	public File[] getUpload() {
		return (this.upload); 
	}

	public String[] getUploadContentType() {
		return (this.uploadContentType); 
	}

	public String[] getUploadFileName() {
		return (this.uploadFileName); 
	}
	@Override
    public String execute() throws Exception
	{
		File[] files = getUpload();
		for (int i = 0 ; i < files.length ; i++)
		{
			//�Է��������ļ������ַ��ԭ�ļ��������ϴ��ļ������
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\" + getUploadFileName()[i]);
			FileInputStream fis = new FileInputStream(files[i]);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer , 0 , len);
			}
		}
        return SUCCESS;
    }
}