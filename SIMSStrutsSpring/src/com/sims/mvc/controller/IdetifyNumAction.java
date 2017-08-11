package com.sims.mvc.controller;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class IdetifyNumAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	
	private String randNum = null;
	private Map<String, Object> session = null;
	HttpServletResponse response = null;
	
	public IdetifyNumAction() {
		response = ServletActionContext.getResponse();
	}

	@Override
	public String execute() throws Exception {
		// 1.定义一个缓存对象
		BufferedImage image = new BufferedImage(100, 40,
				BufferedImage.TYPE_3BYTE_BGR);
		// 2. 获得画笔
		Graphics g = image.getGraphics();
		g.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 26));
		// 3.用画笔画数字
		Random rand = new Random();
		String randNum = "";
		for (int i = 0; i < 4; i++) {
			String str = rand.nextInt(10) + "";// 画的内容
			randNum += str;// 画的内容进行拼接
			g.drawString(str, 20 * i + 9, 22 + i * 4);
		}
		// 4.画好的图片定型
		g.dispose();
		
		
		// 5.保存到会话中
		session.put("rand", randNum);
		
		// 6.将图片以流的形式返回给前端
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return null;
	}
	
	public String idetifyNum() throws Exception {
		
		String rand=(String) session.get("rand");
		
		PrintWriter out = response.getWriter();
		if(randNum==null||randNum.equals("")||rand==null||rand.equals("")){
			out.write("NULL");
		}else if(randNum.equals(rand)){
				out.write("OK");
		}else{
				out.write("NG");
		}
		return null;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getRandNum() {
		return randNum;
	}

	public void setRandNum(String randNum) {
		this.randNum = randNum;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	
	

}
