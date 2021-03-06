package com.study.struts2.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUpLoadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private File up;
	private String upFileContentType;
	private String upFileName; // 这个怎么传递进来
	private String upFilePath;
	
	public File getUp() {
		return up;
	}

	public void setUp(File up) {
		this.up = up;
	}

	public String getUpFileContentType() {
		return upFileContentType;
	}

	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}

	public String getUpFileName() {
		return upFileName;
	}

	public void setUpFileName(String upFileName) {
		this.upFileName = upFileName;
	}

	public String getUpFilePath() {
		return upFilePath;
	}

	public void setUpFilePath(String upFilePath) {
		this.upFilePath = upFilePath;
	}

	@Override
	public String execute() throws Exception {
		// ============定义变量输入流， 输出流
		// =====输入流
		BufferedInputStream bi = new BufferedInputStream(new FileInputStream(up));
		// =====输出流
		// ===先定义输出位置目录 TOD
		upFilePath = ServletActionContext.getServletContext().getRealPath(upFilePath);
		
		System.out.println("upFilePath 为" +upFilePath);
		File outDir = new File(upFilePath);
		if(!outDir.exists()){
			outDir.mkdirs();
		}
		// ==上传文件目录+文件名
		upFilePath += File.separator + upFileName;
		// ==创建输出流
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(upFilePath));
		// ============流的读写
		byte [] b = new byte[1024];
		int l = -1;
		while((l=bi.read(b))!=-1){
			bos.write(b, 0, l);
		}
		// ============流资源关闭
		bos.flush();
		bi.close();
		bos.close();
		return SUCCESS;
	}
	
}
