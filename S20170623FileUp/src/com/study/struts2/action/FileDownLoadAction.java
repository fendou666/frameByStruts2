package com.study.struts2.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownLoadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String downLoadFileName;
	public String getDownLoadFileName() {
		return downLoadFileName;
	}
	public void setDownLoadFileName(String downLoadFileName) {
		this.downLoadFileName = downLoadFileName;
	}
	
	public InputStream getInputStream(){
		FileInputStream outStream = null;
		try {
			//downLoadFileName += File.separator + "Mc - Õ½¸èÍø.mp3"; 
			downLoadFileName += File.separator + "54769b4733857.jpg"; 
			outStream = new FileInputStream(downLoadFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return outStream;
	}
	
	@Override
	public String execute() throws Exception {
		downLoadFileName = "D:\\Pictures";
		return super.execute();
	}
	
}
