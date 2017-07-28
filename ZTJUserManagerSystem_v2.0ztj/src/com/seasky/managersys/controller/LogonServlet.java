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

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.service.ILoainService;
import com.seasky.managersys.model.service.LoginServiceImp;

/**
 * Servlet implementation class LogonServlet
 */
@WebServlet("/LogonServlet")
public class LogonServlet extends HttpServlet {
	ImgServlet imgServlet = new ImgServlet();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogonServlet() {
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
		
		String userId = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String ident = request.getParameter("ident");
		String img = (String)request.getSession().getAttribute("rand");
		UserInfo user=null;
		ILoainService loginService=new LoginServiceImp();
		System.out.println("-----------------------------------");
		System.out.println("logon服务器从前台拿到的用户名："+userId);
		System.out.println("logon服务器从前台拿到的密码："+pwd);
		System.out.println("logon服务器从前台拿到的验证码："+ident);
		System.out.println("logon服务器从验证服务器拿到的验证码："+img);
		System.out.println("-----------------------------------");
		request.removeAttribute("err");
		if (ident==null&&("").equals(ident)) {
			request.setAttribute("err", "验证码不能为空！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		else if (ident.equals(img)) {
			if (userId!=null&&pwd!=null&&!userId.equals("")&&!pwd.equals("")) {
				if(!userId.matches("[0-9]+")){
					request.setAttribute("err", "用户名必须是数字！");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				}else{
					if (userId.length()<10) {
						user=loginService.checkUserInfo(Integer.parseInt(userId), pwd);
//						System.out.println("HeadmasterId"+user.getHeadmasterId());
						System.out.println("id回馈的姓名:"+user.getName());
					}
					else if(userId.length()==11){
						user=loginService.checkUserInfo(Long.parseLong(userId), pwd);
						System.out.println("HeadmasterId"+user.getHeadmasterId());
						System.out.println("电话回馈的姓名:"+user.getName());
					}
					else{
						request.setAttribute("err", "用户名不存在！");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						return;
					}
					if(user==null){
						request.setAttribute("err", "用户名或密码错误！");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						return;
					}else{
						if(user.getPower()==3){
							request.getSession().setAttribute("user", user);
							request.getRequestDispatcher("success.jsp").forward(request, response);
						}else{
							response.sendRedirect("index.jsp");
						}
					}
				}
			}else{
				request.setAttribute("err", "用户名或密码不能为空！");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
		} else {
			request.setAttribute("err", "验证码错误！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
