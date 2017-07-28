package com.seasky.managersys.controller;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	String str="";
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1ͼƬ����
		BufferedImage image = new BufferedImage(65, 26, Image.SCALE_DEFAULT);
//		2��û���
		Graphics graphics = image.getGraphics();
//		3���������
		Random random = new Random();
//		4���û�������
		graphics.setFont(new Font("Times New Roman", Font.ITALIC, 24));
//		5���������
		str = "";
		for(int i=0;i<4;i++){
			String rand = String.valueOf(random.nextInt(10));
//			ƴ�������
			str += rand;
//			�����ڻ���Ķ�λ
			graphics.drawString(rand, 10*i+10, 20);
		}
		//ͼƬ����
		graphics.dispose();
		
		request.getSession().setAttribute("rand", str);
//		System.out.println(str);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

}
