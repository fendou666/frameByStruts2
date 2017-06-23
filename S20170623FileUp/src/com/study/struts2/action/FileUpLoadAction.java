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
	private String upFileName; // �����ô���ݽ���
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
		// ============��������������� �����
		// =====������
		BufferedInputStream bi = new BufferedInputStream(new FileInputStream(up));
		// =====�����
		// ===�ȶ������λ��Ŀ¼ TOD
		upFilePath = ServletActionContext.getServletContext().getRealPath(upFilePath);
		
		System.out.println("upFilePath Ϊ" +upFilePath);
		File outDir = new File(upFilePath);
		if(!outDir.exists()){
			outDir.mkdirs();
		}
		// ==�ϴ��ļ�Ŀ¼+�ļ���
		upFilePath += File.separator + upFileName;
		// ==���������
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(upFilePath));
		// ============���Ķ�д
		byte [] b = new byte[1024];
		int l = -1;
		while((l=bi.read(b))!=-1){
			bos.write(b, 0, l);
		}
		// ============����Դ�ر�
		bos.flush();
		bi.close();
		bos.close();
		return SUCCESS;
	}
	
}