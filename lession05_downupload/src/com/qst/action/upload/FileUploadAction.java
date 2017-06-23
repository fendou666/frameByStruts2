package com.qst.action.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = -7001482935770262132L;
	private File up;
	private String upContentType;
	private String upFileName;
	private String destPath;

	public String execute() throws Exception {

		System.out.println("contentType==" + this.upContentType);
		System.out.println("fileName==" + this.upFileName);

		File basePath = new File(ServletActionContext.getServletContext()
				.getRealPath(destPath));

		if (!basePath.exists()) {
			basePath.mkdirs();
		}

		copy(up, new File(basePath.getCanonicalPath() + "/" + upFileName));

		return SUCCESS;
	}

	public void copy(File srcFile, File destFile) throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(srcFile));
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			byte[] buf = new byte[8192];

			for (int count = -1; (count = bis.read(buf)) != -1;) {
				bos.write(buf, 0, count);
			}
			bos.flush();
		} catch (IOException ie) {
			throw ie;
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}

	public File getUp() {
		return up;
	}

	public void setUp(File up) {
		this.up = up;
	}

	public String getUpContentType() {
		return upContentType;
	}

	public void setUpContentType(String upContentType) {
		this.upContentType = upContentType;
	}

	public String getUpFileName() {
		return upFileName;
	}

	public void setUpFileName(String upFileName) {
		this.upFileName = upFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

}